package pl.coderslab.climbingleague.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Boulder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;
    @Size(min = 2)
    @Column(nullable = false)
    private String name;
}
