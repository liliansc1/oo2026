package ee.lilian.veebipood.controller;

import ee.lilian.veebipood.model.JudgeDto;
import ee.lilian.veebipood.model.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MockApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL =
            "https://SINU-PROJECT.mockapi.io";

    @GetMapping("/judges")
    public JudgeDto[] getJudges() {
        return restTemplate.getForObject(
                BASE_URL + "/judges",
                JudgeDto[].class
        );
    }

    @GetMapping("/locations")
    public LocationDto[] getLocations() {
        return restTemplate.getForObject(
                BASE_URL + "/locations",
                LocationDto[].class
        );
    }
}
