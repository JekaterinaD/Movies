package be.vdab.movies;

import be.vdab.movies.movie.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    private long id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
