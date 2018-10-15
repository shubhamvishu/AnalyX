package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpentableController implements Initializable {
  //@FXML Label mylabel;
  @FXML
  JFXComboBox<String> comboBox;
  //@FXML JFXDatePicker dp;
  ObservableList<String> list=FXCollections.observableArrayList("Customertable");
  Controller ct=new Controller();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
    }
  /*  @FXML
    void comboboxChanged (ActionEvent event)
    {
        mylabel.setText(comboBox.getValue());
    }*/
    @FXML
    void openfile(ActionEvent event) throws IOException
    {
        try {
            Stage prim = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXML/"+comboBox.getValue() + ".fxml"));
            prim.setTitle("Shubham");
            prim.setResizable(false);
            switch (comboBox.getValue())
            {
                case "Customertable":prim.setScene(new Scene(root, 1238, 780));
                                       break;
            }
            prim.show();
        }
        catch (NullPointerException e)
        {
            Alert al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("Sorry...Unable to open "+comboBox.getValue()+".fxml");
            al.showAndWait();
        }
        //ct.prim.close();
       System.out.println("Selected:"+comboBox.getValue());
    }
    /*@FXML
    void dateChanged(ActionEvent event)
    {
        LocalDate dd=dp.getValue();
        mylabel.setText(String.valueOf(dd));
    }*/
    @FXML
    void showalert(ActionEvent event)
    {
        Alert al=new Alert(Alert.AlertType.WARNING);
        al.setTitle("Warning");
        al.setContentText("Sorry!!....Unable to open the file ");
        al.setHeaderText("Basic Alert");
        al.showAndWait();
    }
}
