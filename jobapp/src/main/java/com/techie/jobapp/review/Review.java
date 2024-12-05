package com.techie.jobapp.review;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techie.jobapp.company.Company;
import jakarta.persistence.*;

@Entity
//@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;


    @JsonIgnore
    @ManyToOne
    private Company company;

    public Review(Long id, String text, Company company) {
        this.id = id;
        this.text = text;
        this.company = company;
    }

    public Review() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
