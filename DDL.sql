create table payload_details
    (id number not null primary key,
    request_uri VARCHAR2(1000 char) not null,
    request_method VARCHAR2(50 char) not null,
    request_headers VARCHAR2(4000) constraint ensure_req_header_json check (request_headers is json),
    request_payload VARCHAR2(4000) constraint ensure_req_payload_json check (request_payload is json),
    response_headers VARCHAR2(4000) constraint ensure_resp_header_json check (response_headers is json),
    response_payload VARCHAR2(4000) constraint ensure_resp_payload_json check (response_payload is json),
    response_status number);