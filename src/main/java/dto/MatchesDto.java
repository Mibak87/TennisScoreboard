package dto;

import entity.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MatchesDto {
    private int page;
    private int pageNumber;
    private String filterPlayer;
    private List<Match> matchesList;
    private String button1Hidden;
    private String button2Hidden;
    private String pageNumberLast;
    private String pageNumberNext;
}
