package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.domain.model.TournamentHistory;
import org.openengine.pureengine.domain.repository.WriteableRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class TournamentHistoryRepositoryDbImpl implements WriteableRepository<TournamentHistory> {

    private JdbcTemplate jdbcTemplate;

    public TournamentHistoryRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TournamentHistory tournamentHistory) {
        String sql = "INSERT INTO TournamentHistory(tournament_id, winner, runner_up) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql,
                tournamentHistory.getTournamentId(), tournamentHistory.getWinner(), tournamentHistory.getRunnerUp());
    }
}
