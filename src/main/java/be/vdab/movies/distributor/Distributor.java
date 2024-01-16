package be.vdab.movies.distributor;

import be.vdab.movies.movie.Movie;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "distributors")
public class Distributor {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "distributor")
    private Set<Movie> movies;
    public Distributor() {
    }

    public Distributor(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
