package com.pikaso.crypto;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoService implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        callService();
    }

    void callService() {
        final String url = "https://api.binance.com/api/v3/avgPrice?symbol=ETHUSDT";

        final RestTemplate restTemplate = new RestTemplate();
        final ResponsePriceModel result = restTemplate.getForObject(url, ResponsePriceModel.class);

        if (ObjectUtils.isNotEmpty(result)) {
            System.out.println("Ethereum price: " + Math.round(result.getPrice()));
        }
    }
}
