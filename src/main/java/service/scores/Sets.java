package service.scores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Sets {
    private List<Integer> player1Set;
    private List<Integer> player2Set;
    private int currentSetNumber;
}
