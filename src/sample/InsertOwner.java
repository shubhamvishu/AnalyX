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

public class InsertOwner implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addowner;

    @FXML
    private JFXTextField toid;

    @FXML
    private JFXTextField toname;

    @FXML
    private JFXTextField tphno;

    @FXML
    private JFXTextField taddress;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(toid.getText());
                String b = toname.getText();
                String c = tphno.getText();
                String d = taddress.getText();
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from owner";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into owner values(" + a + ",'" + b + "','" + c + "','" + d + "');";
                System.out.println("Query:" + "insert into owner values(\"+a+\",'\"+b+\"','\"+c+\"','\"+d+\"');");
                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                OwnerTable.insertowner.close();


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
                String b = toname.getText();
                String c = tphno.getText();
                String d = taddress.getText();
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from owner";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into owner values(" + a + ",'" + b + "','" + c + "','" + d + "');";
                System.out.println("Query:" + "insert into owner values(\"+a+\",'\"+b+\"','\"+c+\"','\"+d+\"');");
                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                OwnerTable.insertowner.close();


            }
            catch (SQLIntegrityConstraintViolationException s)
            {
                System.out.println("Integrity"+s);
                try {
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from customer";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    st.executeUpdate("delete from owner where oid=" + OwnerTable.o.getOid());
                    Integer x1 = Integer.parseInt(toid.getText());
                    String x2 = toname.getText();
                    String x3 = tphno.getText();
                    String x4=taddress.getText();
                    System.out.println("insert into owner values(" + x1 + ",'" + x2 + "','" + x3 + "','"+x4+"');");
                    st.executeUpdate("insert into owner values(" + x1 + ",'" + x2 + "','" + x3 + "','"+x4+"');");
                    OwnerTable.insertowner.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from owner";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into owner values(" + OwnerTable.o.getOid() + ",'" + OwnerTable.o.getOname() + "','" + OwnerTable.o.getPhno() +"','"+OwnerTable.o.getAddress()+"');");
                    st.executeUpdate("insert into owner values(" + OwnerTable.o.getOid() + ",'" + OwnerTable.o.getOname() + "','" + OwnerTable.o.getPhno() +"','"+OwnerTable.o.getAddress()+"');");
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
        OwnerTable.insertowner.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(OwnerTable.o!=null)
        {
            ch=2;
            head.setText("Modify Owner");
            toid.setText(String.valueOf(OwnerTable.o.getOid()));
            toname.setText(String.valueOf(OwnerTable.o.getOname()));
            tphno.setText(String.valueOf(OwnerTable.o.getPhno()));
            taddress.setText(String.valueOf(OwnerTable.o.getAddress()));
        }
        else{
            ch=1;
        }
    }
}
