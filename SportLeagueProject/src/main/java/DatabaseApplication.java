import TablesClasses.AppUser;
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
        else if(args[0].equals("getTournaments")) {
            Query query = session.createQuery("SELECT tournamentName FROM TablesClasses.Tournament");
            return query.list();
        }
        else if(args[0].equals("getPlayers")) {
            Query query = session.createQuery("SELECT name, surname FROM TablesClasses.Player");
            return query.list();
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
            result.add(temp.get(0));
            return result;
        }
        return null;
    }
}
