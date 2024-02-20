package entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public Matches(Players player1, Players player2, Players winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
