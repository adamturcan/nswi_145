package com.university.services;

import javax.jws.WebService;

@WebService(endpointInterface = "com.university.services.DocumentVerificationService")
public class DocumentVerificationServiceImpl implements DocumentVerificationService {

    @Override
    public boolean verifyDocuments(String studentId) {
        // Napriklad jednoducha logika ak je id studenta parne tak true, inak false
        return Integer.parseInt(studentId.substring(studentId.length() - 1)) % 2 == 0;
    }
}
