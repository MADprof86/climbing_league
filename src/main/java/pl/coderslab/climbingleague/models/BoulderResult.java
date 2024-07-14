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

    @ManyToOne
    @JoinColumn(name = "scores_id")
    private Scores scores;

    @ManyToOne
    @JoinColumn(name = "boulder_id")
    private Boulder boulder;

    private boolean top;
    private boolean zone;
    private int attempts;
}

