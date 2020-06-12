package com.pumpit.app.data.remote.util;

import java.io.IOException;

public class NetworkUnavailableException extends IOException {
    public NetworkUnavailableException(final String message) {
        super(message);
    }
}
