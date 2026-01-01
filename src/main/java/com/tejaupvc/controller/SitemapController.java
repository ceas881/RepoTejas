package com.tejaupvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SitemapController {

    private final String BASE_URL = "https://tejaupvc.com";

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap() {

        String today = LocalDate.now().toString();

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"

                + url("/", today)
                + url("/productos", today)
                + url("/accesorios", today)
                + url("/contacto", today)
                + url("/checkout", today)

                + "</urlset>";
    }

    private String url(String path, String lastmod) {
        return "<url>"
                + "<loc>" + BASE_URL + path + "</loc>"
                + "<lastmod>" + lastmod + "</lastmod>"
                + "<changefreq>weekly</changefreq>"
                + "<priority>0.8</priority>"
                + "</url>";
    }
}