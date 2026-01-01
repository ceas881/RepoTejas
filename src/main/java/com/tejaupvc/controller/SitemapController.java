package com.tejaupvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SitemapController {

    @Value("${app.base-url}")
    private String baseUrl;

    @GetMapping(value = "/sitemap.xml", produces = "application/xml")
    public void sitemap(HttpServletResponse response) throws IOException {

        response.setContentType("application/xml");

        List<String> urls = List.of(
                baseUrl + "/",
                baseUrl + "/productos",
                baseUrl + "/contacto",
                baseUrl + "/checkout"
        );

        String today = LocalDate.now().toString();

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

        for (String url : urls) {
            xml.append("<url>");
            xml.append("<loc>").append(url).append("</loc>");
            xml.append("<lastmod>").append(today).append("</lastmod>");
            xml.append("<changefreq>weekly</changefreq>");
            xml.append("<priority>0.8</priority>");
            xml.append("</url>");
        }

        xml.append("</urlset>");

        response.getWriter().write(xml.toString());
    }
}
