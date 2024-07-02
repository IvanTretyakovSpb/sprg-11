package ru.tretyakov.http.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoggingFilter implements Filter {

    private final LoggingProperties properties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logRequest(httpServletRequest);
        chain.doFilter(request, response);

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        logResponse(httpServletRequest, httpServletResponse);
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response) {
        doLog("Response {}: status = {}", request.getServletPath(), response.getStatus());
    }

    private void logRequest(HttpServletRequest request) throws IOException {
        doLog("Request {} {}", request.getMethod(), request.getServletPath());

        if (properties.isLogBody()) {
            try (BufferedInputStream is = new BufferedInputStream(request.getInputStream())) {
                String body = new String(is.readAllBytes());
                doLog("Request body {}", body);
            }
        }
    }

    private void doLog(String text, Object... params) {
        log.atLevel(properties.getLogLevel()).log(text, params);
    }
}
