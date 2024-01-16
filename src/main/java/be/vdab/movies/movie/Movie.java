package be.vdab.movies.movie;

import be.vdab.movies.comment.Comment;
import be.vdab.movies.Director;
import be.vdab.movies.distributor.Distributor;
import be.vdab.movies.Genre;
import be.vdab.movies.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch( FetchMode.JOIN )
    @MapsId("distributor_id")
    @JoinColumn(name = "distributor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_movies_distributors"))
    private Distributor distributor;
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    private int year;

    public Distributor getDistributor() {
        return distributor;
    }

    @Column(name = "ranking")
    private BigDecimal ranking;

    @ManyToMany
    @JoinTable(
            name = "moviesDirectors",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "directorId")
    )
    private Set<Director> directors;

    @OneToMany(mappedBy = "movie")
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "moviesGenres",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<Comment> comments;

    public long getId() {
        return id;
    }
    public Distributor getDistributor_id() {
        return distributor;
    }

    public String getName() {
        return name;
    }
    public Set<Director> getDirectors() {
        return directors;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Movie() {}

    public void setRanking(BigDecimal newRanking) {
        this.ranking = newRanking;
    }
}
