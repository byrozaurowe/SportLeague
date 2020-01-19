import org.hibernate.*;
import org.hibernate.boot.model.relational.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DatabaseApplication {

    DatabaseApplication() {}

    public static List queries(String args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(args.equals("getTeams")) {
            Query query = session.createSQLQuery("SELECT teamName FROM Team");
            List result = query.list();
            return result;
        }
        return null;
    }
}
