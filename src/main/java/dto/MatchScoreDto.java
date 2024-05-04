package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class MatchScoreDto {
    private String player1Name;
    private String player2Name;
    private String player1Points;
    private String player2Points;
    private List<Integer> player1Set;
    private List<Integer> player2Set;
    private String finish;
    private String buttonDisabled;
    private String uuid;

}
