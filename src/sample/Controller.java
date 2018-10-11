package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static Stage prim;
    @FXML AnchorPane in;
    @FXML AnchorPane about;
    @FXML AnchorPane mainlog;
    @FXML AnchorPane mainabout;
    @FXML Label res;
    @FXML JFXTextField user;
    @FXML JFXPasswordField pass;
    boolean connec=false;
    Main main=new Main();

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
    public void login() throws IOException, SQLException, ClassNotFoundException {

        res.setText("");
        connec=false;
        String uname=user.getText();
        String pwd=pass.getText();

        // if(uname.equals(username) && pwd.equals(password))
        // {
        //System.out.println("1");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stud?allowPublicKeyRetrieval=true&useSSL=false";
            Connection con = DriverManager.getConnection(url, uname, pwd);
            res.setText("Login successful");
            System.out.println("DB connected");
            connec=true;
            main.primaryStage.close();
            //TimeUnit.SECONDS.sleep(30);
            //System.out.println("bool:"+connec);
           /* prim=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("opentable.fxml"));
            prim.setTitle("Shubham");
            prim.setScene(new Scene(root, 700, 500));
            prim.show();*/

        }
        catch (Exception e)
        {
            connec=false;
            System.out.println("DB not connected"+e);
            res.setText("Invalid username or password");
        }
        //res.setText("Login successful");


        //System.out.println("shubham1");

        //System.exit(0);
        //}
        //else{
        //System.out.println("2");
        //res.setText("Invalid username or password");
        // }
        //System.in.read();
        //res.setText("");
    }
    public void logout()
    {
        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Logout");
        al.setHeaderText(null);
        al.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result=al.showAndWait();
        if(result.get()==ButtonType.OK)
            System.exit(0);
    }
}
