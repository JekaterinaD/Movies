package be.vdab.movies.movie;

import java.math.BigDecimal;

public class RankingUpdateDTO {

    private BigDecimal newRanking;
    public RankingUpdateDTO() {
    }
    public RankingUpdateDTO(BigDecimal newRanking) {
        this.newRanking = newRanking;
    }

    public BigDecimal getNewRanking() {
        return newRanking;
    }

    public void setNewRanking(BigDecimal newRanking) {
        this.newRanking = newRanking;
    }
}
