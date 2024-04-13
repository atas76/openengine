package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.repository.IdentifiableRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class TournamentRepositoryDbImpl implements IdentifiableRepository<Tournament> {

    private JdbcTemplate jdbcTemplate;
    private CompetitionRepositoryDbImpl competitionRepository;
    private TournamentParticipationRepositoryDbImpl tournamentParticipationRepository;
    private TeamRepositoryDbImpl teamRepository;

    public TournamentRepositoryDbImpl(JdbcTemplate jdbcTemplate,
                                      CompetitionRepositoryDbImpl competitionRepository,
                                      TournamentParticipationRepositoryDbImpl tournamentParticipationRepository,
                                      TeamRepositoryDbImpl teamRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.competitionRepository = competitionRepository;
        this.tournamentParticipationRepository = tournamentParticipationRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Tournament findById(int id) {
        String sql = "SELECT id, competition_id, year from Tournament where id = ?";
        Tournament tournament = jdbcTemplate.queryForObject(sql, new TournamentRowMapper(), id);
        assert tournament != null;
        return loadData(tournament);
    }

    @Override
    public Collection<Tournament> findAll() {
        String sql = "SELECT id, competition_id, year from Tournament";
        List<Tournament> tournaments = jdbcTemplate.query(sql, new TournamentRowMapper());
        return tournaments.stream().map(this::loadData).toList();
    }

    private Tournament loadData(Tournament tournament) {
        Competition competition = competitionRepository.findById(tournament.getCompetitionId());
        Collection<String> tournamentParticipations =
                tournamentParticipationRepository.findByReferenceId(tournament.getId());

        tournament.loadParticipants(tournamentParticipations.stream().map(teamRepository::findByName).toList());
        tournament.loadCompetition(competition);

        return tournament;
    }

    public static class TournamentRowMapper implements RowMapper<Tournament> {

        @Override
        public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Tournament(
                    rs.getInt("id"),
                    rs.getInt("competition_id"),
                    rs.getInt("year"));
        }
    }
}
