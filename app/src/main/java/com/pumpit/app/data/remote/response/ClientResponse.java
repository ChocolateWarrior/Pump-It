package com.pumpit.app.data.remote.response;

import com.pumpit.app.data.local.entity.Authority;
import com.pumpit.app.data.local.entity.Sex;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String profilePicturePath;
    private Set<Authority> authorities;
    private double weight;
    private int height;
    private String trainerFirstName;
    private String trainerLastName;
}
