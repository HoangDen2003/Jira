//package com.jira.api_gateway.repository;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.service.annotation.PostExchange;
//
//public interface IdentityClient {
//    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
//    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
//}
