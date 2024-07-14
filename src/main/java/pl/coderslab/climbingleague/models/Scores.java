package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scores implements ScoreCalculator{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "scores")
    private List<BoulderResult> boulderResults;

    private Integer currentRanking;


    @Override
    public Double getTotalScore() {

        return boulderResults.stream()
                    .mapToDouble(result -> (result.isTop() ? result.getBoulder().getPointsForTop() : 0) +
                            (result.isZone() ? result.getBoulder().getPointsForZone() : 0))
                    .sum();
    }

}
