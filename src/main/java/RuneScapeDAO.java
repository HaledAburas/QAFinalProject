
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuneScapeDAO {
    public static final String sqlite_url = "jdbc:sqlite:E:/QA_ECOM/SQL_DB/RuneScapeDB.db";

    public static final Connection con;


    static {
        try {
            con = DriverManager.getConnection(sqlite_url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNewDataBase() {
        String url = "jdbc:sqlite:E:/QA_ECOM/SQL_DB/RuneScapeDB.db";
        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable() {
        String url = "jdbc:sqlite:E:/QA_ECOM/SQL_DB/RuneScapeDB.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                String sql = "CREATE TABLE IF NOT EXISTS RuneScape_users(id INTEGER , username TEXT NOT NULL, Email TEXT NOT NULL,password TEXT NOT NULL, Date TEXT)";
                Statement stm = con.createStatement();
                stm.executeUpdate(sql);
                System.out.println("create table =");
                System.out.println("the table  is created ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    //CreatingUser

    public static void createUser(RuneScapeUser newUser) throws SQLException {
        String query = "INSERT INTO Runescape_Users (id,username, Email, password, date) VALUES (?, ?, ?, ?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,newUser.getId());
        ps.setString(2, newUser.getUsername());
        ps.setString(3, newUser.getEmail());
        ps.setString(4, newUser.getPassword());
        ps.setString(5, newUser.getBirthDate());

        ps.executeUpdate();
    }


    public static void UpdateTable(RuneScapeUser updatedUser) throws SQLException{
        String query = "UPDATE Runescape_Users SET username=?, email=?, password=?, date=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, updatedUser.getUsername());
        ps.setString(2, updatedUser.getEmail());
        ps.setString(3, updatedUser.getPassword());
        ps.setString(4, updatedUser.getBirthDate());
        ps.setInt(5, updatedUser.getId());

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated == 0) {
            System.err.println("No rows were updated for user with id " + updatedUser.getId());
        }
        else {
            System.out.println("the user"+updatedUser.getId()+"has been updated");
        }

    }
    public static void deleteFromTable(int id) throws SQLException {
        String query = "DELETE FROM Runescape_Users WHERE id =?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        //Statement stm = con.createStatement();
        pst.executeUpdate();

        System.out.println("Record deleted from the table...");
        System.out.println("The table is updated!");
    }

    public static List<RuneScapeUser> getAll() throws SQLException {
        List<RuneScapeUser> users = new ArrayList<>();
        String query = "SELECT * FROM Runescape_Users";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String Email = rs.getString("Email");
            String password = rs.getString("password");
            String date = rs.getString("Date");
            RuneScapeUser user = new RuneScapeUser(id, username, Email, password,date);
            users.add(user);
        }
        return users;
    }

    public static RuneScapeUser getById(int id) throws SQLException {
        String query = "SELECT * FROM Runescape_Users WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("Email");
                String password = rs.getString("password");
                String date = rs.getString("Date");
                return new RuneScapeUser(id, username, email, password, date);
            }
        }
        return null;
    }

    public static void main(String[] args) {


    }
}