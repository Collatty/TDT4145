
import java.sql.*;

public class DatabaseController {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";


    //  Database credentials
    static final String USER = "hakon";
    static final String PASS = null;

    Connection conn = null;
    Statement stmt = null;

    public void start() {


        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate("use treningsdagbok");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
        public void end(){
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }


        }

    public void addOvelse(String Ovelse, String type){
        String sql = "insert into øvelse (ØvelseID, øvelsetype) values (?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Ovelse);
            ps.setString(2, type);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addOvelseApparat(String OvelseApparat){
        String sql = "insert into øvelseapparat (ØvelseID) values" + OvelseApparat;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addOvelseVanlig(String OvelseVanlig, String Beskrivelse) {
        String sql = "insert into øvelseapparat (ØvelseID, Beskrivelse) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, OvelseVanlig);
            ps.setString(2, Beskrivelse);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOvelsegruppe(String Ovelsegruppe, String Beskrivelse) {
        String sql = "insert into øvelseapparat (Øvelsesgruppeid, Beskrivelse) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Ovelsegruppe);
            ps.setString(2, Beskrivelse);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addApparat(String Apparat){
        String sql = "insert into apparat (navn, beskrivelse) values" + Apparat;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void addApparatTilOvelse(String OvelseApparat, String Apparat){ //hmmm usikker på hvordan jeg skal gjøre denne
        String sql = "insert into apparattiløvelse (ØvelseID) values" + OvelseApparat;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addTreningsokt(Date Dato, Time Tidspunkt, Integer Varighet) {
        String sql = "insert into Treningsøkt (Dato, Tidspunkt, Varighet) values(?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Dato);
            ps.setTime(2, Tidspunkt);
            ps.setInt(3, Varighet);

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNotat(Integer treningsøktid, String Beskrivelse) {
        String sql = "insert into tre (Øvelsesgruppeid, Beskrivelse) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Ovelsegruppe);
            ps.setString(2, Beskrivelse);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










}

