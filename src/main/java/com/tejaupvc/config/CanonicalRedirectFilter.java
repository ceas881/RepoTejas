package com.tejaupvc.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CanonicalRedirectFilter implements Filter {

    private static final String CANONICAL_DOMAIN = "https://tejaupvc.com";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String host = req.getHeader("Host");

        if (host != null && (
                host.equalsIgnoreCase("www.tejaupvc.com") ||
                host.equalsIgnoreCase("tejaupvc.onrender.com")
        )) {
            String newUrl = CANONICAL_DOMAIN + req.getRequestURI();
            if (req.getQueryString() != null) {
                newUrl += "?" + req.getQueryString();
            }
            res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            res.setHeader("Location", newUrl);
            return;
        }

        chain.doFilter(request, response);
    }
}
