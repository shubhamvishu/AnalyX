package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InsertCategory implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addcat;

    @FXML
    private JFXTextField tcatid;

    @FXML
    private JFXTextField tcatname;

    @FXML
    private JFXTextArea tdesp;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(tcatid.getText());
                String b = tcatname.getText();
                System.out.println("b:" + b);
                String c = tdesp.getText();
                System.out.println("c:" + c);
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from category";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into category values(" + a + ",'" + b + "','" + c + "');";
                System.out.println("Query:" + "insert into category values(" + a + ",'" + b + "','" + c + "');");

                if (a != null && !b.isEmpty() && !c.isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                CategoryTable.insertcategory.close();


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
                Integer a = Integer.parseInt(tcatid.getText());
                String b = tcatname.getText();
                System.out.println("b:" + b);
                String c = tdesp.getText();
                System.out.println("c:" + c);
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from category";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into category values(" + a + ",'" + b + "','" + c + "');";
                System.out.println("Query:" + "insert into category values(" + a + ",'" + b + "','" + c + "');");

                if (a != null && !b.isEmpty() && !c.isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                CategoryTable.insertcategory.close();


            }
            catch (SQLIntegrityConstraintViolationException s)
            {
                System.out.println("Integrity"+s);
                try {
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from product";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    st.executeUpdate("delete from category where catid=" + CategoryTable.cat.getCatid());
                    Integer x1 = Integer.parseInt(tcatid.getText());
                    String x2 = tcatname.getText();
                    String x3 = tdesp.getText();
                    System.out.println("insert into category values(" + x1 + ",'" + x2 + "','" + x3 + "');");
                    st.executeUpdate("insert into category values(" + x1 + ",'" + x2 + "','" + x3 + "');");
                    BuyTable.insertbuy.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from category";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into category values(" + CategoryTable.cat.getCatid() + ",'" + CategoryTable.cat.getCatname() + "','" + CategoryTable.cat.getDesp() +"');");
                    st.executeUpdate("insert into category values(" + CategoryTable.cat.getCatid() + ",'" + CategoryTable.cat.getCatname() + "','" + CategoryTable.cat.getDesp() +"');");
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
        CategoryTable.insertcategory.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CategoryTable.cat!=null)
        {
            ch=2;
            head.setText("Modify Category");
            tcatid.setText(String.valueOf(CategoryTable.cat.getCatid()));
            tcatname.setText(String.valueOf(CategoryTable.cat.getCatname()));
            tdesp.setText(String.valueOf(CategoryTable.cat.getDesp()));
        }
        else{
            ch=1;
        }
    }
}
