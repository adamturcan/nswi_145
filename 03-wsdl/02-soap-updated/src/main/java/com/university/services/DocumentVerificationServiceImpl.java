package com.university.services;

import javax.jws.WebService;

@WebService(
        endpointInterface = "com.university.services.DocumentVerificationService",
        targetNamespace = "http://services.university.com/",
        serviceName = "DocumentVerificationService"
)
public class DocumentVerificationServiceImpl implements DocumentVerificationService {

    @Override
    public boolean verifyDocuments(String studentId) {
        // Napriklad jednoducha logika ak je id studenta parne tak true, inak false
        return Integer.parseInt(studentId.substring(studentId.length() - 1)) % 2 == 0;
    }

    @Override
    public String getVerificationStatus(String studentId) {
        boolean verified = verifyDocuments(studentId);
        return verified ? "Dokumenty boli schválené pre študenta so Student Id: " + studentId
                : "Dokumenty neboli schválené pre študenta so Student Id: " + studentId;
    }
}
