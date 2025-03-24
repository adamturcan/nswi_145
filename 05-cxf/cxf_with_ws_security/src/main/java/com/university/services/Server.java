package com.university.services;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.apache.wss4j.common.WSS4JConstants;

import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        DocumentVerificationServiceImpl implementor = new DocumentVerificationServiceImpl();
        String address = "http://localhost:8080/documentVerificationService";

        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(DocumentVerificationService.class);
        factory.setAddress(address);
        factory.setServiceBean(implementor);

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSS4JConstants.PW_TEXT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallback.class.getName());

        factory.getInInterceptors().add(new WSS4JInInterceptor(inProps));

        factory.create();
        System.out.println("Service started at " + address);
    }
}
