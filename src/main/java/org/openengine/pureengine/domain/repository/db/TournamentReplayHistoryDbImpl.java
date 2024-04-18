package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.domain.model.TournamentReplayHistory;
import org.openengine.pureengine.domain.repository.WriteableRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TournamentReplayHistoryDbImpl implements WriteableRepository<TournamentReplayHistory> {

    private JdbcTemplate jdbcTemplate;

    public TournamentReplayHistoryDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TournamentReplayHistory tournamentReplayHistory) {
        String sql = "INSERT INTO " +
                "TournamentReplayHistory(" +
                "tournament_id, " +
                "team_name, " +
                "wins, " +
                "finals_participations, " +
                "historical_coefficient) " +
                "VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                tournamentReplayHistory.getTournamentId(),
                tournamentReplayHistory.getTeamName(),
                tournamentReplayHistory.getWinsNum(),
                tournamentReplayHistory.getFinalsParticipationsNum(),
                tournamentReplayHistory.getHistorialCoefficient());

    }

    public void update(int tournamentId, String teamName,
                       int wins, int finals_participations, int historical_coefficient) {
        String sql = "UPDATE TournamentReplayHistory " +
                "SET wins = ?, finals_participations = ?, historical_coefficient = ? " +
                "where tournament_id = ? and team_name = ?";

        jdbcTemplate.update(sql, wins, finals_participations, historical_coefficient, tournamentId, teamName);
    }

    public TournamentReplayHistory findByTournamentAndTeam(int tournamentId, String teamName) {
        String sql = "SELECT tournament_id, team_name, wins, finals_participations, historical_coefficient " +
                "from TournamentReplayHistory where tournament_id = ? and team_name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new TournamentReplayHistoryRowMapper(), tournamentId, teamName);
        } catch (EmptyResultDataAccessException emptyResultEx) {
            return null;
        }
    }

    public static class TournamentReplayHistoryRowMapper implements RowMapper<TournamentReplayHistory> {

        @Override
        public TournamentReplayHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TournamentReplayHistory(
                    rs.getInt("tournament_id"),
                    rs.getString("team_name"),
                    rs.getInt("wins"),
                    rs.getInt("finals_participations"),
                    rs.getInt("historical_coefficient"));
        }
    }
}
