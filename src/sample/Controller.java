package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class Controller {

   Main main=new Main();
   public static Stage prim;
    private String username="root";
    private String password="sc13111998";
    @FXML StackPane stackpane;
    @FXML AnchorPane anchor;
    @FXML AnchorPane in;
    @FXML AnchorPane about;
    @FXML AnchorPane mainlog;
    @FXML AnchorPane mainabout;
    @FXML private Label res;
    @FXML public JFXTextField user;
    @FXML public JFXPasswordField pass;
    @FXML public CheckBox checkbox;
    public boolean connec;

    public void login() throws IOException, SQLException, ClassNotFoundException {

        res.setText("");
        connec=false;
        String uname=user.getText();
        String pwd=pass.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stud?allowPublicKeyRetrieval=true&useSSL=false";
            Connection con = DriverManager.getConnection(url, uname, pwd);
            //res.setText("Login successful");
            System.out.println("DB connected");
            connec=true;
            if(!checkbox.isSelected())
            Main.primaryStage.close();
            //TimeUnit.SECONDS.sleep(30);
            //System.out.println("bool:"+connec);
            prim=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXML/opentable.fxml"));
            prim.setTitle("Shubham");
            prim.setResizable(false);
            prim.setScene(new Scene(root, 700, 500));
            prim.show();

        }
        catch (Exception e)
        {
            connec=false;
            System.out.println("DB not connected"+e);
            JFXDialogLayout content=new JFXDialogLayout();
            Label label=new Label(" Invalid username or password......try again");
            label.setStyle("-fx-text-fill:#000;-fx-font-weight:bold;-fx-font-size:20px;-fx-alignment:center;-fx-font-family:Lato;");
            label.setAlignment(Pos.CENTER);
            content.setHeading(label);
            //TextArea textArea=new TextArea(s);
            //textArea.setStyle("-fx-font-weight:bold;");
            content.setBody(label);
            JFXDialog dialog=new JFXDialog(stackpane,content,JFXDialog.DialogTransition.RIGHT);
            content.setStyle("-fx-background-color:#E5E7E9;-fx-pref-width:500px;-fx-pref-height:150px;-fx-text-fill:#ff0000;-fx-text-color:#ff0000;");
            dialog.setContent(content);
            JFXButton button=new JFXButton("OK");
            button.setStyle("-fx-background-color:#16A085;-fx-text-fill:#fff;-fx-font-weight:bold;-fx-pref-width:120px;-fx-pref-height:40px;-fx-background-radius:20px;-fx-border-radius:20px;");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            content.setActions(button);
            dialog.show();
            //res.setText("Invalid username or password");
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
