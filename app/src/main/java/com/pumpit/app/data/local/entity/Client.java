package com.pumpit.app.data.local.entity;

import androidx.room.Entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(tableName = "clients")
public class Client extends User {
    private double weight;
    private int height;
    private String trainerFirstName;
    private String trainerLastName;

    public Client(long id, String firstName, String lastName, String username, String profilePicturePath, LocalDate dateOfBirth, Sex sex, Set<Authority> authorities, double weight, int height, String trainerFirstName, String trainerLastName) {
        super(id, firstName, lastName, username, profilePicturePath, dateOfBirth, sex, authorities);
        this.weight = weight;
        this.height = height;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
    }

    public Client() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public void setTrainerFirstName(String trainerFirstName) {
        this.trainerFirstName = trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Double.compare(client.weight, weight) == 0 &&
                height == client.height &&
                Objects.equals(trainerFirstName, client.trainerFirstName) &&
                Objects.equals(trainerLastName, client.trainerLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight, height, trainerFirstName, trainerLastName);
    }
}
