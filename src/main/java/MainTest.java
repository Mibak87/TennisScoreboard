import entity.Players;
import org.hibernate.HibernateException;
import repository.PlayersRepository;



public class MainTest {
    public static void main(String[] args) {
        Players player1 = new Players("Медведев");
        Players player2 = new Players("Сафин");
        try {
            new PlayersRepository().save(player1);
            new PlayersRepository().save(player2);
            System.out.println(new PlayersRepository().findById(1).get());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
