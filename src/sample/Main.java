package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage primaryStage=new Stage();

    @Override
    public void start(Stage primary) throws Exception{
        try {
            //System.out.println("11");
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("FXML/log.fxml"));
            System.out.println("shubham");
            primaryStage.setTitle("Galleria Mall");
            Scene scene=new Scene(root,1103, 605);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
