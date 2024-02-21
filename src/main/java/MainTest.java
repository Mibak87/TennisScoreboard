import entity.Matches;
import entity.Players;
import org.hibernate.HibernateException;
import repository.MatchesRepository;
import repository.PlayersRepository;



public class MainTest {
    public static void main(String[] args) {
        Players player1 = new Players("Медведев");
        Players player2 = new Players("Сафин");
        Matches matches1 = new Matches(player1,player2,player1);
        try {
            new PlayersRepository().save(player1);
            new PlayersRepository().save(player2);
            //System.out.println(new PlayersRepository().findAll());
            new MatchesRepository().save(matches1);
            System.out.println(new MatchesRepository().findAll());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
