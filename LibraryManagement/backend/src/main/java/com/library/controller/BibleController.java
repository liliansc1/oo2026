package com.library.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class BibleController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("bibles")
    public BibleDto[] getBiblesFromExternalApi() {
        String url = "https://holy-bible-api.com/bibles";
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity:null,
        )
    }
}
