package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class TournamentRepositoryDbImpl implements org.openengine.pureengine.domain.repository.Repository<Tournament> {

    private JdbcTemplate jdbcTemplate;
    private CompetitionRepositoryDbImpl competitionRepository;

    public TournamentRepositoryDbImpl(JdbcTemplate jdbcTemplate, CompetitionRepositoryDbImpl competitionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Tournament findById(int id) {
        String sql = "SELECT competition_id, year from Tournament where id = ?";
        Tournament tournament = jdbcTemplate.queryForObject(sql, new TournamentRowMapper(), id);
        assert tournament != null;
        return loadData(tournament);
    }

    @Override
    public Collection<Tournament> findByReferenceId(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Tournament findByName(String name) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<Tournament> findAll() {
        String sql = "SELECT competition_id, year from Tournament";
        List<Tournament> tournaments = jdbcTemplate.query(sql, new TournamentRowMapper());
        return tournaments.stream().map(this::loadData).toList();
    }

    @Override
    public void loadData() {
        throw new IllegalArgumentException("Not applicable");
    }

    private Tournament loadData(Tournament tournament) {
        Competition competition = competitionRepository.findById(tournament.getCompetitionId());
        tournament.setCompetition(competition);
        // TODO load tournament participants
        return tournament;
    }

    public static class TournamentRowMapper implements RowMapper<Tournament> {

        @Override
        public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Tournament(rs.getInt("competition_id"), rs.getInt("year"));
        }
    }
}
