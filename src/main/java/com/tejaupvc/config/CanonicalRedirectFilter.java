package com.tejaupvc.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CanonicalRedirectFilter implements Filter {

    private static final String CANONICAL_HOST = "tejaupvc.com";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String host = req.getHeader("Host");
        String proto = req.getHeader("X-Forwarded-Proto");

        boolean isHttps = "https".equalsIgnoreCase(proto);
        boolean isCanonicalHost = CANONICAL_HOST.equalsIgnoreCase(host);

        if (host != null && (!isHttps || !isCanonicalHost)) {

            String redirectUrl = "https://" + CANONICAL_HOST + req.getRequestURI();

            if (req.getQueryString() != null) {
                redirectUrl += "?" + req.getQueryString();
            }

            res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            res.setHeader("Location", redirectUrl);
            return;
        }

        chain.doFilter(request, response);
    }
}
