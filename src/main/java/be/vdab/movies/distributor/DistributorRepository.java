package be.vdab.movies.distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    @Query("SELECT DISTINCT d FROM Distributor d JOIN FETCH d.movies m WHERE m.id = :movieId")
    List<Distributor> findDistributorsByMovieId(@Param("movieId") Long movieId);
}

