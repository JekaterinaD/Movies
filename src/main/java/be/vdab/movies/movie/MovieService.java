package be.vdab.movies.movie;

import be.vdab.movies.Actor;
import be.vdab.movies.DTO.DirectorDTO;
import be.vdab.movies.DTO.MovieDetailDTO;
import be.vdab.movies.DTO.RoleDTO;
import be.vdab.movies.distributor.Distributor;
import be.vdab.movies.distributor.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import be.vdab.movies.Genre;
import be.vdab.movies.Director;
import be.vdab.movies.Role;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final DistributorRepository distributorRepository; // Add this line

    @Autowired
    public MovieService(MovieRepository movieRepository, DistributorRepository distributorRepository) {
        this.movieRepository = movieRepository;
        this.distributorRepository = distributorRepository; // Set the distributorRepository
    }

    public Optional<Movie> findById(long id) {
        return movieRepository.findById(id);
    }

    public List<Integer> findDistinctYears() {
        return movieRepository.findDistinctYears();
    }
    public List<Movie> findMoviesByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAllWithDistributors();
    }

    public MovieDetailDTO getMovieDetails(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(NotFoundException::new);

        List<String> sortedGenres = movie.getGenres().stream()
                .map(Genre::getName)
                .sorted()
                .collect(Collectors.toList());

        List<DirectorDTO> sortedDirectors = movie.getDirectors().stream()
                .sorted(Comparator.comparing(Director::getFirstname)
                        .thenComparing(Director::getLastname))
                .map(director -> new DirectorDTO(director.getFirstname() + " " + director.getLastname()))
                .collect(Collectors.toList());


        List<RoleDTO> sortedRoles = movie.getRoles().stream()
                .sorted(Comparator.comparing(Role::getName))
                .map(role -> {
                    Actor actor = role.getActor();
                    return new RoleDTO(role.getName(), actor, actor.getGender());
                })
                .collect(Collectors.toList());

        String distributorName = movie.getDistributor() != null ? movie.getDistributor().getName() : "N/A";

       return new MovieDetailDTO(movie.getName(), movie.getYear(), movie.getRanking(),
                sortedGenres, sortedDirectors, sortedRoles, distributorName);
    }

    @Transactional
    public void updateMovieRanking(Long movieId, BigDecimal newRanking) {
        movieRepository.updateMovieRanking(movieId, newRanking);
    }
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }
    public List<Movie> getMoviesByDistributor(Long distributor_id) {
        return movieRepository.findByDistributorId(distributor_id);
    }
    public Distributor getDistributorForMovie(Long movieId) {
        Optional<Distributor> distributorOptional = movieRepository.findDistributorByMovieId(movieId);

        if (distributorOptional.isPresent()) {
            return distributorOptional.get();
        } else {
            throw new MovieNotFoundException("Movie with ID " + movieId + " not found");
        }
    }
}







