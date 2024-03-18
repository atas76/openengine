CREATE TABLE Team(name TEXT PRIMARY KEY, full_name TEXT UNIQUE, SKILL INTEGER);
CREATE TABLE Competition(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, country TEXT);
CREATE TABLE CompetitionRound(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    home_advantage BOOLEAN,
    competition_id INTEGER FOREIGN_KEY REFERENCES Competition);
CREATE TABLE Tournament(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    competition_id INTEGER FOREIGN KEY REFERENCES Competition,
    year INTEGER);
CREATE TABLE TournamentParticipation(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    team_id INTEGER FOREIGN_KEY REFERENCES Team,
    tournament_id FOREIGN_KEY REFERENCES Tournament);
CREATE TABLE TournamentReplayHistory(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tournament_id INTEGER FOREIGN_KEY REFERENCES Tournament,
    team_full_name TEXT FOREIGN_KEY REFERENCES team(full_name),
    wins INTEGER,
    finals_participations INTEGER,
    historical_coefficient INTEGER);
