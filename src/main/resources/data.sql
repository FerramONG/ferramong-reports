DROP TABLE IF EXISTS Scheduler;

CREATE TABLE Scheduler (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          name VARCHAR(250) NOT NULL
);

INSERT INTO Scheduler (name) VALUES
('FULANO DA SILVA'),
('FULANO DA COSTA'),
('FULANO DA SILVA COSTA'),
('BELTRANO'),
('BELTRANO FULANO DA SILVA'),
('SICRANO');
