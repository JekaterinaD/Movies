package be.vdab.movies;

import be.vdab.movies.movie.Movie;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch( FetchMode.JOIN )
    @MapsId("actorId")
    @JoinColumn(name = "actorId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_roles_actors"))
    private Actor actor;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch( FetchMode.JOIN )
    @MapsId("movieId")
    @JoinColumn(name = "movieId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_roles_movies"))
    private Movie movie;
    @Column(name = "name")
    private String name;

    public String getActorFullName() {
        if (actor != null) {
            return actor.getFirstName() + " " + actor.getLastName();
        }
        return null;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getName() {
        return name;
    }

}
