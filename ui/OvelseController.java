
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.multi.MultiButtonUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OvelseController implements Initializable, ControlledScreen{

	private ScreensController myController;
	
    @FXML
    private TextField navn;
    @FXML
    private CheckBox maskin;
    @FXML
    private TextArea beskrivelse;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beskrivelse.setVisible(true);
		maskin.setOnAction(new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event) {
	        	if(maskin.isSelected()){
					beskrivelse.setVisible(false);
				}else{
	        		beskrivelse.setVisible(true);
				}

	        }
	    });
	}

    @FXML
    void goToHome(ActionEvent event) {
    		myController.setScreen(ApplicationDemo.HomescreenID);
    }

    @FXML
    void leggTil(ActionEvent event) {
		if (maskin.isSelected()){
			myController.dbController.addOvelse(navn.getText(), "apparat");

			myController.dbController.addOvelseApparat(navn.getText());
		}else{
			myController.dbController.addOvelse(navn.getText(), "vanlig");
			myController.dbController.addOvelseVanlig(navn.getText(), beskrivelse.getText());

		}

    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
		
	}


}
