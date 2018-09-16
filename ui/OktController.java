

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.collections.FXCollections;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class OktController implements Initializable, ControlledScreen{



	public  ObservableList<OvelseIOkt> ovelseriOkt = FXCollections.observableArrayList();
	public int valgtOktID =0;


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
    private ListView<String> ovelseListe;
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
    @FXML
    private ListView<OvelseIOkt> oktListe;


    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setChoiceBoxes();
		try {
			updateOvelseListe();
		}catch (SQLException e){
			e.printStackTrace();
		}

		todayCheck.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
            public void handle(MouseEvent event) {
				if (!todayCheck.isSelected()) {
					ownDate.setVisible(true);
				}
			}
		});

		
	}

	public void updateOvelseListe() throws SQLException{
		List<String> ovelserliste = myController.dbController.getOvelser();
		ObservableList<String> ovelser = FXCollections.observableList(ovelserliste);
		ovelseListe.setItems(ovelser);
	}
	
	public void setChoiceBoxes() {
		okt_dag.setItems((FXCollections.observableArrayList(
	    	    "01", "02", "03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"))); 
		okt_mnd.setItems((FXCollections.observableArrayList(
	    	    "Jan", "Feb", "Mar","Apr","Mai","Jun","Jul","Aug","Sep","Okt","Nov","Des")));
		okt_ar.setItems(FXCollections.observableArrayList(
	    	    "2017", "2018"));
		form.setItems(FXCollections.observableArrayList(
				"1","2","3","4","5","6","7","8","9","10"));
		prestasjon.setItems(FXCollections.observableArrayList(
				"1","2","3","4","5","6","7","8","9","10"));

	}
	
	@FXML
	public void loggOkt(){
	}

	@FXML
	public void leggTilOvelse(){
		if (valgtOktID != 0) {
			OvelseIOkt okt = new OvelseIOkt(valgtOktID,
                    ovelseListe.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(Set.getText()), Integer.parseInt(Kg.getText()),
                    Integer.parseInt(form.getValue()), Integer.parseInt(prestasjon.getValue()));
			ovelseriOkt.add(okt);
//			myController.dbController.addOvelseIOkt(valgtOktID, ovelseListe.getSelectionModel().getSelectedItem(),
//					Integer.parseInt(Set.getText()), Integer.parseInt(Kg.getText()), Integer.parseInt(form.getValue()),
//							Integer.parseInt(prestasjon.getValue()));
			oktListe.setItems(ovelseriOkt);
		}else{
			System.out.println("du må opprette økt før du legger til øvelse");
		}
	}
	@FXML
    public void oprettOkt(){
		String dato = null;
        if (todayCheck.isSelected()) {
            String DATE_FORMAT_NOW = "yyyy-MM-dd";
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            dato = sdf.format(cal.getTime());

        }else{dato = Date(okt_dag, okt_mnd, okt_ar);}

        String startTid = oktTime.getText()+":"+oktMinutt.getText()+":00";
        Integer varighet = Integer.parseInt((oktVarighet.getText()));
	    valgtOktID = myController.dbController.addTreningsokt(dato, startTid, varighet);
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
