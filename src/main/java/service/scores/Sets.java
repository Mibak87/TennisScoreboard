package service.scores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Sets {
    private List<Integer> player1Set;
    private List<Integer> player2Set;
    private int currentSetNumber;

    public Sets(List<Integer> player1Set, List<Integer> player2Set, int currentSetNumber) {
        this.player1Set = player1Set;
        this.player2Set = player2Set;
        this.currentSetNumber = currentSetNumber;
    }
}
