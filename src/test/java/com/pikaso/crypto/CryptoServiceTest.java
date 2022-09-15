package com.pikaso.crypto;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CryptoServiceTest {

    @Autowired
    CryptoService cryptoService;

    @Autowired
    RestTemplate restTemplate;

    @Test
    void binanceResponseModelIsCorrect() {
        try {
            restTemplate.getForObject(CryptoService.URL_ETH_USD, ResponsePriceModel.class);
        } catch (RestClientException restClientException) {
            fail("Test fails by cause: " + restClientException.getMessage());
        } catch (Exception exception) {
            fail("Test fails by cause: " + exception.getMessage());
        }
    }

    @Test
    void binanceResponseModelIsNotNull() {
        assertNotNull(restTemplate.getForObject(CryptoService.URL_ETH_USD, ResponsePriceModel.class));
    }

    @Test
    void binanceResponseModelHasNotNullValues() {
        final ResponsePriceModel priceModel = restTemplate.getForObject(CryptoService.URL_ETH_USD, ResponsePriceModel.class);
        assertNotNull(priceModel.getMins());
        assertNotNull(priceModel.getPrice());
    }

    @Test
    void binanceResponseModelHasPositiveValues() {
        final ResponsePriceModel priceModel = restTemplate.getForObject(CryptoService.URL_ETH_USD, ResponsePriceModel.class);
        assert priceModel != null;
        assertTrue(priceModel.getMins() > 0);
        assertTrue(priceModel.getPrice() > 0);
    }

    @Test
    void errorMessageIsCorrectWhenResponseIsNull() {
        RestTemplate mockedRestTemplate = mock(RestTemplate.class);
        cryptoService = new CryptoService(mockedRestTemplate);
        when(mockedRestTemplate.getForObject(CryptoService.URL_ETH_USD, ResponsePriceModel.class)).thenReturn(null);
        String result = cryptoService.getEthereumUsdRate();
        assertEquals("Error during getting ethereum rate", result);
    }
}