package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private Double score;
    private Integer top;
    private Integer zone;
    private Integer attempts;
    private Integer flashAttempts;
    private Double totalPoints;
}
