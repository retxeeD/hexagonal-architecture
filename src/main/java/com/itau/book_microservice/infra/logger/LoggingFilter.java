package com.itau.book_microservice.infra.logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(wrappedRequest, wrappedResponse);
        long duration = System.currentTimeMillis() - startTime;

        logRequest(wrappedRequest);
        logResponse(wrappedResponse, duration);

        wrappedResponse.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("method", request.getMethod());
        requestData.put("uri", request.getRequestURI());
        requestData.put("headers", getHeaders(request));
        requestData.put("queryString", request.getQueryString());
        requestData.put("body", getRequestBodyAsJson(request));

        try {
            String jsonLog = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData);
            logger.info("Request: {}", jsonLog);
        } catch (Exception e) {
            logger.error("Failed to log request in JSON format", e);
        }
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("statusCode", response.getStatus());
        responseData.put("headers", getHeaders(response));
        responseData.put("body", getResponseBodyAsJson(response));
        responseData.put("duration", duration);

        try {
            String jsonLog = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseData);
            logger.info("Response: {}", jsonLog);
        } catch (Exception e) {
            logger.error("Failed to log response in JSON format", e);
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            headers.put(headerName, response.getHeader(headerName));
        }
        return headers;
    }

    private JsonNode getRequestBodyAsJson(ContentCachingRequestWrapper request) {
        try {
            byte[] content = request.getContentAsByteArray();
            if (content.length > 0) {
                return objectMapper.readTree(content);
            }
        } catch (IOException e) {
            logger.error("Failed to read request body", e);
        }
        return null;
    }

    private JsonNode getResponseBodyAsJson(ContentCachingResponseWrapper response) {
        try {
            byte[] content = response.getContentAsByteArray();
            if (content.length > 0) {
                return objectMapper.readTree(content);
            }
        } catch (IOException e) {
            logger.error("Failed to read response body", e);
        }
        return null;
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}
