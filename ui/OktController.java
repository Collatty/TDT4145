package ui;
import java.awt.Checkbox;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class OktController implements Initializable, ControlledScreen{

	private ScreensController myController;
	
    @FXML
    private ChoiceBox<String> okt_dag;
    @FXML
    private ChoiceBox<String> okt_mnd;
    @FXML
    private ChoiceBox<String> okt_ar;
    @FXML
    private TextField oktTime;
    @FXML
    private TextField oktMinutt;
    @FXML
    private TextField oktVarighet;
    @FXML
    private ListView<?> ovelseListe;
    @FXML
    private AnchorPane ovelseVindu;
    @FXML
    private TextField Set;
    @FXML
    private TextField Kg;
    @FXML
    private ChoiceBox<String> form;
    @FXML
    private ChoiceBox<String> prestasjon;
    @FXML
    private CheckBox todayCheck;
    @FXML
    private AnchorPane ownDate;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setChoiceBoxes();
		todayCheck.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
            public void handle(MouseEvent event) {
				if (!todayCheck.isSelected()) {
					ownDate.setVisible(true);
				}
			}
		});
		
	}
	
	public void setChoiceBoxes() {
		okt_dag.setItems((FXCollections.observableArrayList(
	    	    "01", "02", "03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"))); 
		okt_mnd.setItems((FXCollections.observableArrayList(
	    	    "Jan", "Feb", "Mar","Apr","Mai","Jun","Jul","Aug","Sep","Okt","Nov","Des")));
		okt_ar.setItems(FXCollections.observableArrayList(
	    	    "2017", "2018"));
	}
	
	@FXML
	public void loggOkt(){
		String dato = null;
		if (todayCheck.isSelected()) {
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			dato = sdf.format(cal.getTime());
			
		}else{dato = Date(okt_dag, okt_mnd, okt_ar);}
		
		String startTid = oktTime.getText()+":"+oktMinutt.getText()+":00";
		String varighet = oktVarighet.getText();
		System.out.println("\t-ØKT-\nDato: "+dato+"\nStartet kl: "+startTid+"\nVarighet: "+varighet);
		//her vil vi også ha en liste med øvelse-i-økt instanser.
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
	public void goToHome() {
		myController.setScreen(ApplicationDemo.HomescreenID);
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		this.myController = screenPage;
		
	}


}
