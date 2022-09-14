package com.pikaso.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    CryptoService cryptoService;

    @GetMapping(path = "get")
    void callService() {
        cryptoService.callService();
    }
}
