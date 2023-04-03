
import java.sql.Connection;
import java.sql.DriverManager;

public class Connecting {
    Connection con;

    public Connecting() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
        } catch (Exception e) {
            System.out.println("connected not yet!!");
        }

    }

    public Connection BaseDeDonnees() {
        return con;
    }
}