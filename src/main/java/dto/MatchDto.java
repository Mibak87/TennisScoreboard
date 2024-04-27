package dto;

import entity.Players;
import lombok.Getter;
import lombok.Setter;

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
    private boolean deuce;
    private boolean tieBreak;
    private boolean finished;
}