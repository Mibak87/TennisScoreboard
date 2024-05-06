package dto;

import entity.Players;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MatchDto {
    private Players player1;
    private Players player2;
    private Players playerWin;
    private String player1Points;
    private String player2Points;
    private List<Integer> player1Set;
    private List<Integer> player2Set;
    private int currentSetNumber;
    private boolean deuce;
    private boolean tieBreak;
    private boolean finished;

    public MatchDto(Players player1, Players player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Points = "0";
        player2Points = "0";
        player1Set = new ArrayList<>(Arrays.asList(0,0,0));
        player2Set = new ArrayList<>(Arrays.asList(0,0,0));
        currentSetNumber = 1;
    }
}
