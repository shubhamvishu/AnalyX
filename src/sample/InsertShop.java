package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InsertShop implements Initializable {

    public int ch;
    @FXML
    Label head;
    @FXML
    private JFXButton addshop;

    @FXML
    private JFXTextField tsid;

    @FXML
    private JFXTextField tsname;

    @FXML
    private JFXTextField trev;

    @FXML
    private JFXTextField tcatid;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(tsid.getText());
                String b = tsname.getText();
                Integer c = Integer.parseInt(trev.getText());
                Integer d = Integer.parseInt(tcatid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from shop";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into shop values(" + a + ",'" + b + "'," + c + "," + d + ");";
                System.out.println("Query:" + "insert into shop values(\"+a+\",'\"+b+\"',\"+c+\",'\"+d+\");");

                if (a != null && !b.isEmpty() && c != null && d != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                ShopTable.insertshop.close();


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
                Integer a = Integer.parseInt(tsid.getText());
                String b = tsname.getText();
                Integer c = Integer.parseInt(trev.getText());
                Integer d = Integer.parseInt(tcatid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from shop";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into shop values(" + a + ",'" + b + "'," + c + "," + d + ");";
                System.out.println("Query:" + "insert into shop values(\"+a+\",'\"+b+\"',\"+c+\",'\"+d+\");");

                if (a != null && !b.isEmpty() && c != null && d != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                ShopTable.insertshop.close();


            }
            catch (SQLIntegrityConstraintViolationException s)
            {
                System.out.println("Integrity"+s);
                try {
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from shop";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    st.executeUpdate("delete from shop where sid=" + ShopTable.s.getSid());
                    Integer x1 = Integer.parseInt(tsid.getText());
                    String x2 = tsname.getText();
                    Integer x3 = Integer.parseInt(trev.getText());
                    Integer x4 = Integer.parseInt(tcatid.getText());
                    System.out.println("insert into shop values(" + x1 + ",'" + x2 + "'," + x3 + ","+x4+");");
                    st.executeUpdate("insert into shop values(" + x1 + ",'" + x2 + "'," + x3 + ","+x4+");");
                    ShopTable.insertshop.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from shop";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into shop values(" + ShopTable.s.getSid() + ",'" + ShopTable.s.getSname() + "'," + ShopTable.s.getRev() +","+ShopTable.s.getCatid()+");");
                    st.executeUpdate("insert into shop values(" + ShopTable.s.getSid() + ",'" + ShopTable.s.getSname() + "'," + ShopTable.s.getRev() +","+ShopTable.s.getCatid()+");");
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("OOPS!!!");
                    al.setHeaderText(null);
                    al.setContentText("Unable to insert the record");
                    al.showAndWait();
                    //TimeUnit.SECONDS.sleep(3);
                    al.close();
                }
            }
            catch (Exception e) {
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
        ShopTable.insertshop.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ShopTable.s!=null)
        {
            ch=2;
            head.setText("Modify Shop");
            tsid.setText(String.valueOf(ShopTable.s.getSid()));
            tsname.setText(String.valueOf(ShopTable.s.getSname()));
            trev.setText(String.valueOf(ShopTable.s.getRev()));
            tcatid.setText(String.valueOf(ShopTable.s.getCatid()));
        }
        else{
            ch=1;
        }
    }
}
