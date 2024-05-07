package service.scores;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Scores {
    private Points points;
    private Sets sets;
    private boolean deuce;
    private boolean tieBreak;
    private boolean finished;

    public Scores(Points points, Sets sets) {
        this.points = points;
        this.sets = sets;
    }
}
