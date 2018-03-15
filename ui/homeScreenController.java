

import java.awt.Checkbox;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class homeScreenController implements Initializable, ControlledScreen{
	
	private ScreensController myController;
	
    @FXML
    private TextField n_okter;
    @FXML
    private TextField ovelseNavn;
    @FXML
    private ChoiceBox<String> f_dag;
    @FXML
    private ChoiceBox<String> f_mnd;
    @FXML
    private ChoiceBox<String> f_ar;
    @FXML
    private ChoiceBox<String> t_dag;
    @FXML
    private ChoiceBox<String> t_mnd;
    @FXML
    private ChoiceBox<String> t_ar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setChoiceBoxes();	
	}
	
	public void setChoiceBoxes() {
		f_dag.setItems((FXCollections.observableArrayList(
	    	    "01", "02", "03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"))); 
		f_mnd.setItems((FXCollections.observableArrayList(
	    	    "Jan", "Feb", "Mar","Apr","Mai","Jun","Jul","Aug","Sep","Okt","Nov","Des")));
		f_ar.setItems(FXCollections.observableArrayList(
	    	    "2017", "2018"));
		t_dag.setItems((FXCollections.observableArrayList(
	    	    "01", "02", "03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30")));
		t_mnd.setItems((FXCollections.observableArrayList(
	    	    "Jan", "Feb", "Mar","Apr","Mai","Jun","Jul","Aug","Sep","Okt","Nov","Des")));
		t_ar.setItems(FXCollections.observableArrayList(
	    	    "2017", "2018"));
	}
    
    @FXML
    void resultSok(ActionEvent event) {
    		String navn = ovelseNavn.getText();
    		String fraDato = Date(f_dag, f_mnd, f_ar);
    		String tilDato = Date(t_dag, t_mnd, t_ar);
    		System.out.println("øvelsenavn: "+navn+"\nfra: "+fraDato+"\ntil: "+tilDato);
    		

    		
    }
    
    public String Date(ChoiceBox<String> dag, ChoiceBox<String> mnd, ChoiceBox<String> ar) {
		String dato = "";
		dato += ar.getValue();
		switch (mnd.getValue()) {
		case "Jan": dato += "-01-";
			 break;
		case "Feb": dato += "-02-";
			 break;
		case "Mar": dato += "-03-";
			 break;
		case "Apr": dato += "-04-";
		 	 break;
		case "Mai": dato += "-05-";
		 	 break;
		case "Jun": dato += "-06-";
		 	 break;
		case "Jul": dato += "-07-";
		 	 break;
		case "Aug": dato += "-08-";
			 break;
		case "Sep": dato += "-09-";
			 break;
		case "Okt": dato += "-10-";
			 break;
		case "Nov": dato += "-11-";
			 break;
		case "Des": dato += "-12-";
			 break;
		}
		dato += dag.getValue();
		return dato;
    }
    
    @FXML
    void oktSok(ActionEvent event) {
    		System.out.println("Få opp informasjon om "+n_okter.getText()+" siste gjennomførte økter");
    		int n = Integer.parseInt(n_okter.getText());
    		//hent n siste økter fra database

    }
    @FXML
    void ovelseGruppe(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.OvelseGruppeID);
    }

    @FXML
    void nyttApparat(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.ApparatID);
    }

    @FXML
    void nyOkt(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.OktID);
    }

    @FXML
    void nyOvelse(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.OvelseID);
    }

    @FXML
    void openOvelsegrupper(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.SokOvelseGruppeID);
    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.myController = screenPage;
		
	}


}
