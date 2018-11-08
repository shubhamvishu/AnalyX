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

public class InsertProduct implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addprod;

    @FXML
    private JFXTextField tpid;

    @FXML
    private JFXTextField tpname;

    @FXML
    private JFXTextField tpcost;

    @FXML
    private JFXTextField tprofit;

    @FXML
    private JFXTextField tcatid;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(tpid.getText());
                String b = tpname.getText();
                System.out.println("b:" + b);
                Integer c = Integer.parseInt(tpcost.getText());
                System.out.println("c:" + c);
                Integer d = Integer.parseInt(tprofit.getText());
                System.out.println("d:" + d);
                Integer e = Integer.parseInt(tcatid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from product";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into product values(" + a + ",'" + b + "'," + c + "," + d + "," + e + ");";
                System.out.println("Query:" + "insert into product values(" + a + ",'" + b + "'," + c + "," + d + "," + e + ");");
                if (b.isEmpty()) {
                    System.out.println("hhijhiuhij");
                }
                if (a != null && !b.isEmpty() && c != null && d != null && e != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                ProductTable.insertproduct.close();


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
                System.out.println("ch=2");
                Integer a = Integer.parseInt(tpid.getText());
                String b = tpname.getText();
                System.out.println("b:" + b);
                Integer c = Integer.parseInt(tpcost.getText());
                System.out.println("c:" + c);
                Integer d = Integer.parseInt(tprofit.getText());
                System.out.println("d:" + d);
                Integer e = Integer.parseInt(tcatid.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from product";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into product values(" + ProductTable.p.getPid() + ",'" + ProductTable.p.getPname()  + "'," + ProductTable.p.getPcost() + "," + ProductTable.p.getProfit() + "," + ProductTable.p.getCatid() + ");";
                System.out.println("Query:" + "insert into product values(" + a + ",'" + b + "'," + c + "," + d + "," + e + ");");
                if (b.isEmpty()) {
                    System.out.println("hhijhiuhij");
                }
                if (a != null && !b.isEmpty() && c != null && d != null && e != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                ProductTable.insertproduct.close();


            } catch (SQLIntegrityConstraintViolationException s)
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
                    st.executeUpdate("delete from product where pid=" + ProductTable.p.getPid());
                    Integer x1 = Integer.parseInt(tpid.getText());
                    String x2 = tpname.getText();
                    Integer x3 = Integer.parseInt(tpcost.getText());
                    Integer x4 = Integer.parseInt(tprofit.getText());
                    Integer x5 = Integer.parseInt(tcatid.getText());
                    System.out.println("insert into product values(" + x1 + ",'" + x2 + "'," + x3 + "," + x4 + "," + x5 + ");");
                    st.executeUpdate("insert into product values(" + x1 + ",'" + x2 + "'," + x3 + "," + x4 + "," + x5 + ");");
                    ProductTable.insertproduct.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from product";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into product values(" + ProductTable.p.getPid() + ",'" + ProductTable.p.getPname() + "'," + ProductTable.p.getPcost() + "," +ProductTable.p.getProfit() + "," + ProductTable.p.getCatid() + ");");
                    st.executeUpdate("insert into product values(" + ProductTable.p.getPid() + ",'" + ProductTable.p.getPname() + "'," + ProductTable.p.getPcost() + "," +ProductTable.p.getProfit() + "," + ProductTable.p.getCatid() + ");");
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
        ProductTable.insertproduct.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ProductTable.p!=null)
        {
            ch=2;
            head.setText("Modify Product");
            tpid.setText(String.valueOf(ProductTable.p.getPid()));
            tpname.setText(String.valueOf(ProductTable.p.getPname()));
            tpcost.setText(String.valueOf(ProductTable.p.getPcost()));
            tprofit.setText(String.valueOf(ProductTable.p.getProfit()));
            tcatid.setText(String.valueOf(ProductTable.p.getCatid()));
        }
        else{
            ch=1;
        }
    }
}
