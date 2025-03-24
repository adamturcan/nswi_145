package com.university.services;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import javax.security.auth.callback.*;

public class ClientPasswordCallback implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if ("testuser".equals(pc.getIdentifier())) {
            pc.setPassword("heslo123");
        }
    }
}
