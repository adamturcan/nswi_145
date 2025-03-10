package com.university.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;


// táto službo odpovedá Application Request/Response v diagrame
@WebService(targetNamespace = "http://services.university.com/")
public interface DocumentVerificationService {

    @WebMethod(operationName = "verifyDocuments")
    boolean verifyDocuments(@WebParam(name = "studentId") String studentId);

    @WebMethod(operationName = "getVerificationStatus")
    String getVerificationStatus(@WebParam(name = "studentId") String studentId);
}
