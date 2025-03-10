package com.university.services;

import javax.jws.WebService;


@WebService(
        endpointInterface = "com.university.services.PaymentProcessingService",
        targetNamespace = "http://services.university.com/",
        serviceName = "PaymentProcessingService"
)
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    @Override
    public String processPayment(String studentId, double amount) {
        /// napriklad ak je suma pod 100 eur :D
        if (amount < 100) {
            return "Platba zamietnuta, pre studenta so Student Id: " + studentId;
        }
        return "Platba uspesna, pre studenta so Student Id: " + studentId;
    }


    @Override
    public String refundPayment(String studentId, double refundAmount) {
        if (refundAmount < 50) {
            return "Požiadavka zamietnutá pre studenta so Student Id: " + studentId;
        }
        return "Požiadavka o vrátenie čiastky: " + refundAmount + ", bola schválená pre študenta so Student Id: " + studentId;
    }
}
