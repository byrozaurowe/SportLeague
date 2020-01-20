import TablesClasses.AppUser;
import TablesClasses.Player;
import TablesClasses.Team;
import TablesClasses.Tournament;
import org.apache.log4j.varia.NullAppender;
import org.hibernate.*;
import java.util.List;

class DatabaseApplication {

    DatabaseApplication() {}

    public static List queries(String[] args) {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(args[0].equals("getTeams")) {
            Query query = session.createQuery("SELECT teamName FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamCity")) {
            Query query = session.createQuery("SELECT city FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamFoundationYear")) {
            Query query = session.createQuery("SELECT foundationYear FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamDivision")) {
            Query query = session.createQuery("SELECT division FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamWins")) {
            Query query = session.createQuery("SELECT wins FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamDraws")) {
            Query query = session.createQuery("SELECT draws FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamLosts")) {
            Query query = session.createQuery("SELECT losts FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("teamPoints")) {
            Query query = session.createQuery("SELECT scoredPoints FROM TablesClasses.Team");
            return query.list();
        }
        else if(args[0].equals("getTournaments")) {
            Query query = session.createQuery("SELECT tournamentName FROM TablesClasses.Tournament");
            return query.list();
        }
        else if(args[0].equals("tournamentDate")) {
            Query query = session.createQuery("SELECT tournamentDate FROM TablesClasses.Tournament");
            return query.list();
        }
        else if(args[0].equals("tournamentLocation")) {
            Query query = session.createQuery("SELECT location FROM TablesClasses.Tournament");
            return query.list();
        }
        else if(args[0].equals("tournamentDivision")) {
            Query query = session.createQuery("SELECT division FROM TablesClasses.Tournament");
            return query.list();
        }
        else if(args[0].equals("playerName")) {
            Query query = session.createQuery("SELECT name FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("playerSurname")) {
            Query query = session.createQuery("SELECT surname FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("playerTeam")) {
            //Query query = session.createQuery("SELECT teamId FROM TablesClasses.Player");
            SQLQuery query = session.createSQLQuery("SELECT nazwaDruzyny FROM Druzyna JOIN Zawodnik ON Druzyna.idDruzyny = Zawodnik.idDruzyny");
            return query.list();
        }
        else if(args[0].equals("playerNumber")) {
            Query query = session.createQuery("SELECT playerNumber FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("playerSex")) {
            Query query = session.createQuery("SELECT sex FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("playerBirth")) {
            Query query = session.createQuery("SELECT birthYear FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("playerScoredPoints")) {
            Query query = session.createQuery("SELECT scoredPoints FROM TablesClasses.Player");
            return query.list();
        }
        else if(args[0].equals("userCounter")) {
            Query query = session.createQuery("SELECT COUNT(*) FROM AppUser WHERE czyZatwierdzony = 0");
            return query.list();
        }
        else if(args[0].equals("capitanTeams")) {
            SQLQuery query = session.createSQLQuery("SELECT nazwaDruzyny FROM druzyna WHERE idUzytkownikaKapitana = :idUzytkownikaKapitana");
            query.setParameter("idUzytkownikaKapitana", args[1]);
            return query.list();
        }
        else if(args[0].equals("requestsLogins")) {
            Query query = session.createQuery("SELECT login FROM AppUser WHERE czyZatwierdzony = 0 ORDER BY idUzytkownika");
            return query.list();
        }
        else if(args[0].equals("requestsFirstNames")) {
            Query query = session.createQuery("SELECT imie FROM AppUser WHERE czyZatwierdzony = 0 ORDER BY idUzytkownika");
            return query.list();
        }
        else if(args[0].equals("requestsNames")) {
            Query query = session.createQuery("SELECT nazwisko FROM AppUser WHERE czyZatwierdzony = 0 ORDER BY idUzytkownika");
            return query.list();
        }
        else if(args[0].equals("requestsPesels")) {
            Query query = session.createQuery("SELECT pesel FROM AppUser WHERE czyZatwierdzony = 0 ORDER BY idUzytkownika");
            return query.list();
        }
        else if(args[0].equals("requestsPermissions")) {
            Query query = session.createQuery("SELECT poziomUprawnien FROM AppUser WHERE czyZatwierdzony = 0 ORDER BY idUzytkownika");
            return query.list();
        }
        else if(args[0].equals("confirmUser")) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT idUzytkownika FROM AppUser WHERE login = :login");
            query.setParameter("login", args[1]);
            int id = Integer.parseInt(String.valueOf(query.list().get(0)));
            query = session.createQuery("UPDATE AppUser set czyZatwierdzony = true WHERE idUzytkownika = :idUzytkownika");
            query.setParameter("idUzytkownika", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
        else if(args[0].equals("declineUser")) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT idUzytkownika FROM AppUser WHERE login = :login");
            query.setParameter("login", args[1]);
            int id = Integer.parseInt(String.valueOf(query.list().get(0)));
            query = session.createQuery("DELETE FROM AppUser WHERE idUzytkownika = :idUzytkownika");
            query.setParameter("idUzytkownika", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
        else if(args[0].equals("addUser")) {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("call dodajUzytkownika(:login, :haslo, :imie, :nazwisko, :pesel, :poziomUprawnien)");
            query.addEntity(AppUser.class);
            query.setParameter("login", args[1]);
            query.setParameter("haslo", args[2]);
            query.setParameter("imie", args[3]);
            query.setParameter("nazwisko", args[4]);
            query.setParameter("pesel", args[5]);
            query.setParameter("poziomUprawnien", Integer.parseInt(args[6]));
            query.executeUpdate();
            session.getTransaction().commit();
        }
        else if(args[0].equals("loggIn")) {
            Query query = session.createSQLQuery("call zaloguj(:login, :haslo)");
            query.setParameter("login", args[1]);
            query.setParameter("haslo", args[2]);
            List result = query.list();
            query = session.createQuery("SELECT poziomUprawnien FROM AppUser WHERE login = :login");
            query.setParameter("login", args[1]);
            List temp = query.list();
            if(temp.size() == 0) {
                System.out.println("Nieprawidlowe dane");
            }
            else {
                result.add(temp.get(0));
                query = session.createQuery("SELECT idUzytkownika FROM AppUser WHERE login = :login");
                query.setParameter("login", args[1]);
                temp = query.list();
                result.add(temp.get(0));
            }
            return result;
        }
        else if(args[0].equals("addPlayer")) {
            SQLQuery query = session.createSQLQuery("SELECT idDruzyny FROM druzyna WHERE nazwaDruzyny = :nazwaDruzyny");
            query.setParameter("nazwaDruzyny", args[6]);
            List result = query.list();
            int teamId = Integer.parseInt(String.valueOf(result.get(0)));
            query = session.createSQLQuery("SELECT COUNT(*) FROM zawodnik WHERE idDruzyny = :idDruzyny");
            query.setParameter("idDruzyny", teamId);
            result = query.list();
            session.beginTransaction();
            Player player = new Player();
            player.setPlayerId(Integer.parseInt(String.valueOf(result.get(0))));
            player.setName(args[1]);
            player.setSurname(args[2]);
            player.setSex(args[3]);
            player.setBirthYear(Integer.parseInt(args[4]));
            player.setPlayerNumber(Integer.parseInt(args[5]));
            player.setTeamId(teamId);
            session.save(player);
            session.getTransaction().commit();
        }
        else if(args[0].equals("addTeam")) {
            Query query = session.createQuery("SELECT COUNT(*) FROM TablesClasses.Team");
            int id = Integer.parseInt(String.valueOf(query.list().get(0)));
            session.beginTransaction();
            Team team = new Team();
            team.setTeamId(id);
            team.setTeamName(args[1]);
            team.setCity(args[2]);
            team.setFoundationYear(Integer.parseInt(args[3]));
            team.setDivision(args[4]);
            team.setDraws(0);
            team.setScoredPoints(0);
            team.setWins(0);
            team.setLosts(0);
            team.setCapitanUserId(Integer.parseInt(args[5]));
            session.save(team);
            session.getTransaction().commit();
        }
        else if(args[0].equals("addTournament")) {
            Query query = session.createQuery("SELECT COUNT(*) FROM TablesClasses.Tournament");
            int id = Integer.parseInt(String.valueOf(query.list().get(0)));
            session.beginTransaction();
            Tournament tournament = new Tournament();
            tournament.setTournamentId(id);
            tournament.setTournamentName(args[1]);
            //tournament.setTournamentDate(args[3]);
            tournament.setDivision(args[4]);
            tournament.setLocation(args[2]);
            tournament.setOrganizerId(Integer.parseInt(args[5]));
            session.save(tournament);
            session.getTransaction().commit();
        }
        return null;
    }
}
