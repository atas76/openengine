CREATE TABLE Team(team_name TEXT PRIMARY KEY, full_name TEXT UNIQUE, SKILL INTEGER);
CREATE TABLE Competition(id INTEGER PRIMARY KEY, name TEXT, country TEXT);
CREATE TABLE CompetitionRound(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    home_advantage BOOLEAN,
    tie_breaker TEXT,
    competition_id INTEGER FOREIGN_KEY REFERENCES Competition);
CREATE TABLE Tournament(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    competition_id INTEGER FOREIGN KEY REFERENCES Competition,
    year INTEGER);
CREATE TABLE TournamentParticipation(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    team_key INTEGER FOREIGN_KEY REFERENCES Team(team_name),
    tournament_id FOREIGN_KEY REFERENCES Tournament);
CREATE TABLE TournamentReplayHistory(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tournament_id INTEGER FOREIGN_KEY REFERENCES Tournament,
    team_full_name TEXT FOREIGN_KEY REFERENCES Team(full_name),
    wins INTEGER,
    finals_participations INTEGER,
    historical_coefficient INTEGER);
