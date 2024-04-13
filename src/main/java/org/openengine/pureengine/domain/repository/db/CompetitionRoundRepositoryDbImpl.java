package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.TieBreaker;
import org.openengine.pureengine.domain.model.CompetitionRound;
import org.openengine.pureengine.domain.repository.ReferencableRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class CompetitionRoundRepositoryDbImpl implements ReferencableRepository<CompetitionRound> {

    private final JdbcTemplate jdbcTemplate;

    public CompetitionRoundRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<CompetitionRound> findByReferenceId(int id) {
        String sql = "select name, home_advantage, tie_breaker from CompetitionRound where competition_id = ?";
        return jdbcTemplate.query(sql, new CompetitionRoundRowMapper(), id);
    }

    @Override
    public CompetitionRound findByName(String name) {
        throw new IllegalArgumentException("Not supported");
    }

    public static class CompetitionRoundRowMapper implements RowMapper<CompetitionRound> {

        @Override
        public CompetitionRound mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CompetitionRound(
                    rs.getString("name"),
                    rs.getBoolean("home_advantage"),
                    TieBreaker.valueOf(rs.getString("tie_breaker")));
        }
    }
}
