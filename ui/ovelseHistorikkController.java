import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ovelseHistorikkController implements Initializable, ControlledScreen{

    private ScreensController myController;

    @FXML
    private TableView<Treningsøkt> treningsokter;
    @FXML
    private TableColumn<Treningsøkt, Date> Dato;
    @FXML
    private TableColumn<?, ?> Tidspunkt;
    @FXML
    private TableColumn<?, ?> Varighet;
    @FXML
    private TextField n;
    @FXML
    private Button nullstill;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dato.setCellValueFactory(new PropertyValueFactory<>("Dato"));
        Tidspunkt.setCellValueFactory(new PropertyValueFactory<>("Tidspunkt"));
        Varighet.setCellValueFactory(new PropertyValueFactory<>("Varighet"));
        loadTableView();
    }

    public void loadTableView(){
        myController.dbController.loadTableView(treningsokter);
    }

    public void nSisteOkter(){
        ObservableList<Treningsøkt> nOkter = FXCollections.observableArrayList();
        if (n.getText().length() == 0){
            resetTable();
        }else{
            nullstill.setVisible(true);
            int i = 0;
            for (Treningsøkt t: treningsokter.getItems().sorted()){
                if (i < Integer.parseInt(n.getText())){
                    i++;
                    nOkter.add(t);
                }
            }
        }
        treningsokter.setItems(nOkter);
    }
    @FXML
    public void resetTable(){
        n.setText("");
        nullstill.setVisible(false);
        ObservableList<Treningsøkt> nOkter = FXCollections.observableArrayList();
        for(Treningsøkt t: Treningsøkt.økter){
            nOkter.add(t);
        }
        treningsokter.setItems(nOkter);
    }

    @FXML
    public void goToHome(){
        myController.setScreen(ApplicationDemo.HomescreenID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }


}
