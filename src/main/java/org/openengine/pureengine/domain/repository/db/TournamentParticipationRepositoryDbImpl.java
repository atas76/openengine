package org.openengine.pureengine.domain.repository.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class TournamentParticipationRepositoryDbImpl implements
        org.openengine.pureengine.domain.repository.Repository<String> {

    private JdbcTemplate jdbcTemplate;

    public TournamentParticipationRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findById(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<String> findByReferenceId(int id) {
        String sql = "SELECT team_key from TournamentParticipation where tournament_id = ?";
        return jdbcTemplate.query(sql, new TournamentParticipationRowMapper(), id);
    }

    @Override
    public String findByName(String name) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<String> findAll() {
        throw new IllegalArgumentException("Not supported");
    }

    public static class TournamentParticipationRowMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("team_key");
        }
    }
}
