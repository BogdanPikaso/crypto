package com.pikaso.crypto;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoService {

    public static final String URL_ETH_USD = "https://api.binance.com/api/v3/avgPrice?symbol=ETHUSDT";

    String getEthereumUsdRate() {

        final RestTemplate restTemplate = new RestTemplate();
        final ResponsePriceModel result = restTemplate.getForObject(URL_ETH_USD, ResponsePriceModel.class);

        String ethPrice = "Error during getting ethereum rate";
        if (ObjectUtils.isNotEmpty(result)) {
            ethPrice = String.valueOf(Math.round(result.getPrice()));
            System.out.println("Ethereum price: " + ethPrice);
        }
        return ethPrice;
    }
}
