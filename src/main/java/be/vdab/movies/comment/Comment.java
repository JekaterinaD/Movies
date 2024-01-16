package be.vdab.movies.comment;

import be.vdab.movies.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch( FetchMode.JOIN )
    @MapsId("movieId")
    @JoinColumn(name = "movieId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comments_movies"))
    @JsonIgnore
    private Movie movie;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "comment")
    private String comment;

    @Column(name = "moment")
    private LocalDateTime moment;

    public Long getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public Movie getMovie() {
        return movie;
    }
}
