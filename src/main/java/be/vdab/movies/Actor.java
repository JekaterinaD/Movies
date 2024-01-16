package be.vdab.movies;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "actor")
    private Set<Role> roles;
    public String getActorFullName() {
        return getFirstName() + " " + getLastName();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
