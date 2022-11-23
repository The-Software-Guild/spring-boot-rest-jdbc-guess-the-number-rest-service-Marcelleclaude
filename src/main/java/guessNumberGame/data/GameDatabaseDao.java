package guessNumberGame.data;

import guessNumberGame.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GameDatabaseDao implements GameDao {
     private final JdbcTemplate jdbcTemplate;
//    public GameDatabaseDao (Connection connection) {
//        super(connection);
//    }

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {

        final String sql = "INSERT INTO game(answer, isFinished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();


        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isFinished());
            return statement;

        }, keyHolder);

        game.setGame_id(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAll() {
       //implement
        //retrieving from the data base
        final String sql = "SELECT game_id, answer, isFinished FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
        }


    @Override
    public Game findById(int game_id){
            //implement
//            final String Get_One="SELECT game_id, answer, isFinished WHERE game_id=?";
          //  Game game=new Game();
//            try (PreparedStatement statement =this.connection.prepareStatement(Get_One);)
            final String sql = "SELECT game_id, answer, isFinished" +
                    " FROM game WHERE game_id = ? ";

            return jdbcTemplate.queryForObject(sql, new GameMapper(), game_id);
        }

    @Override
    public boolean update(Game game) {

         //implement
            final String sql="UPDATE game SET"  + " answer = ?, " + " game_id =?, "+ " isFinished=? WHERE game_id=?";
            return jdbcTemplate.update(sql, game.getAnswer(),game.getGame_id(), game.isFinished(),game.getGame_id())>0;}

    @Override
    public boolean deleteById(int game_id) {
       //implement
            final String sql="DELETE FROM game Where game_id=?;";
            return jdbcTemplate.update(sql,game_id)>0;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGame_id(rs.getInt("game_id"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("isFinished"));
            return game;
        }
    }
}
