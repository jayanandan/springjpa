package com.example.springjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PAYLOAD_DETAILS")
@Getter
@Setter
public class PayloadEntity {

    @Id
    @SequenceGenerator(name = "payload_id",sequenceName = "PAYLOAD_DETAILS_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "payload_id")
    private Long id;
    @Column(name = "request_uri")
    private String requestUri;
    @Column(name = "request_method")
    private String requestMethod;
    @Column(name = "request_headers")
    private String requestHeaders;
    @Column(name = "request_payload")
    private String requestPayload;
    @Column(name = "response_headers")
    private String responseHeaders;
    @Column(name = "response_payload")
    private String responsePayload;
    @Column(name = "response_status")
    private Long responseStatus;

}
