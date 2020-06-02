package com.pumpit.app.data.remote.response;

import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.Sex;

import java.time.LocalDate;
import java.util.Set;

public class TrainerResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String profilePicturePath;
    private Set<Authority> authorities;
    private String company;
    private int clientCount;
}
