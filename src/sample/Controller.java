package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML AnchorPane in;
    @FXML AnchorPane about;
    @FXML AnchorPane mainlog;
    @FXML AnchorPane mainabout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainlog.setVisible(true);

    }
    @FXML
    void logg(MouseEvent event)
    {
        if(event.getSource()==in) {
            //in.setStyle("-fx-background-color:#00ff00;-fx-border-color:#fff;-fx-border-width:5px;");
            //about.setStyle("-fx-background-color: #B2BABB;");
            mainlog.setVisible(true);
            mainabout.setVisible(false);
        }
        else if(event.getSource()==about){
            System.out.println("B");
            //about.setStyle("-fx-background-color: #B2BABB;-fx-border-color:#fff;-fx-border-width:5px;");
            //in.setStyle("-fx-background-color:#00ff00;");
            mainlog.setVisible(false);
            mainabout.setVisible(true);

        }
    }
}
