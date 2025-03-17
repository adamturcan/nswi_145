package com.university.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

///tato sluzba odpoveda Payment request / denial/confirmation v diagrame
@WebService(targetNamespace = "http://services.university.com/")
public interface PaymentProcessingService {
    @WebMethod(operationName = "processPayment")
    String processPayment(@WebParam(name = "studentId")  String studentId,@WebParam(name = "amount")  double amount);


    @WebMethod(operationName = "refundPayment")
    String refundPayment( @WebParam(name = "studentId") String studentId, @WebParam(name = "refundAmount") double refundAmount);
}
