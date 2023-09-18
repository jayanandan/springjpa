package com.example.springjpa.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PayloadDetails {
    private String requestUri;
    private String requestMethod;
    private List<Header> requestHeaders;
    private String requestPayload;
    private List<Header> responseHeaders;
    private String responsePayload;
    private Long responseStatus;
}
