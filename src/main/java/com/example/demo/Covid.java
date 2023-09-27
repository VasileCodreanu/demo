package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Covid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int deaths;
    private int active;
    private int recovered;
    private String country;

    @Override
    public String toString() {
        return "Covid{" +
                "id=" + id +
                ", deaths=" + deaths +
                ", active=" + active +
                ", recovered=" + recovered +
                ", country='" + country + '\'' +
                '}';
    }
}
