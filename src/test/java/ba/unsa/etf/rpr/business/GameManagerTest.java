package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.GameDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManagerTest {

    private GameManager gameManager;
    private Game game;
    private GameDaoSQLImpl gameDaoSQLMock;
    private List<Game> games;

    private Game createGame(int id, int capacity, int sold, String competition, String opponent){
        Game game = new Game();
        game.setId(id);
        game.setCapacity(capacity);
        game.setSold(sold);
        game.setDate(LocalDate.now());
        game.setCompetition(competition);
        game.setOpponent(opponent);
        return game;
    }

    /**
     * This method is called before each test
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        gameManager = Mockito.mock(GameManager.class);
        game = new Game();
        game.setId(50);
        game.setCapacity(12056);
        game.setSold(0);
        LocalDate date = LocalDate.now();
        game.setDate(date);
        game.setCompetition("Premijer Liga BiH");
        game.setOpponent("Fk Velež Mostar");

        gameDaoSQLMock = Mockito.mock(GameDaoSQLImpl.class);
        games = new ArrayList<>();
        games.add(createGame(51, 5000, 5, "Kup BiH", "Fk Tuzla City"));
        games.add(createGame(52, 6000, 20, "Liga Prvaka", "Manchester City"));
        games.add(createGame(53, 12056, 1000, "Premijer Liga BiH", "Nk Široki Brijeg"));
    }

    @Test
    void validateCompetitionName() throws TicketException{
        String correctName = "Kup BiH";
        try {
            Mockito.doCallRealMethod().when(gameManager).validateCompetitionName(correctName);
        } catch (TicketException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "A";  //must be at least three characters
        Mockito.doCallRealMethod().when(gameManager).validateCompetitionName(incorrectNameShort);
        TicketException ticketException1 = Assertions.assertThrows(TicketException.class, () -> {
            gameManager.validateCompetitionName(incorrectNameShort);}, "Competition name must be between 3 and 45 characters");
        Assertions.assertEquals("Competition name must be between 3 and 45 characters", ticketException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(gameManager).validateCompetitionName(incorrectNameLong);
        TicketException ticketException2 = Assertions.assertThrows(TicketException.class, () -> {
            gameManager.validateCompetitionName(incorrectNameLong);}, "Competition name must be between 3 and 45 characters");
        Assertions.assertEquals("Competition name must be between 3 and 45 characters", ticketException2.getMessage());
    }
}
