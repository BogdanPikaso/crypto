package com.pikaso.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    final CryptoService cryptoService;

    @Autowired
    public Controller(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping(path = "get/eth")
    String getEthereumUsdRate() {
        return cryptoService.getEthereumUsdRate();
    }
}
