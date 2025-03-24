package com.university.services;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import javax.security.auth.callback.*;

import java.io.IOException;

public class ServerPasswordCallback implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if ("testuser".equals(pc.getIdentifier())) {
            pc.setPassword("heslo123");
        } else {
            throw new UnsupportedCallbackException(pc, "Invalid username");
        }
    }
}
