package com.pumpit.app.data.remote.response;

import com.pumpit.app.data.local.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String isSuccessful;
    private String message;
    private User user;
}
