package be.vdab.movies.distributor;
import be.vdab.movies.movie.Movie;
import be.vdab.movies.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distributors")
public class DistributorController {

    private final DistributorService distributorService;

    @Autowired
    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @GetMapping
    public ResponseEntity<List<Distributor>> getAllDistributors() {
        List<Distributor> distributors = distributorService.findAllDistributors();
        return ResponseEntity.ok(distributors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Distributor> getDistributorById(@PathVariable Long id) {
        return distributorService.findDistributorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/by-movie/{movieId}")
    public ResponseEntity<List<Distributor>> getDistributorsByMovieId(@PathVariable Long movieId) {
        List<Distributor> distributors = distributorService.findDistributorsByMovieId(movieId);
        return ResponseEntity.ok(distributors);
    }
}
