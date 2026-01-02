package com.tejaupvc.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CanonicalRedirectConfig implements Filter {

    private static final String CANONICAL_HOST = "tejaupvc.com";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String host = req.getHeader("Host");
        boolean isHttps = req.isSecure();

        if (host != null && (!host.equals(CANONICAL_HOST) || !isHttps)) {

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