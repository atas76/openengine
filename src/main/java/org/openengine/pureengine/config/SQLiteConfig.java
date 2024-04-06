package org.openengine.pureengine.config;

import javax.sql.DataSource;

import org.openengine.pureengine.domain.repository.db.CompetitionRepositoryDbImpl;
import org.openengine.pureengine.domain.repository.db.TeamRepositoryDbImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SQLiteConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:src/main/resources/db/pureengine/domain.db");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TeamRepositoryDbImpl teamRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        return new TeamRepositoryDbImpl(jdbcTemplate);
    }

    @Bean
    public CompetitionRepositoryDbImpl competitionRepositoryDbImpl(JdbcTemplate jdbcTemplate) {
        return new CompetitionRepositoryDbImpl(jdbcTemplate);
    }
}
