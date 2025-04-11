package com.university.services;

import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class DocumentVerificationApplication extends ResourceConfig {
    public DocumentVerificationApplication() {
        packages("com.university.services",
                "io.swagger.v3.jaxrs2.integration.jakarta");
        register(OpenApiResource.class);
    }
}
