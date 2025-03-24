package com.university.services;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.apache.wss4j.common.WSS4JConstants;


import javax.xml.ws.soap.SOAPFaultException;
import java.util.HashMap;
import java.util.Map;

public class DocumentVerificationClient {

    public static void main(String[] args) {
        String address = "http://localhost:8080/documentVerificationService";

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DocumentVerificationService.class);
        factory.setAddress(address);

        DocumentVerificationService service = (DocumentVerificationService) factory.create();

        Client client = ClientProxy.getClient(service);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, "testuser");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSS4JConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());

        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));

        String studentId = "123456";
        try {
            boolean result = service.verifyDocuments(studentId);
            System.out.println("Verified: " + result);
            System.out.println(service.getVerificationStatus(studentId));
        } catch (SOAPFaultException fault) {
            System.out.println("Security error: " + fault.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
