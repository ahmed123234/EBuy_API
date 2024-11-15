package com.example.simpleapi.entities.User;

import com.example.simpleapi.entities.country.Country;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;
    private String firstname;
    private String lastname;

    private String username;

    private String mobile;

    private boolean enabled = true;

    private String password;

    private String email;

//    private LocalDateTime JoinDate;

    private Date JoinDate;

    //    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne()
    @JoinColumn(name = "country-id", referencedColumnName = "country_id")
    private Country country;


    //  TODO: To be edited later on

//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    public User(String username, String password, String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
//
//    public User(String username, String password, String email, UserRole role) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.role = role;
//    }


}
