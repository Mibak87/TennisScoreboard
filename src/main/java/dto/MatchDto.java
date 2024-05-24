package dto;

import entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MatchDto {
    private Player player1;
    private Player player2;
    private Player playerWin;
    private String player1Points = "0";
    private String player2Points = "0";
    private List<Integer> player1Set = new ArrayList<>(Arrays.asList(0,0,0));
    private List<Integer> player2Set = new ArrayList<>(Arrays.asList(0,0,0));
    private int currentSetNumber = 1;
    private boolean deuce;
    private boolean tieBreak;
    private boolean finished;

    public MatchDto(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
