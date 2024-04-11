package org.openengine.pureengine.domain.repository.db;

import org.openengine.pureengine.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class TeamRepositoryDbImpl implements org.openengine.pureengine.domain.repository.Repository<Team> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Team findById(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<Team> findByReferenceId(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Team findByName(String name) {
        String sql = "SELECT * FROM Team WHERE team_name = ?";
        return jdbcTemplate.queryForObject(sql, new TeamRowMapper(), name);
    }

    @Override
    public Collection<Team> findAll() {
        return jdbcTemplate.query("SELECT team_name, full_name, skill FROM Team", new TeamRowMapper());
    }

    @Override
    public void loadData() {
        throw new IllegalArgumentException("Not applicable");
    }

    public static class TeamRowMapper implements RowMapper<Team> {
        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Team(
                    rs.getString("team_name"),
                    rs.getString("full_name"),
                    rs.getInt("skill"));
        }
    }
}