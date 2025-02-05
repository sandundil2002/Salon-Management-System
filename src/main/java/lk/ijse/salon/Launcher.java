package lk.ijse.salon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application{
    public static void main(String[] args) {
        launch(args);
    }
@Override
    public void start(Stage stage) throws Exception{
        Parent parent = FXMLLoader.load(this.getClass().getResource("/View/mainForm.fxml"));
        Scene scene = new Scene(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Mona Beauty Salon");
        stage.getIcons().add(new Image("/asstes/save.png"));
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}