package com.university.client;

import javax.xml.soap.*;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentProcessingClient {
    public static void main(String[] args) {
        try {
            // Updated endpoint to SOAP Intermediary
            String endpoint = "http://localhost:8080/soap_intermediary/soap-intermediary";

            // Create SOAP Request with Header
            SOAPMessage request = createSOAPRequest("1234", 150.0);
            request.writeTo(System.out);
            System.out.println();

            // Send request and receive response
            SOAPMessage response = forwardToPaymentService(request, endpoint);
            response.writeTo(System.out);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String studentId, double amount) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        SOAPHeader soapHeader = envelope.getHeader();
        if (soapHeader == null) {
            soapHeader = envelope.addHeader();
        }
        SOAPBody soapBody = envelope.getBody();

        envelope.addNamespaceDeclaration("ser", "http://services.university.com/");

        SOAPElement processingFeeHeader = soapHeader.addChildElement("ProcessingFee", "", "");
        processingFeeHeader.addTextNode("5.00");

        SOAPElement processPayment = soapBody.addChildElement("processPayment", "ser");
        processPayment.addChildElement("studentId").addTextNode(studentId);

        String amountValue = (amount == (int) amount) ? String.valueOf((int) amount) : String.valueOf(amount);
        processPayment.addChildElement("amount").addTextNode(amountValue);

        soapMessage.saveChanges();
        return soapMessage;
    }

    private static SOAPMessage forwardToPaymentService(SOAPMessage requestMessage, String endpoint) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

        try (OutputStream outputStream = connection.getOutputStream()) {
            requestMessage.writeTo(outputStream);
        }

        int responseCode = connection.getResponseCode();
        InputStream inputStream = connection.getInputStream();

        if (responseCode != 200) {
            throw new Exception("PaymentProcessingService returned HTTP " + responseCode);
        }

        return MessageFactory.newInstance().createMessage(null, inputStream);
    }
}
