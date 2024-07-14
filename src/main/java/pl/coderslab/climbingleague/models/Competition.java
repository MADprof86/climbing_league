package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String location;
    private String objectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetitionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScoreSystem scoreSystem;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Scores> scores;

   @Transient
   public int getNumberOfParticipants(){
       return scores != null ? scores.size() : 0;
   }

    public enum CompetitionType {
        ELIMINATIONS, FINALS
    }

    public enum ScoreSystem {
        POINTS, TOP_ZONE, FLASH, TOP_BONUS
    }
}
