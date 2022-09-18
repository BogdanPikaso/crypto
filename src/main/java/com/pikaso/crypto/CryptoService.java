package com.pikaso.crypto;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoService {

    public static final String URL_ETH_USD = "https://api.binance.com/api/v3/avgPrice?symbol=ETHUSDT";

    RestTemplate restTemplate;

    JavaMailSender javaMailSender;

    @Autowired
    public CryptoService(RestTemplate restTemplate, JavaMailSender javaMailSender) {
        this.restTemplate = restTemplate;
        this.javaMailSender = javaMailSender;
    }

    String getEthereumUsdRate() {
        final ResponsePriceModel ethUsdResponse = getEthUsdModel();
        final String ethUsdPrice = getValidateEthUsdRate(ethUsdResponse);
        sendMessage(ethUsdPrice);
        return ethUsdPrice;
    }

    private ResponsePriceModel getEthUsdModel() {
        return restTemplate.getForObject(URL_ETH_USD, ResponsePriceModel.class);
    }

    private String getValidateEthUsdRate(final ResponsePriceModel ethUsdResponse) {
        return ObjectUtils.isNotEmpty(ethUsdResponse)
                ? String.valueOf(Math.round(ethUsdResponse.getPrice()))
                : "Error during getting ethereum rate";
    }

    @Async
    void sendMessage(final String ethUsdPrice) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("bogdanpikaso@gmail.com");
        mailMessage.setSubject("Ethereum price mail");
        mailMessage.setText(ethUsdPrice);
        javaMailSender.send(mailMessage);
    }
}
