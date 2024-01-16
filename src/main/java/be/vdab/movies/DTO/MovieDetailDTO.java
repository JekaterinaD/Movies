package be.vdab.movies.DTO;

import java.math.BigDecimal;
import java.util.List;

public class MovieDetailDTO {
    private final String title;
    private final int year;
    private final BigDecimal ranking;
    private final List<String> genres;
    private final List<DirectorDTO> directors;
    private final List<RoleDTO> roles;
    private final String distributorName;

    public MovieDetailDTO(String title, int year, BigDecimal ranking, List<String> genres, List<DirectorDTO> directors, List<RoleDTO> roles, String distributorName) {
        this.title = title;
        this.year = year;
        this.ranking = ranking;
        this.genres = genres;
        this.directors = directors;
        this.roles = roles;
        this.distributorName = distributorName;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<DirectorDTO> getDirectors() {
        return directors;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public String getDistributorName() {
        return distributorName;
    }
}