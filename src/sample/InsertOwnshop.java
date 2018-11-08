package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InsertOwnshop implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addownshop;

    @FXML
    private JFXTextField toid;

    @FXML
    private JFXTextField tsid;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(toid.getText());
                Integer b = Integer.parseInt(tsid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from ownshop";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into ownshop values(" + a + "," + b + ");";
                System.out.println("Query1:" + "insert into ownshop values("+a+","+b+");");
                if (a != null && b != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                OwnshopTable.insertownshop.close();


            } catch (Exception e) {
                System.out.println("Ex:" + e);
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("Unable to insert the record");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }
        }
        else{
            try {
                Integer a = Integer.parseInt(toid.getText());
                Integer b = Integer.parseInt(tsid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from ownshop";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String delq="delete from ownshop where oid="+OwnshopTable.own.getOid()+" and sid="+OwnshopTable.own.getSid()+";";
                st.executeUpdate(delq);
                String q = "insert into ownshop values(" + a + "," + b + ");";
                System.out.println("Query Main:" + "insert into ownshop values("+a+","+b+");");
                if (a != null && b != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                OwnshopTable.insertownshop.close();


            }
            catch (NumberFormatException ex)
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("Unable to insert the record");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();

            }
            catch (Exception e) {
                System.out.println("Ex:" + e);
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from category";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                st.executeUpdate("insert into ownshop values(" + OwnshopTable.own.getOid() + "," + OwnshopTable.own.getSid() + ");");
                System.out.println("Ex:" + e);
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("Unable to insert the record");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }
        }


    }
    public void cancelInsert(ActionEvent event)
    {
        OwnshopTable.insertownshop.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(OwnshopTable.own!=null)
        {
            ch=2;
            head.setText("Modify Record");
            System.out.println("asdfghjkl");
            toid.setText(String.valueOf(OwnshopTable.own.getOid()));
            System.out.println("A:"+String.valueOf(OwnshopTable.own.getOid()));
            tsid.setText(String.valueOf(OwnshopTable.own.getSid()));
            System.out.println("B:"+String.valueOf(OwnshopTable.own.getSid()));

        }
        else{
            ch=1;
        }
    }
}
