package com.university.intermediary;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.*;

@WebServlet("/soap-intermediary")
public class SOAPIntermediaryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, request.getInputStream());
            SOAPHeader header = soapMessage.getSOAPHeader();
            SOAPBody soapBody = soapMessage.getSOAPBody();

            NodeList feeNodes = header.getElementsByTagName("ProcessingFee");
            double processingFee = (feeNodes.getLength() > 0) ? Double.parseDouble(feeNodes.item(0).getTextContent()) : 0;

            NodeList amountNodes = soapBody.getElementsByTagName("amount");
            NodeList studentIdNodes = soapBody.getElementsByTagName("studentId");

            if (amountNodes.getLength() == 0 || studentIdNodes.getLength() == 0) {
                throw new Exception("Error: <amount> or <studentId> element not found in SOAP request.");
            }

            double amount = Double.parseDouble(amountNodes.item(0).getTextContent());
            String studentId = studentIdNodes.item(0).getTextContent();
            double modifiedAmount = amount + processingFee;

            SOAPMessage modifiedSoapRequest = createSOAPRequest(studentId, modifiedAmount);
            SOAPMessage responseMessage = forwardToPaymentService(modifiedSoapRequest);

            if (responseMessage == null) {
                throw new Exception("Received NULL response from PaymentProcessingService");
            }

            SOAPBody responseBody = responseMessage.getSOAPBody();
            NodeList returnNodes = responseBody.getElementsByTagName("return");

            if (returnNodes.getLength() == 0) {
                throw new Exception("Error: <return> element missing in response.");
            }

            SOAPHeader responseHeader = responseMessage.getSOAPHeader();
            if (responseHeader == null) {
                responseHeader = responseMessage.getSOAPPart().getEnvelope().addHeader();
            }

            Name headerName = responseMessage.getSOAPPart().getEnvelope().createName("ProcessingStatus", "ser", "http://services.university.com/");
            SOAPHeaderElement headerElement = responseHeader.addHeaderElement(headerName);
            headerElement.addTextNode("Success, fee included, with amount of " + processingFee + ". Total amount: " + modifiedAmount);

            responseMessage.saveChanges();

            response.setContentType("text/xml;charset=UTF-8");
            response.setHeader("Content-Type", "text/xml;charset=UTF-8");
            responseMessage.writeTo(response.getOutputStream());

        } catch (Exception e) {
            response.getWriter().write("Error processing SOAP request: " + e.getMessage());
        }
    }

    private SOAPMessage createSOAPRequest(String studentId, double amount) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody soapBody = envelope.getBody();

        envelope.addNamespaceDeclaration("ser", "http://services.university.com/");

        SOAPElement processPayment = soapBody.addChildElement("processPayment", "ser");
        processPayment.addChildElement("studentId").addTextNode(studentId);
        processPayment.addChildElement("amount").addTextNode(String.valueOf(amount));

        soapMessage.saveChanges();
        return soapMessage;
    }

    private SOAPMessage forwardToPaymentService(SOAPMessage requestMessage) throws Exception {
        URL endpoint = new URL("http://localhost:8083/university/payment");
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml");

        OutputStream outputStream = connection.getOutputStream();
        requestMessage.writeTo(outputStream);
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("PaymentProcessingService returned HTTP " + responseCode);
        }

        try {
            InputStream inputStream = connection.getInputStream();
            return MessageFactory.newInstance().createMessage(null, inputStream);
        } catch (IOException e) {
            throw new Exception("Failed to get response from PaymentProcessingService");
        }
    }

}
