package entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Players")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Players (String name) {
        this.name = name;
    }
}
