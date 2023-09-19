package com.example.springjpa.filter;


import com.example.springjpa.events.EventPublisher;
import com.example.springjpa.model.Header;
import com.example.springjpa.model.PayloadDetails;
import com.example.springjpa.service.PayloadDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.Function;

@Component
@Order(1)
@Log4j2
public class RequestFilter implements Filter {

    @Autowired
    EventPublisher eventPublisher;

    @Autowired
    PayloadDetailsService payloadDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        if(!isDuplicateRequest(servletRequest,servletResponse)){
            filterChain.doFilter(requestWrapper,responseWrapper);
            try{
                PayloadDetails payloadDetails = populatePayload(requestWrapper, responseWrapper);
                eventPublisher.publishPayloadEvent(payloadDetails);
            }catch (Exception e){
                log.error("problem in publish event");
            }finally {
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    private boolean isDuplicateRequest(ServletRequest servletRequest, ServletResponse servletResponse){

        try {
            String requestID = ((HttpServletRequest) servletRequest).getHeader("x-request-id");
            if(null != requestID && requestID.length()>0){
                Optional<PayloadDetails> payloadDetails = payloadDetailsService.getPayloadDetails(Long.valueOf(requestID));

                payloadDetails.ifPresent(p->{

                    HttpServletResponse response = (HttpServletResponse)servletResponse;

                    List<Header> responseHeaders = p.getResponseHeaders();
                    if(responseHeaders != null){
                        responseHeaders.forEach(header -> {
                            if(null != header.getValues()){
                                header.getValues().forEach(val->{
                                    response.addHeader(header.getName(),val);
                                });
                            }
                        });
                    }

                    try {
                        String payload = p.getResponsePayload();
                        if(null != payload && payload.length()>0) {
                            response.setContentLength(payload.length());
                            response.setContentType(isValidJson(payload) ? "application/json":"plain/text");
                            PrintWriter responseWriter = response.getWriter();
                            responseWriter.write(payload);
                            responseWriter.flush();
                        }
                        response.setStatus(p.getResponseStatus().intValue());
                    } catch (IOException e) {
                        log.error("unable to write response ",e);
                    }

                });
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("unable to handleDuplicateRequest",e);
        }
        return Boolean.FALSE;
    }

    private boolean isValidJson(String jsonStr){
        try {
            new ObjectMapper().readTree(jsonStr);
            return Boolean.TRUE;
        } catch (JsonProcessingException e) {
            log.error("Not an json string");
        }
        return Boolean.FALSE;
    }

    private PayloadDetails populatePayload(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper ){
        PayloadDetails payloadDetails = new PayloadDetails();

        payloadDetails.setRequestUri(requestWrapper.getRequestURI());
        payloadDetails.setRequestMethod(requestWrapper.getMethod());
        payloadDetails.setRequestHeaders(populateHeaders(Collections.list(requestWrapper.getHeaderNames()), s -> Collections.list(requestWrapper.getHeaders(s)) ));
        payloadDetails.setRequestPayload(getPayload(requestWrapper.getContentAsByteArray(),requestWrapper.getCharacterEncoding()));
        payloadDetails.setResponseHeaders(populateHeaders(responseWrapper.getHeaderNames(),s->responseWrapper.getHeaders(s) ));
        payloadDetails.setResponsePayload(getPayload(responseWrapper.getContentAsByteArray(),responseWrapper.getCharacterEncoding()));
        payloadDetails.setResponseStatus(Long.valueOf(responseWrapper.getStatus()));
        return payloadDetails;

    }

    private List<Header> populateHeaders(Collection<String> headerNames, Function<String, Collection<String>> fn){
        List<Header> headers = new ArrayList<>(5);
        headerNames.stream().forEach(name->{
            final Header header = new Header();
            header.setName(name);
            header.setValues(new ArrayList<>(2));
            Collection<String> values = fn.apply(name);
            values.stream().forEach(val->header.getValues().add(val));
            headers.add(header);
        });
        return headers;
    }

    private String getPayload(byte[] content,String charSetName){
        if(null == content || content.length==0) return "";
        String payload = null;
        try{
            payload = new String(content, 0, content.length, charSetName);

        }catch (UnsupportedEncodingException ue){
        }

        return payload;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
