create TABLE IF NOT EXISTS subjects (
id INT NOT NULL AUTO_INCREMENT,
subjName VARCHAR(45) NOT NULL,
weekday VARCHAR(45) NOT NULL,
classroom VARCHAR(4) NOT NULL,
PRIMARY KEY (id));

create TABLE IF NOT EXISTS teachers (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
subjId INT NULL,
numbPair INT NULL,
numbStud INT NULL,
PRIMARY KEY (id),
FOREIGN KEY (subjId) references subjects(id));

