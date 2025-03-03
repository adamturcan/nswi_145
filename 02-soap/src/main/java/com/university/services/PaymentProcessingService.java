package com.university.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

///tato sluzba odpoveda Payment request / denial/confirmation v diagrame
@WebService
public interface PaymentProcessingService {
    @WebMethod
    String processPayment(String studentId, double amount);
}
