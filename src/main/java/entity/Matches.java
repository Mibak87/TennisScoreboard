package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Matches")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "Player1")
    private Players player1;
    @ManyToOne
    @JoinColumn(name = "Player2")
    private Players player2;
    @ManyToOne
    @JoinColumn(name = "Winner")
    private Players winner;
}
