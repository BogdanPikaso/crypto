package com.pikaso.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Service implements ApplicationRunner {

    void callService() {
        final String url = "https://api.binance.com/api/v3/avgPrice?symbol=ETHUSDT";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        Model model = new Model();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            model = objectMapper.readValue(result, Model.class);
        } catch (Exception ex) {

        }

        System.out.println("Ethereum price: " + Math.round(model.getPrice()));
        System.out.println(result);
    }

    @Override
    public void run(ApplicationArguments args) {
        callService();
    }
}
