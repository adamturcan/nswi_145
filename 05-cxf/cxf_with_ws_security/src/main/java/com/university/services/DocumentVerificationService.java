package com.university.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://services.university.com/")
public interface DocumentVerificationService {

    @WebMethod(operationName = "verifyDocuments")
    boolean verifyDocuments(@WebParam(name = "studentId") String studentId);

    @WebMethod(operationName = "getVerificationStatus")
    String getVerificationStatus(@WebParam(name = "studentId") String studentId);
}
