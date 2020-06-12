package com.pumpit.app.data.remote.response;

import com.pumpit.app.data.local.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private boolean successful;
    private User user;
}
