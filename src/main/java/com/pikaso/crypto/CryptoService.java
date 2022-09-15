package com.pikaso.crypto;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoService {

    public static final String URL_ETH_USD = "https://api.binance.com/api/v3/avgPrice?symbol=ETHUSDT";

    final RestTemplate restTemplate;

    @Autowired
    public CryptoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String getEthereumUsdRate() {
        final ResponsePriceModel ethUsdResponse = getEthUsdModel();
        return getValidateEthUsdRate(ethUsdResponse);
    }

    private ResponsePriceModel getEthUsdModel() {
        return restTemplate.getForObject(URL_ETH_USD, ResponsePriceModel.class);
    }

    private String getValidateEthUsdRate(final ResponsePriceModel ethUsdResponse) {
        return ObjectUtils.isNotEmpty(ethUsdResponse)
                ? String.valueOf(Math.round(ethUsdResponse.getPrice()))
                : "Error during getting ethereum rate";
    }
}
