package com.example.week3weekend.model;

public class Employee {
    private int id;
    private String name;
    private String birthDate;
    private double wage;
    private String hireDate;
    private String imageUrl;

    public Employee() {
    }

    public Employee(int id, String name, String birthDate, double wage, String hireDate, String imageUrl) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.wage = wage;
        this.hireDate = hireDate;
        this.imageUrl = imageUrl;
    }

    // Employee with no ID
    public Employee(String name, String birthDate, double wage, String hireDate, String imageUrl) {
        this.name = name;
        this.birthDate = birthDate;
        this.wage = wage;
        this.hireDate = hireDate;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
