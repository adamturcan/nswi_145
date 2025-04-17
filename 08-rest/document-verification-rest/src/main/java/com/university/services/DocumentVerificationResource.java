package com.university.services;

import com.university.model.DocumentVerification;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentVerificationResource {

    private static final Map<String, DocumentVerification> verifications = new ConcurrentHashMap<>();

    @GET
    @Path("/{studentId}")
    @Operation(summary = "Get verification status by student ID")
    public Response getVerificationStatus(@PathParam("studentId") String studentId) {
        DocumentVerification verification = verifications.get(studentId);
        if (verification == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No verification found for studentId: " + studentId)
                    .build();
        }
        return Response.ok(verification).build();
    }

    @POST
    @Operation(summary = "Verify documents for a student")
    public Response verifyDocument(DocumentVerification verification) {
        if (verification.getStudentId() == null || verification.getStudentId().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("studentId is required")
                    .build();
        }
        verifications.put(verification.getStudentId(), verification);
        return Response.status(Response.Status.CREATED).entity(verification).build();
    }

    @PUT
    @Path("/{studentId}")
    @Operation(summary = "Update document verification")
    public Response updateVerification(@PathParam("studentId") String studentId, DocumentVerification updated) {
        if (!verifications.containsKey(studentId)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existing verification for studentId: " + studentId)
                    .build();
        }
        updated.setStudentId(studentId);
        verifications.put(studentId, updated);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{studentId}")
    @Operation(summary = "Delete verification status")
    public Response deleteVerification(@PathParam("studentId") String studentId) {
        DocumentVerification removed = verifications.remove(studentId);
        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No verification to delete for studentId: " + studentId)
                    .build();
        }
        return Response.ok("Deleted verification for studentId: " + studentId).build();
    }
}
