package be.vdab.movies.movie;

import java.math.BigDecimal;

public class MovieDTO {
    private Long id;
    private String name;
    private int year;
    private BigDecimal ranking;
    private String distributorName;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.year = movie.getYear();
        this.ranking = movie.getRanking();
        this.distributorName = movie.getDistributor() != null ? movie.getDistributor().getName() : "N/A";

    }
        public Long getId () {
            return id;
        }

        public String getName () {
            return name;
        }

        public int getYear () {
            return year;
        }

        public BigDecimal getRanking () {
            return ranking;
        }

        public String getDistributorName () {
            return distributorName;
        }
        public void setId (Long id){
            this.id = id;
        }

        public void setName (String name){
            this.name = name;
        }

        public void setYear ( int year){
            this.year = year;
        }

        public void setRanking (BigDecimal ranking){
            this.ranking = ranking;
        }

        public void setDistributorName (String distributorName){
            this.distributorName = distributorName;
        }
    }



