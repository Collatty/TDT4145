
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no:3306/ntnu_all_treningsdagbok?autoReconnect=true&useSSL=false";


    //  Database credentials
    static final String USER = "ntnu_all_dbprosjekt";
    static final String PASS = "hei123";

    Connection conn = null;
    Statement stmt = null;


    public DatabaseConnector() {




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



    public int addTreningsokt(String Dato, String Tidspunkt, Integer Varighet) {
        String sql = "insert into Treningsøkt (Dato, Tidspunkt, Varighet) values(?,?,?)";
        int ovelseid;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Dato);
            ps.setString (2, Tidspunkt);
            ps.setInt(3, Varighet);

            ps.executeUpdate();
            ps.close();

            String sql1 = "select (TreningsøktID) from Treningsøkt where Dato = ? and Tidspunkt = ? and Varighet = ?";

            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, Dato);
            ps1.setString(2, Tidspunkt);
            ps1.setInt(3, Varighet);

            ResultSet rs = ps1.executeQuery();
            rs.next();
            ovelseid = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            ovelseid = 0;
        }
        return ovelseid;
    }
    public void addNotat(Integer treningsøktid, String notat) {
        String delete = "delete from Notat where TreningsøktID= ?";
        String sql = "insert into Notat (TreningsøktID, Treningsnotat) values(?,?)";

        try{

            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setInt(1, treningsøktid);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, treningsøktid);
            ps.setString(2, notat);


            ps.executeUpdate();

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


    public void loadTableView(TableView<Treningsøkt> table){
        ObservableList<Treningsøkt> data = FXCollections.observableArrayList();
        String sql = "select * from Treningsøkt";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Treningsøkt økt = Treningsøkt.nyTreningsøkt(rs.getInt("TreningsøktID"), rs.getDate("Dato"), rs.getTime("Tidspunkt"), rs.getInt("Varighet"));
                data.add(økt);
                table.setItems(data);
            }
            ps.close();
            rs.close();
            

        }catch (SQLException e){e.printStackTrace();}
    }


    public String displayNotat(int oktid){

            String sql = "select Treningsnotat from Notat where TreningsøktID = ?";


            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, oktid);
                System.out.println(ps);
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getString(1);
            }catch (SQLException e){
                e.printStackTrace();
                return "No Notates from given trainingsøkt";
            }



    }

    public ObservableList<String> displayOvelsesgrupper() {

        String sql = "select ØvelsesgruppeID from Øvelsesgruppe";
        ObservableList<String> ovelsesgrupper = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ovelsesgrupper.add(rs.getString(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return ovelsesgrupper;


    }

    public ObservableList<String> displayOvelser(String ovelsegruppeid) {

        String sql = "select ØvelseID from ØvelseIGruppe where GruppeID = ?";
        ObservableList<String> ovelser = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ovelsegruppeid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ovelser.add(rs.getString(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return ovelser;


    }

    public void addOvelseIOkt(int øktid, String ovelseid, int set, int kg, int form, int prestasjon) {
        String sql = "insert into ØvelseIØkt (ØktID, ØvelseID, Set, Kg, Form, Prestasjon) values(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, øktid);
            ps.setString(2, ovelseid);
            ps.setInt(3, set);
            ps.setInt(4, kg);
            ps.setInt(5, form);
            ps.setInt(6, prestasjon);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public String sokEtterOvelse( String fra, String til, String navn){
//
//        String sql = select
//    }


}

