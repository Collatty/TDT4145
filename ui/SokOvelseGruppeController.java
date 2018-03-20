import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SokOvelseGruppeController implements Initializable, ControlledScreen{

	ScreensController myController;
	public String valgtGruppe;

	@FXML
	private ListView<String> gruppe;
	@FXML
	private ListView<String> ovelse;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gruppe.setItems(myController.dbController.displayOvelsesgrupper());
		gruppe.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				valgtGruppe = gruppe.getSelectionModel().getSelectedItem();
				System.out.println(valgtGruppe);
			}
		});
	}

	@FXML
	void goToHome(ActionEvent event) {
		myController.setScreen(ApplicationDemo.HomescreenID);
	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;

	}


}
