package be.vdab.movies.distributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {
    private final DistributorRepository distributorRepository;


    @Autowired
    public DistributorService(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    public List<Distributor> findAllDistributors() {
        return distributorRepository.findAll();
    }

    public Optional<Distributor> findDistributorById(Long id) {
        return distributorRepository.findById(id);
    }
    public List<Distributor> findDistributorsByMovieId(Long movieId) {
        return distributorRepository.findDistributorsByMovieId(movieId);
    }
}
