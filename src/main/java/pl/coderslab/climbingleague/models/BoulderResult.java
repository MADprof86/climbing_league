package pl.coderslab.climbingleague.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoulderResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scores_id")
    private Scores scores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boulder_id")
    private Boulder boulder;

    private Double score;
    private Boolean top;
    private Boolean zone;
    private Integer attempts;
    private Boolean flash;
}

