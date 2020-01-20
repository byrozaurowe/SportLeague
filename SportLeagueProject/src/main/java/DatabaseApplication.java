import TablesClasses.AppUser;
import org.apache.log4j.varia.NullAppender;
import org.hibernate.*;
import org.hibernate.boot.model.relational.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DatabaseApplication {

    DatabaseApplication() {}

    public static List queries(String[] args) {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(args[0].equals("getTeams")) {
            Query query = session.createQuery("SELECT teamName FROM TablesClasses.Team");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("teamCity")) {
            Query query = session.createQuery("SELECT city FROM TablesClasses.Team");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("teamFoundationYear")) {
            Query query = session.createQuery("SELECT foundationYear FROM TablesClasses.Team");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("teamDivision")) {
            Query query = session.createQuery("SELECT division FROM TablesClasses.Team");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("getTournaments")) {
            Query query = session.createQuery("SELECT tournamentName FROM TablesClasses.Tournament");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("getPlayers")) {
            Query query = session.createQuery("SELECT name, surname FROM TablesClasses.Player");
            List result = query.list();
            return result;
        }
        else if(args[0].equals("addUser")) {
            session.beginTransaction();
            AppUser appUser = new AppUser();
            appUser.setLogin(args[1]);
            appUser.setHaslo(args[2]);
            appUser.setImie(args[3]);
            appUser.setNazwisko(args[4]);
            appUser.setPesel(args[5]);
            appUser.setPoziomUprawnien(args[6]);
            appUser.setCzyZatwierdzony(false);
            session.save(appUser);
            session.getTransaction().commit();
        }
        return null;
    }
}
