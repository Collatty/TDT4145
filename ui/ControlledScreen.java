

public interface ControlledScreen {
    
    //This method will allow the injection of the Parent ScreenPane. All fxml-controllers implement this interface.
    public void setScreenParent(ScreensController screenPage);

}