package dto;

import lombok.*;

@Getter
@Setter
@Builder
public class MatchScoreDto {
    private String player1Name;
    private String player2Name;
    private String player1Score;
    private String player2Score;
    private byte player1Game;
    private byte player2Game;
    private byte player1Set;
    private byte player2Set;
    private String finish;
    private String buttonDisabled;
    private String uuid;

}
