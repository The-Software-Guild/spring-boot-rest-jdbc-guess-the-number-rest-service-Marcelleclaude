package guessNumberGame.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Round {
    private int round_id;
    int game_id;
    Timestamp guess_time;
    String guess;

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Timestamp getGuess_time() {
        return guess_time;
    }

    public void setGuess_time(Timestamp guess_time) {
        this.guess_time = guess_time;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    String result;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return round_id == round.round_id && game_id == round.game_id && Objects.equals(guess_time, round.guess_time) && Objects.equals(guess, round.guess) && Objects.equals(result, round.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round_id, game_id, guess_time, guess, result);
    }
}
