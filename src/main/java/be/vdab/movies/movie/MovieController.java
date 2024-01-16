package be.vdab.movies.movie;


import be.vdab.movies.DTO.MovieDetailDTO;
import be.vdab.movies.distributor.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("years")
    public ResponseEntity<List<Integer>> findDistinctYears() {
        return ResponseEntity.ok(movieService.findDistinctYears());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<MovieDTO>> findMoviesByYear(@PathVariable int year) {
        List<MovieDTO> movies = movieService.findMoviesByYear(year)
                .stream()
                .map( (Movie movie) -> new MovieDTO( movie ) )
                .collect(Collectors.toList());
        return ResponseEntity.ok(movies);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/ok/movies")
    public ResponseEntity<List<Movie>> getAllMoviesOk() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

     @GetMapping("/{id}")
     public ResponseEntity<MovieDetailDTO> getMovieDetails(@PathVariable Long id) {
         MovieDetailDTO movieDetails = movieService.getMovieDetails(id);
         return ResponseEntity.ok(movieDetails);
     }
    @PostMapping("/updateRanking/{movieId}")
    public ResponseEntity<?> updateMovieRanking(
            @PathVariable Long movieId,
            @RequestBody RankingUpdateDTO rankingUpdateDTO) {
        BigDecimal newRanking = rankingUpdateDTO.getNewRanking();

        try {
            movieService.updateMovieRanking(movieId, newRanking);
            return ResponseEntity.ok("Ranking updated successfully for movie ID " + movieId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating movie ranking");
        }

}
    @GetMapping("/by-distributor/{distributorId}")
    public ResponseEntity<List<Movie>> getMoviesByDistributor(@PathVariable Long distributorId) {
        List<Movie> movies = movieService.getMoviesByDistributor(distributorId);
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/{movieId}/distributor")
    public ResponseEntity<Distributor> getDistributorForMovie(@PathVariable Long movieId) {
        Distributor distributor = movieService.getDistributorForMovie(movieId);
        return ResponseEntity.ok(distributor);
    }
   }

