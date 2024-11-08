package com.hexaware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Executive {

    @Id
    private int id;
    private String name;
    private double salary;

    @OneToOne
    private User user;

    public Executive(int id, String name, double salary, User user) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.user = user;
    }

    public Executive() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Executive{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", user=" + user +
                '}';
    }
}
