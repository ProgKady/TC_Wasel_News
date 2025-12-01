
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

   
    //I Love You
    //More

    @Override
    public void start(Stage stage) throws Exception {
        showLogin(stage);
    }
  
    private void showLogin(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Window.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TC Wasel");
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("hrrr.png")));
        stage.setOnCloseRequest((WindowEvent event) -> System.exit(0));
        stage.show();

    }
   
    

    public static void main(String[] args) {
        launch(args);
    }
}
  
   