package dto;

import entity.Matches;
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
    private List<Matches> matchesList;
    private String button1Hidden;
    private String button2Hidden;
    private String pageNumberLast;
    private String pageNumberNext;
}
