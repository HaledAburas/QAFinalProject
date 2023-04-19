import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class RuneScapeUserDAOTest {
    private static final RuneScapeDAO dao = new RuneScapeDAO();
    private static final RuneScapeUser testUser1 = new RuneScapeUser(101, "testuser", "testuser1@example.com", "P@ssw0rd!1", "15/12/23");
    private static final RuneScapeUser testUser2 = new RuneScapeUser(102, "testuser2", "testuser2@example.com", "P@ssw0rd!2", "25/2/24");


    public static String sqlite_url = "jdbc:sqlite:E:/QA_ECOM/SQL_DB/RuneScapeDB.db";
    public static Connection con;


    @BeforeClass
    public static void setUp() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:C:/My DataBase/FinalProject.db");
    }

    @After
    public void tearDown() throws SQLException {
        // Close the database connection
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

        @Test
        public void testCreateUser () throws SQLException {
            // save the user using the DAO
            RuneScapeDAO.createUser(testUser1);
            RuneScapeDAO.createUser(testUser2);



            // retrieve the user from the database using the DAO
            RuneScapeUser retrievedUser = dao.getById(testUser2.getId());
            System.out.println(testUser2.toString());
            System.out.println(retrievedUser.toString());

            // assert that the retrieved user matches the original user
            assertEquals(testUser2.getId(), retrievedUser.getId());
            assertEquals(testUser2.getUsername(), retrievedUser.getUsername());
            assertEquals(testUser2.getEmail(), retrievedUser.getEmail());
            assertEquals(testUser2.getPassword(), retrievedUser.getPassword());
            assertEquals(testUser2.getBirthDate(), retrievedUser.getBirthDate());

        }

        @Test // Delete record test
        public void testDeleteFromTable () throws SQLException {

            String query = "SELECT COUNT(*) FROM Runescape_Users";
            Statement stm = RuneScapeDAO.con.createStatement();

            ResultSet rs = stm.executeQuery(query);
            rs.next();
            int countBefore = rs.getInt(1);

            // Delete a record from the table
            RuneScapeDAO.deleteFromTable(testUser2.getId());

            // Check if the record has been deleted

            rs = stm.executeQuery(query);
            rs.next();
            int countAfter = rs.getInt(1);
            assertEquals(countAfter, countBefore - 1);
        }




    @Test
    public void testUpdateUser() throws SQLException {
        //create user
        RuneScapeUser testUser3 = new RuneScapeUser(103, "testuser3", "tesuser3@example.com", "P@ssw0rd!3", "13/10/25");
        RuneScapeDAO.createUser(testUser3);
        System.out.println(testUser3+"has been added");
        // modify the user's properties
        testUser3.setUsername("Updated_User_NAme");
        testUser3.setEmail("Updatedmail@Email.com");
        testUser3.setPassword("NewP@ssw0rd1");
        // update the user in the database
        RuneScapeDAO.UpdateTable(testUser3);
        System.out.println(testUser3+"hs been updated");
        // retrieve the updated user from the database using the DAO
        RuneScapeUser retrievedUser = RuneScapeDAO.getById(testUser3.getId());
        // assert that the retrieved user matches the updated user
        assertEquals(testUser3.getId(), retrievedUser.getId());
        assertEquals(testUser3.getUsername(), retrievedUser.getUsername());
        assertEquals(testUser3.getEmail(), retrievedUser.getEmail());
        assertEquals(testUser3.getPassword(), retrievedUser.getPassword());
        assertEquals(testUser3.getBirthDate(), retrievedUser.getBirthDate());
       // RuneScapeDAO.deleteFromTable(103);
    }




        @Test // Select all records test
        public void testGetAll () throws SQLException {
            List<RuneScapeUser> users = RuneScapeDAO.getAll();
            assertNotNull(users); // check that the list is not null
            assertTrue(users.size() > 0); // check that the list is not empty
            for (RuneScapeUser user : users) {
                System.out.println(user.toString());
            }
        }
    }


    /*
    @Test
    public void UpdateUser() throws SQLException {
        Random rand = new Random();
        int num = rand.nextInt(10);
        String str = "P@assw0rd" + num;
        //System.out.println("Please enter the new email. ");
        String email = "abcd"+rand.nextInt(100)+"@email.com";
        String query = "UPDATE Runescape_Users SET password = '" + str + "',Email = '"+ email +"' WHERE id = 103";
        Statement stm = RuneScapeDAO.con.createStatement();
        stm.executeUpdate(query);
        query = "SELECT password FROM Runescape_Users WHERE id = 103";
        ResultSet rs = stm.executeQuery(query);
        String pw = rs.getString(1);
        assertEquals(pw, str);
    }

 */

/*
    @Test //Update table test
    public void testUpdateTable() throws SQLException {
      RuneScapeDAO.UpdateTable(103,"Haled","HaledMAil","P@assw0ord","21/12/92");
      RuneScapeUser retrievedUser = dao.getById(testUser3.getId());
        assertEquals(testUser3.getId(), retrievedUser.getId());
        assertEquals(testUser3.getUsername(), retrievedUser.getUsername());
        assertEquals(testUser3.getEmail(), retrievedUser.getEmail());
        assertEquals(testUser3.getPassword(), retrievedUser.getPassword());
        assertEquals(testUser3.getBirthDate(), retrievedUser.getBirthDate());

    }


 */

  /* public static Connection con = null;
    static {
        try {
            con = DriverManager.getConnection(sqlite_url);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    */

/*
    @BeforeAll
    public static void setup() throws SQLException {
        //dao.createTable();

    }

 */
 /*   @BeforeClass
    public static void setUp() throws SQLException {
        if(RuneScapeDAO.con == null) {
            con = DriverManager.getConnection("jdbc:sqlite:E:/QA_ECOM/SQL_DB/RuneScapeDB.db");
        }
    }
  */
    /*
    @AfterEach
    public void tearDown() throws SQLException {
        // Close the database connection
        if (RuneScapeDAO.con != null && !RuneScapeDAO.con.isClosed()) {
            RuneScapeDAO.con.close();
            RuneScapeDAO.con = null;
        }
    }
     */