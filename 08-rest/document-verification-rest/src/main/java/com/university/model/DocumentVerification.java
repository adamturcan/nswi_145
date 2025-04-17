package com.university.model;

public class DocumentVerification {
    private String studentId;
    private boolean verified;
    private String comment;

    public DocumentVerification() {}

    public DocumentVerification(String studentId, boolean verified, String comment) {
        this.studentId = studentId;
        this.verified = verified;
        this.comment = comment;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}