package com.university.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.*;

@Path("/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentVerificationResource {

    @GET
    @Path("/{studentId}")
    @Operation(summary = "Get verification status by student ID")
    public Response getVerificationStatus(@PathParam("studentId") String studentId) {
        boolean verified = Integer.parseInt(studentId.substring(studentId.length() - 1)) % 2 == 0;
        String message = verified ? "Documents approved" : "Documents rejected";
        return Response.ok(message).build();
    }

    @POST
    @Operation(summary = "Verify documents for a student")
    public Response verifyDocument(String studentId) {
        return Response.ok("Verified documents for studentId: " + studentId).build();
    }

    @PUT
    @Path("/{studentId}")
    @Operation(summary = "Update document verification")
    public Response updateVerification(@PathParam("studentId") String studentId) {
        return Response.ok("Updated verification status for studentId: " + studentId).build();
    }

    @DELETE
    @Path("/{studentId}")
    @Operation(summary = "Delete verification status")
    public Response deleteVerification(@PathParam("studentId") String studentId) {
        return Response.ok("Deleted verification for studentId: " + studentId).build();
    }
}
