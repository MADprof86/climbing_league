package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<Competition> competitions;
    @PreRemove
    private void preRemove(){
        for(Competition competition : competitions)
        {
            competition.setLeague(null);
        }
    }
}
