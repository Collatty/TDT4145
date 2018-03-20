

public class OvelseIOkt {

    private int oktID;
    private String OvelseID;
    private int set;
    private int vekt;
    private int form;
    private int prestasjon;

    public OvelseIOkt(int oktID, String OvesleID, int set, int vekt, int form, int prestasjon){
        this.oktID = oktID;
        this.OvelseID= OvesleID;
        this.set=set;
        this.vekt=vekt;
        this.form=form;
        this.prestasjon=prestasjon;

    }

    public int getOktID() {
        return oktID;
    }

    public String getOvelseID() {
        return OvelseID;
    }

    public int getSet() {
        return set;
    }

    public int getVekt() {
        return vekt;
    }

    public int getForm() {
        return form;
    }

    public int getPrestasjon() {
        return prestasjon;
    }
}
