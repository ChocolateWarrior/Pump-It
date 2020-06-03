package com.pumpit.app.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.pumpit.app.data.local.DatabaseConstants;
import com.pumpit.app.data.local.converter.AuthoritiesConverter;
import com.pumpit.app.data.local.converter.LocalDateConverter;
import com.pumpit.app.data.local.converter.SexConverter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = false)
    private long uid = DatabaseConstants.CURRENT_USER_ID;

    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String profilePicturePath;

    @TypeConverters({LocalDateConverter.class})
    private LocalDate dateOfBirth;
    @TypeConverters({SexConverter.class})
    private Sex sex;
    @TypeConverters({AuthoritiesConverter.class})
    private Set<Authority> authorities;

    public User(long id, String firstName, String lastName, String username, String profilePicturePath, LocalDate dateOfBirth, Sex sex, Set<Authority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.profilePicturePath = profilePicturePath;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.authorities = authorities;
    }

    public User() {
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return uid == user.uid &&
                id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(profilePicturePath, user.profilePicturePath) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                sex == user.sex &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, id, firstName, lastName, username, profilePicturePath, dateOfBirth, sex, authorities);
    }
}
