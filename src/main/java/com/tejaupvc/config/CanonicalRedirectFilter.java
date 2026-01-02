package com.tejaupvc.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CanonicalRedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String host = request.getServerName();

        if (host.startsWith("www.")) {
            String newUrl = "https://" + host.substring(4) + request.getRequestURI();
            if (request.getQueryString() != null) {
                newUrl += "?" + request.getQueryString();
            }
            response.setStatus(301);
            response.setHeader("Location", newUrl);
            return;
        }

        chain.doFilter(req, res);
    }
}
