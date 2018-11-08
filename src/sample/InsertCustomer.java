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

public class InsertCustomer implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addcust;

    @FXML
    private JFXTextField tcid;

    @FXML
    private JFXTextField tcname;

    @FXML
    private JFXTextField temail;

    @FXML
    private JFXTextField tphno;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(ch==1) {
            System.out.println("shubham");
            try {
                Integer a = Integer.parseInt(tcid.getText());
                String b = tcname.getText();
                System.out.println("b:" + b);
                String c = temail.getText();
                System.out.println("c:" + c);
                String d = tphno.getText();
                System.out.println("d:" + d);
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from customer";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into customer values(" + a + ",'" + b + "','" + c + "','" + d + "');";
                System.out.println("Query:" + "insert into customer values(" + a + ",'" + b + "','" + c + "','" + d + "');");

                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty()) {
                    st.executeUpdate(q);
                    //SendMail sm=new SendMail(temail.getText());
                    //SendSms sms=new SendSms("9876543210","Thankyou dear customer for choosing galleria......hope to see u soon");
                } else
                    throw new Exception();
                CustomerTable.insert.close();


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
                Integer a = Integer.parseInt(tcid.getText());
                String b = tcname.getText();
                System.out.println("b:" + b);
                String c = temail.getText();
                System.out.println("c:" + c);
                String d = tphno.getText();
                System.out.println("d:" + d);
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from customer";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into customer values(" + a + ",'" + b + "','" + c + "','" + d + "');";
                System.out.println("Query:" + "insert into customer values(" + a + ",'" + b + "','" + c + "','" + d + "');");

                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty()) {
                    st.executeUpdate(q);
                    //SendMail sm=new SendMail(temail.getText());
                    //SendSms sms=new SendSms("9876543210","Thankyou dear customer for choosing galleria......hope to see u soon");
                } else
                    throw new Exception();
                CustomerTable.insert.close();


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
                    st.executeUpdate("delete from customer where cid=" + CustomerTable.c.getCid());
                    Integer x1 = Integer.parseInt(tcid.getText());
                    String x2 = tcname.getText();
                    String x3 = temail.getText();
                    String x4 = tphno.getText();
                    System.out.println("insert into customer values(" + x1 + ",'" + x2 + "','" + x3 + "','"+x4+"');");
                    st.executeUpdate("insert into customer values(" + x1 + ",'" + x2 + "','" + x3 + "','"+x4+"');");
                    CustomerTable.insert.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from customer";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into customer values(" + CustomerTable.c.getCid() + ",'" + CustomerTable.c.getCname() + "','" + CustomerTable.c.getEmail() +"','"+CustomerTable.c.getPhno()+"');");
                    st.executeUpdate("insert into customer values(" + CustomerTable.c.getCid() + ",'" + CustomerTable.c.getCname() + "','" + CustomerTable.c.getEmail() +"','"+CustomerTable.c.getPhno()+"');");
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
        CustomerTable.insert.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CustomerTable.c!=null)
        {
            ch=2;
            head.setText("Modify Customer");
            tcid.setText(String.valueOf(CustomerTable.c.getCid()));
            tcname.setText(String.valueOf(CustomerTable.c.getCname()));
            temail.setText(String.valueOf(CustomerTable.c.getEmail()));
            tphno.setText(String.valueOf(CustomerTable.c.getPhno()));
        }
        else{
            ch=1;
        }
    }
}
