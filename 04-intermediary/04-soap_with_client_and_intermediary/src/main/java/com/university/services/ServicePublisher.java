package com.university.services;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {
        String docServiceURL = "http://localhost:8083/university/verification";
        String paymentServiceURL = "http://localhost:8083/university/payment";

        System.out.println("Sluzba verifikacie dokumentov zverejnena na: " + docServiceURL);
        Endpoint.publish(docServiceURL, new DocumentVerificationServiceImpl());

        System.out.println("zluzba na prevedenie platby zverejnena na: " + paymentServiceURL);
        Endpoint.publish(paymentServiceURL, new PaymentProcessingServiceImpl());

        System.out.println("Sluzby spustene, WSDL najdete tu:");
        System.out.println(docServiceURL + "?wsdl");
        System.out.println(paymentServiceURL + "?wsdl");
    }
}
