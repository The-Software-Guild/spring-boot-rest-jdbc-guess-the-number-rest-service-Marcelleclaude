package guessNumberGame.data;

import guessNumberGame.models.Game;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class GameInMemoryDao implements GameDao {

    private static final List<Game> games = new ArrayList<>();

    @Override
    public Game add(Game game) {

        int nextId = games.stream()
                .mapToInt(Game::getGame_id)
                .max()
                .orElse(0) + 1;

        game.setGame_id(nextId);
        games.add(game);
        return game;
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(games);
    }

    @Override
    public Game findById(int id) {
        return games.stream()
                .filter(i -> i.getGame_id() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Game game) {

        int index = 0;
        while (index < games.size()
                && games.get(index).getGame_id() != game.getGame_id()) {
            index++;
        }

        if (index < games.size()) {
            games.set(index, game);
        }
        return index < games.size();
    }

    @Override
    public boolean deleteById(int id) {
        return games.removeIf(i -> i.getGame_id() == id);
    }

}
