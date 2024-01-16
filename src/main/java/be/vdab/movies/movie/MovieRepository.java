package be.vdab.movies.movie;

import be.vdab.movies.distributor.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT m.year FROM Movie m ORDER BY m.year ASC")
    List<Integer> findDistinctYears();
    List<Movie> findByDistributorId(Long distributor_id);
    Optional<Movie> findById(Long id);
    @Query("SELECT m FROM Movie m JOIN FETCH m.distributor")
    List<Movie> findAllWithDistributors();
    List<Movie> findMoviesByYear(int year);
    @Query("SELECT m, d.name FROM Movie m LEFT JOIN m.distributor d")
    List<Object[]> findMoviesWithDistributorNames();
    List<Movie> findByYear(int year);

    @Query("SELECT m FROM Movie m JOIN FETCH m.distributor WHERE m.id = :id")
    Optional<Movie> findByIdWithDistributors(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.ranking = :newRanking WHERE m.id = :movieId")
    void updateMovieRanking(@Param("movieId") Long movieId, @Param("newRanking") BigDecimal newRanking);
    @Query("SELECT m.distributor FROM Movie m WHERE m.id = :movieId")
    Optional<Distributor> findDistributorByMovieId(@Param("movieId") Long movieId);



}

