package com.example.simpleapi.entities.country;

import com.example.simpleapi.entities.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<User> users;

    public Country(String name) {
        this.name = name;
    }
}
