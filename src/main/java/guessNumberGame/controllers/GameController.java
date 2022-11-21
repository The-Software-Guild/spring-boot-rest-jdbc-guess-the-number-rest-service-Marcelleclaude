package guessNumberGame.controllers;

import guessNumberGame.Service.GameService;
import guessNumberGame.data.GameDao;
import guessNumberGame.data.RoundDao;
import guessNumberGame.models.Game;
import java.util.List;

import guessNumberGame.models.Round;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class GameController {


    private final GameDao gameDao;
    private final RoundDao roundDao;


    public GameController(GameDao gameDao, RoundDao roundDao, GameDao gameDao1, RoundDao roundDao1) {
        this.gameDao = gameDao1;
        //implement
        this.roundDao = roundDao1;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game create() {
         //implement create gameService object and game object
        GameService gameService=new GameService();
        Game game = new Game();
        //add to database
       gameDao.add(game);
        //getGame will hide answer before returning it to the user
        return gameService.getGames(game);
    }


    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guessNumber(@RequestBody Round body) {
       //implement
        Game game= gameDao.findById(body.getGameId());
        GameService gameService= new GameService();
        Round round= gameService.guessNumber(game, body.getGuess(),gameDao);
        return roundDao.add(round);

    }

    @GetMapping("/game")
    public List<Game> all() {
      //implement
        List<Game> games =gameDao.getAll();
        GameService gameService =new GameService();
        gameService.getAllGames(games);
        return games;
    }

    @GetMapping("game/{id}")
    public Game getGameById(@PathVariable int id) {
        //implement
        Game game=gameDao.findById(id);
        GameService gameService=new GameService();
        return gameService.getGames(game);
    }


    @GetMapping("rounds/{gameId}")
    //implement
    public List<Round> getGameRounds (@PathVariable int gameId){
        return roundDao.getAllOfGame(gameId);
    }

}