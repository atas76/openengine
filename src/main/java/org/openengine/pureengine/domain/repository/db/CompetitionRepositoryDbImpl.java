package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.domain.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class CompetitionRepositoryDbImpl implements org.openengine.pureengine.domain.repository.Repository<Competition> {

    private final JdbcTemplate jdbcTemplate;
    private final CompetitionRoundRepositoryDbImpl competitionRoundRepository;

    @Autowired
    public CompetitionRepositoryDbImpl(JdbcTemplate jdbcTemplate,
                                       CompetitionRoundRepositoryDbImpl competitionRoundRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.competitionRoundRepository = competitionRoundRepository;
    }

    @Override
    public Competition findById(int id) {
        String sql = "SELECT * FROM Competition WHERE id = ?";
        Competition competition = jdbcTemplate.queryForObject(sql, new CompetitionRowMapper(), id);
        assert competition != null;
        return loadData(competition);
    }

    @Override
    public Collection<Competition> findByReferenceId(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Competition findByName(String name) {
        String sql = "SELECT * FROM Competition WHERE name = ?";
        Competition competition = jdbcTemplate.queryForObject(sql, new CompetitionRowMapper(), name);
        assert competition != null;
        return loadData(competition);
    }

    @Override
    public Collection<Competition> findAll() {
        String sql = "SELECT * FROM Competition";
        List<Competition> competitions = jdbcTemplate.query(sql, new CompetitionRowMapper());
        return competitions.stream().map(this::loadData).toList();
    }

    @Override
    public void loadData() {
        throw new IllegalArgumentException("Not applicable");
    }

    private Competition loadData(Competition competition) {
        competition.setRoundDetails(new ArrayList<>(competitionRoundRepository.findByReferenceId(competition.getId())));
        return competition;
    }

    public static class CompetitionRowMapper implements RowMapper<Competition> {

        @Override
        public Competition mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Competition(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("country"),
                    rs.getString("country_demonym"));
        }
    }
}
