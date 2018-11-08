package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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

public class InsertBuy implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addbuy;

    @FXML
    private JFXTextField tcid;

    @FXML
    private JFXTextField tpid;

    @FXML
    private JFXTextField tsid;

    @FXML
    private JFXDatePicker tdop;

    @FXML
    private JFXTextField tqty;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                System.out.println("shubham1");
                Integer a = Integer.parseInt(tcid.getText());
                Integer b = Integer.parseInt(tpid.getText());
                Integer c = Integer.parseInt(tsid.getText());
                System.out.println("shubham2");
                LocalDate d = tdop.getValue();
                System.out.println("shubham3");
                System.out.println("date=" + d.toString());
                Integer e = Integer.parseInt(tqty.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from category";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into buy values(" + a + "," + b + "," + c + ",'" + d.toString() + "'," + e + ");";
                System.out.println("Query:" + "insert into buy values(" + a + "," + b + "," + c + ",'" + d.toString() + "'," + e + ");");

                if (a != null && b != null && c != null && d != null && e != null)
                    st.executeUpdate(q);
                else
                    throw new Exception();
                BuyTable.insertbuy.close();


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
                System.out.println("shubham1");
                Integer a = Integer.parseInt(tcid.getText());
                Integer b = Integer.parseInt(tpid.getText());
                Integer c = Integer.parseInt(tsid.getText());
                System.out.println("shubham2");
                LocalDate d = tdop.getValue();
                System.out.println("shubham3");
                System.out.println("date=" + d.toString());
                Integer e = Integer.parseInt(tqty.getText());
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from category";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                System.out.println("DQ : delete from buy where cid="+BuyTable.b.getCid()+" and pid="+BuyTable.b.getPid()+" and sid="+BuyTable.b.getSid()+" and dop='"+BuyTable.b.getDop()+"' and qty="+BuyTable.b.getQty()+";");
                String delq="delete from buy where cid="+BuyTable.b.getCid()+" and pid="+BuyTable.b.getPid()+" and sid="+BuyTable.b.getSid()+" and dop='"+BuyTable.b.getDop()+"' and qty="+BuyTable.b.getQty()+";";
                st.executeUpdate(delq);
                String q = "insert into buy values(" + a + "," + b + "," + c + ",'" + d.toString() + "'," + e + ");";
                System.out.println("Query:" + "insert into buy values(" + a + "," + b + "," + c + ",'" + d.toString() + "'," + e + ");");

                if (a != null && b != null && c != null && d != null && e != null) {
                    st.executeUpdate(q);
                    System.out.println("updated");
                    BuyTable.b=null;
                }
                else
                    throw new Exception();
                BuyTable.insertbuy.close();


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
                st.executeUpdate("insert into buy values(" + BuyTable.b.getCid() + "," + BuyTable.b.getPid() + "," + BuyTable.b.getSid() + ",'" + BuyTable.b.getDop() + "'," + BuyTable.b.getQty() + ");");
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
        BuyTable.insertbuy.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(BuyTable.b!=null)
        {
            ch=2;
            head.setText("Modify Purchase");
            tcid.setText(String.valueOf(BuyTable.b.getCid()));
            tpid.setText(String.valueOf(BuyTable.b.getPid()));
            tsid.setText(String.valueOf(BuyTable.b.getSid()));
            tdop.setValue(LocalDate.parse(BuyTable.b.getDop()));
            tqty.setText(String.valueOf(BuyTable.b.getQty()));
        }
        else{
            ch=1;
        }
    }
}
