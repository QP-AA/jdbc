import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class test {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/test",
                        "root",
                        "12345");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select *"
                        + " from employees"
                        + " where department_id in ('10','20','30')")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t"
                        + rs.getString(2) + "\t"
                        + rs.getString(3) + "\t"
                        + rs.getString(4) + "\t"
                        + rs.getString(5));
            }
        }
    }
}