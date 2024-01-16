package be.vdab.movies.movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class MovieNietGevondenException extends RuntimeException {
    public MovieNietGevondenException() {
        super("Movie not found.");
    }
}
