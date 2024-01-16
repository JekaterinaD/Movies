package be.vdab.movies;
import be.vdab.movies.movie.Movie;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "directors")
public class Director {
    @Id
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;


    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies;
    public String getFullName() {
        return firstname + " " + lastname;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
