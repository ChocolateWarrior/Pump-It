package com.pumpit.app.data.local.entity;

import androidx.room.Entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(tableName = "trainers")
public class Trainer extends User {
    private String company;
    private int clientCount;

    public Trainer(long id, String firstName, String lastName, String username, String profilePicturePath, LocalDate dateOfBirth, Sex sex, Set<Authority> authorities, String company, int clientCount) {
        super(id, firstName, lastName, username, profilePicturePath, dateOfBirth, sex, authorities);
        this.company = company;
        this.clientCount = clientCount;
    }

    public Trainer() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;
        if (!super.equals(o)) return false;
        Trainer trainer = (Trainer) o;
        return clientCount == trainer.clientCount &&
                Objects.equals(company, trainer.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, clientCount);
    }
}
