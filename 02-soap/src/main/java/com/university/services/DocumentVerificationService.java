package com.university.services;

import javax.jws.WebMethod;
import javax.jws.WebService;


// táto službo odpovedá Application Request/Response v diagrame
@WebService
public interface DocumentVerificationService {
    @WebMethod
    boolean verifyDocuments(String studentId);
}
