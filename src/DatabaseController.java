
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no:3306/ntnu_all_treningsdagbok?autoReconnect=true&useSSL=false";


    //  Database credentials
    static final String USER = "ntnu_all_dbprosjekt";
    static final String PASS = "hei123";

    Connection conn = null;
    Statement stmt = null;


    public DatabaseController() {




        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database");


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
        String sql = "insert into Øvelse (ØvelseID, øvelsetype) values (?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Ovelse);
            ps.setString(2, type);
            stmt = conn.createStatement();
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addOvelseApparat(String OvelseApparat){
        String sql = "insert into ØvelseApparat (ØvelseID) values(?)" ;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, OvelseApparat);
            stmt = conn.createStatement();
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addOvelseVanlig(String OvelseVanlig, String Beskrivelse) {
        String sql = "insert into ØvelseVanlig (ØvelseID, Beskrivelse) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, OvelseVanlig);
            ps.setString(2, Beskrivelse);
            stmt = conn.createStatement();
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOvelsegruppe(String Ovelsegruppe, String Beskrivelse) {
        String sql = "insert into Øvelsesgruppe (Øvelsesgruppeid, Beskrivelse) values(?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Ovelsegruppe);
            ps.setString(2, Beskrivelse);
            stmt = conn.createStatement();
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addApparat(String Apparat, String Beskrivelse){
        String sql = "insert into Apparat (navn, beskrivelse) values(?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Apparat);
            ps.setString(2, Beskrivelse);

            stmt = conn.createStatement();
            System.out.println(sql);

            ps.executeUpdate();
            System.out.println("Success");

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

    public void addTreningsokt(String Dato, String Tidspunkt, Integer Varighet) {
        String sql = "insert into Treningsøkt (Dato, Tidspunkt, Varighet) values(?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Dato);
            ps.setString(2, Tidspunkt);
            ps.setInt(3, Varighet);

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNotat(Integer treningsøktid, String treningsformål, String Vurdering) {
        String sql = "insert into notat (treningsøktid, treningsformål, Vurdering) values(?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, treningsøktid);
            ps.setString(2, treningsformål);
            ps.setString(3, Vurdering);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOvelser() throws SQLException {

        ResultSet rs = null;
        List<String> Ovelser = new ArrayList<String>();

        String sql = "select ØvelseID from Øvelse";
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(sql);
            rs = stmt.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (rs != null) {
            while (rs.next()) {
                String Ovelse = rs.getString(1);
                Ovelser.add(Ovelse);


            }
        }
        return Ovelser;


    }














}

