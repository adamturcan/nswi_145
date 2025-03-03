package com.university.services;

import javax.jws.WebService;

@WebService(endpointInterface = "com.university.services.PaymentProcessingService")
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    @Override
    public String processPayment(String studentId, double amount) {
        /// napriklad ak je suma pod 100 eur :D
        if (amount < 100) {
            return "Platba zamietnuta, pre studenta so Student Id: " + studentId;
        }
        return "Platba uspesna, pre studenta so Student Id: " + studentId;
    }
}
