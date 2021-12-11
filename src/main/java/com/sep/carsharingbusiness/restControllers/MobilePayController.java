package com.sep.carsharingbusiness.restControllers;


import com.google.gson.Gson;
import com.sep.carsharingbusiness.log.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class MobilePayController {

    private final Gson gson;
    private final String paymentAdress;

    public MobilePayController(Gson gson) {
        this.gson = gson;
        this.paymentAdress = "123456789";
    }

    //in the future here will be a connection to mobilepay API, from which the payment address will be obtained and
    //also the final price will be sent to mobile pay to verify that the received amount is the correct amount

    @GetMapping(value = "/mobilepay")
    public synchronized String getNewPaymentID() {

            Log.addLog("|restControllers/MobilePayController.getNewPaymentID|");
            return paymentAdress;
    }

    @GetMapping(value = "/mobilepay/verify")
    public synchronized boolean verifyPayment() throws InterruptedException {
        Log.addLog("|restControllers/MobilePayController.verifyPayment|");
        Thread.sleep(3000);
        return true;
    }

}
