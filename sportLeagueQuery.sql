CREATE DATABASE SportLeague;
use sportleague;
CREATE TABLE UzytkownikAplikacji (
idUzytkownika int auto_increment primary key not null,
login varchar(30) unique not null,
haslo varchar(255) not null,
imie varchar(20),
nazwisko varchar(20),
pesel varchar(11),
poziomUprawnien int not null,
czyZatwierdzony bool default false not null
);
DELIMITER //
CREATE PROCEDURE dodajUzytkownika (login VARCHAR(30), haslo VARCHAR(255), imie VARCHAR(20), nazwisko VARCHAR(20), pesel char(11), poziomUprawnien int)
BEGIN
	INSERT INTO uzytkownikaplikacji (login, haslo, imie, nazwisko, pesel, poziomUprawnien, czyZatwierdzony) VALUES (login, sha1(haslo), imie, nazwisko, pesel, poziomUprawnien, false);
END //

INSERT INTO uzytkownikaplikacji (idUzytkownika, login, haslo, poziomUprawnien, czyZatwierdzony) VALUES (1, "admin", sha1('ZAQ!2wsx'), 1, true);

DELIMITER //
CREATE PROCEDURE zaloguj (nick VARCHAR(30), haslo VARCHAR(255))
BEGIN
DECLARE pass1, pass2 VARCHAR(255);
	SET pass1 = sha1(haslo);
    SET pass2 = (SELECT uzytkownikaplikacji.haslo FROM uzytkownikaplikacji WHERE login = nick AND czyZatwierdzony = true);
    IF pass1 = pass2 THEN SELECT 1;
    ELSE SELECT 0;
    END IF;
END //
DELIMITER //
CREATE PROCEDURE sprawdzHaslo (haslo VARCHAR(255), id int)
BEGIN
DECLARE pass1, pass2 VARCHAR(255);
	SET pass1 = sha1(haslo);
    SET pass2 = (SELECT uzytkownikaplikacji.haslo FROM uzytkownikaplikacji WHERE idUzytkownika = id);
    IF pass1 = pass2 THEN SELECT 1;
    ELSE SELECT 0;
    END IF;
END //
INSERT INTO uzytkownikaplikacji (idUzytkownika, login, haslo, poziomUprawnien, czyZatwierdzony) VALUES (1, "admin", sha1('ZAQ!2wsx'), 1, true);
CREATE TABLE Druzyna (
 idDruzyny int auto_increment primary key not null,
 nazwaDruzyny varchar(20) not null,
 miasto varchar(20) not null,
 rokZalozenia int not null,
 dywizja enum("Open", "Women", "Mixed") not null,
 zwyciestwa int default 0 not null,
 remisy int default 0 not null,
 porazki int default 0 not null,
 zdobytePunkty int default 0 not null,
 idUzytkownikaKapitana int not null,
 foreign key (idUzytkownikaKapitana) references uzytkownikaplikacji(idUzytkownika)
);

INSERT INTO Druzyna(nazwaDruzyny, miasto, rokZalozenia, dywizja, idUzytkownikaKapitana) VALUES ("71 Wratislavia", "Wrocław", 2014, "Mixed", 1);
INSERT INTO Druzyna(nazwaDruzyny, miasto, rokZalozenia, dywizja, idUzytkownikaKapitana) VALUES ("FLOW", "Wrocław", 2010, "Mixed", 1);
INSERT INTO Druzyna(nazwaDruzyny, miasto, rokZalozenia, dywizja, idUzytkownikaKapitana) VALUES ("KWR Knury", "Kamieniec Wrocławski", 2012, "Mixed", 1);

CREATE TABLE Turniej (
idTurnieju int auto_increment primary key not null,
nazwaTurnieju varchar(20) not null,
dataTurnieju date not null,
miejsce varchar(20) not null,
dywizjaTurnieju enum("Open", "Women", "Mixed", "Open/Women") not null,
idOrganizatora int not null
);
CREATE TABLE Zawodnik (
idZawodnika int auto_increment primary key not null,
idDruzyny int not null,
imie varchar(20) not null,
nazwisko varchar(20) not null,
plec enum("Mężczyzna", "Kobieta") not null,
rokUrodzenia int not null,
numerZawodnika int not null,
zdobytePunkty int default 0,
foreign key (idDruzyny) references Druzyna(idDruzyny)
);
CREATE TABLE Mecz (
idMeczu int auto_increment primary key not null,
idDruzynyPierwszej int not null,
idDruzynyDrugiej int not null,
punktyDruzynyPierwszej int not null,
punktyDruzynyDrugiej int not null,
idTurnieju int not null,
czyZakonczony bool default false,
foreign key (idTurnieju) references Turniej(idTurnieju),
foreign key (idDruzynyPierwszej) references Druzyna(idDruzyny),
foreign key (idDruzynyDrugiej) references Druzyna(idDruzyny)
);
CREATE TABLE punktacjaMeczu (
id int primary key auto_increment,
idMeczu int not null,
idZawodnika int not null,
punktyDruzynyPierwszejPoPunkcie int not null,
punktyDruzynyDrugiejPoPunkcie int not null,
foreign key (idMeczu) references Mecz(idMeczu),
foreign key (idZawodnika) references Zawodnik(idZawodnika)
);
DELIMITER //
CREATE PROCEDURE dodajPunkt (meczuId int, druzynyId int, zawodnikaNumer int, czyPierwsza VARCHAR(10))
BEGIN
	IF czyPierwsza = 'true' THEN
		UPDATE mecz SET punktyDruzynyPierwszej = punktyDruzynyPierwszej + 1 WHERE idMeczu = meczuId AND idDruzynyPierwszej = druzynyId;
    END IF;
    IF czyPierwsza = 'false' THEN
		UPDATE mecz SET punktyDruzynyDrugiej = punktyDruzynyDrugiej + 1 WHERE idMeczu = meczuId AND idDruzynyDrugiej = druzynyId;
    END IF;
    UPDATE zawodnik SET zdobytePunkty = zdobytePunkty + 1 WHERE idDruzyny = druzynyId AND numerZawodnika = zawodnikaNumer;
    INSERT INTO punktacjaMeczu (idMeczu, idZawodnika, punktyDruzynyPierwszejPoPunkcie, punktyDruzynyDrugiejPoPunkcie) VALUES (meczuId, 
		(SELECT idZawodnika FROM Zawodnik WHERE idDruzyny = druzynyId AND numerZawodnika = zawodnikaNumer), 
		(SELECT punktyDruzynyPierwszej FROM mecz WHERE idMeczu = meczuId), (SELECT punktyDruzynyDrugiej FROM mecz WHERE idMeczu = meczuId));
END //

INSERT INTO Mecz VALUES (2, 1, 2, 1, 0, 0, false);
INSERT INTO Turniej (nazwaTurnieju, dataTurnieju, miejsce, dywizjaTurnieju, idOrganizatora) VALUES ("Poligon", "2020-10-10", "Zabrze", "Mixed", 1);
INSERT INTO Turniej (nazwaTurnieju, dataTurnieju, miejsce, dywizjaTurnieju, idOrganizatora) VALUES ("Windmill", "2018-07-19", "Amsterdam", "Mixed", 1);
INSERT INTO Turniej (nazwaTurnieju, dataTurnieju, miejsce, dywizjaTurnieju, idOrganizatora) VALUES ("Mistrzostwa Polski", "2010-01-10", "Warszawa", "Open/Women", 1);
INSERT INTO Turniej (nazwaTurnieju, dataTurnieju, miejsce, dywizjaTurnieju, idOrganizatora) VALUES ("WLU", "2020-05-09", "Warszawa", "Open", 1);
INSERT INTO ZAWODNIK (idDruzyny, imie, nazwisko, plec, rokUrodzenia, numerZawodnika) values (1, "Wiktoria", "Byra", "Kobieta", 1999, 51);
INSERT INTO ZAWODNIK (idDruzyny, imie, nazwisko, plec, rokUrodzenia, numerZawodnika) values (1, "Klaudia", "Wrońska", "Kobieta", 1998, 7);
INSERT INTO ZAWODNIK (idDruzyny, imie, nazwisko, plec, rokUrodzenia, numerZawodnika) values (1, "Maciej", "Adamski", "Mężczyzna", 1998, 3);
INSERT INTO ZAWODNIK (idDruzyny, imie, nazwisko, plec, rokUrodzenia, numerZawodnika) values (2, "Natalia", "Nędzi", "Kobieta", 2001, 13);
INSERT INTO ZAWODNIK (idDruzyny, imie, nazwisko, plec, rokUrodzenia, numerZawodnika) values (2, "Paulina", "Dul", "Kobieta", 1995, 1);