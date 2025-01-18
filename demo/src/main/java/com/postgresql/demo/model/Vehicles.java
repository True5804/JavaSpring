package com.postgresql.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseplate;
    private float mileage;
    private String owner_name;
    private String owner_phone;
    private LocalDateTime build_date;
    private LocalDateTime modified_date;

    // Default constructor
    public Vehicles() {}

    // All-args constructor
    public Vehicles(String licenseplate, float mileage, String owner_name,
                    String owner_phone, LocalDateTime build_date, LocalDateTime modified_date) {
        this.licenseplate = licenseplate;
        this.mileage = mileage;
        this.owner_name = owner_name;
        this.owner_phone = owner_phone;
        this.build_date = build_date;
        this.modified_date = modified_date;
    }

    // Getters and Setters for proper JSON serialization
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public LocalDateTime getBuild_date() {
        return build_date;
    }

    public void setBuild_date(LocalDateTime build_date) {
        this.build_date = build_date;
    }

    public LocalDateTime getModified_date() {
        return modified_date;
    }

    public void setModified_date(LocalDateTime modified_date) {
        this.modified_date = modified_date;
    }
}
