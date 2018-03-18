import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;




public class Treningsøkt implements Comparable<Treningsøkt>{

    public static List<Treningsøkt> økter = new ArrayList<Treningsøkt>();

    public static Treningsøkt nyTreningsøkt(int ID, Date Dato, Time Tid, int Varighet) {
        Treningsøkt økt = getTreningsøkt(ID);
        if (økt != null) {
            return økt;
        } else {
            økt = new Treningsøkt(ID,Dato,Tid,Varighet);
            økter.add(økt);
            return økt;
        }
    }

    public static Treningsøkt getTreningsøkt(int ID){
            for(Treningsøkt o: økter) {
                if(o.getID() == ID) {
                    return o;
                }
            }
            return null;

    }

    private final int ID;
    private final Date Dato;
    private final Time Tidspunkt;
    private final int Varighet;

    private Treningsøkt(int ID, Date Dato, Time Tid, int Varighet){
        this.ID = ID;
        this.Dato = Dato;
        this.Tidspunkt = Tid;
        this.Varighet = Varighet;
    }

    public int getID() {
        return ID;
    }

    public String getDato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(Dato);
        return date;
    }

    public Date getDate(){
        return Dato;
    }

    public String getTidspunkt() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String tid = sdf.format(Tidspunkt);
        return tid;
    }

    public String getVarighet() {
        return Varighet+" minutter";
    }

    @Override
    public int compareTo(Treningsøkt o) {
        return o.getDate().compareTo(getDate());
    }

    @Override
    public String toString() {
        return "ID: "+getID()+", Dato: "+getDato().toString()+", Tidspunkt: "+getTidspunkt().toString()+", Varighet: "+getVarighet();
    }
}
