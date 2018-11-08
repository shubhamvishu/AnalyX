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
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InsertEmployee implements Initializable {

    public int ch;
    @FXML
    private Label head;
    @FXML
    private JFXButton addemp;

    @FXML
    private JFXTextField teid;

    @FXML
    private JFXTextField tename;

    @FXML
    private JFXTextField tphno;

    @FXML
    private JFXTextField taddress;

    @FXML
    private JFXTextField tsal;

    @FXML
    private JFXTextField tup;

    @FXML
    private JFXTextField tdown;

    @FXML
    private JFXTextField tsid;

    @FXML
    private JFXDatePicker tdoj;

    @FXML
    private JFXButton cancel;

    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("shubham");
        if(ch==1) {
            try {
                Integer a = Integer.parseInt(teid.getText());
                String b = tename.getText();
                String c = tphno.getText();
                String d = taddress.getText();
                Integer e = Integer.parseInt(tsal.getText());
                Integer f = Integer.parseInt(tup.getText());
                Integer g = Integer.parseInt(tdown.getText());
                Integer h = Integer.parseInt(tsid.getText());
                LocalDate i = tdoj.getValue();
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from employee";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into employee values(" + a + ",'" + b + "','" + c + "','" + d + "'," + e + "," + f + "," + g + "," + h + ",'" + i + "');";
                System.out.println("Query:" + "insert into employee values(\"+a+\",'\"+b+\"','\"+c+\"','\"+d+\"',\"+e+\",\"+f+\",\"+g+\",\"+h+\",'\"+i+\"');");
                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty() && e != null && f != null && g != null && h != null && !i.toString().isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                EmployeeTable.insertemployee.close();


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
                Integer a = Integer.parseInt(teid.getText());
                String b = tename.getText();
                String c = tphno.getText();
                String d = taddress.getText();
                Integer e = Integer.parseInt(tsal.getText());
                Integer f = Integer.parseInt(tup.getText());
                Integer g = Integer.parseInt(tdown.getText());
                Integer h = Integer.parseInt(tsid.getText());
                LocalDate i = tdoj.getValue();
                String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                String uname = "root";
                String pass = "sc13111998";
                String query = "select * from employee";
                //Connection conn=Connect.getconnected();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                String q = "insert into employee values(" + a + ",'" + b + "','" + c + "','" + d + "'," + e + "," + f + "," + g + "," + h + ",'" + i + "');";
                System.out.println("Query:" + "insert into employee values(\"+a+\",'\"+b+\"','\"+c+\"','\"+d+\"',\"+e+\",\"+f+\",\"+g+\",\"+h+\",'\"+i+\"');");
                if (a != null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty() && e != null && f != null && g != null && h != null && !i.toString().isEmpty())
                    st.executeUpdate(q);
                else
                    throw new Exception();
                EmployeeTable.insertemployee.close();


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
                    st.executeUpdate("delete from employee where eid=" + EmployeeTable.e.getEid());
                    Integer x1 = Integer.parseInt(teid.getText());
                    String x2 = tename.getText();
                    String x3 = tphno.getText();
                    String x4 = taddress.getText();
                    Integer x5 = Integer.parseInt(tsal.getText());
                    Integer x6 = Integer.parseInt(tup.getText());
                    Integer x7 = Integer.parseInt(tdown.getText());
                    Integer x8 = Integer.parseInt(tsid.getText());
                    LocalDate x9=tdoj.getValue();
                    System.out.println("insert into employee values("+x1+",'"+x2+"','"+x3+"','"+x4+"',"+x5+","+x6+","+x7+","+x8+",'"+x9.toString()+"');");
                    st.executeUpdate("insert into employee values("+x1+",'"+x2+"','"+x3+"','"+x4+"',"+x5+","+x6+","+x7+","+x8+",'"+x9.toString()+"');");
                    EmployeeTable.insertemployee.close();
                    System.out.println("WRONGGGG");
                }
                catch (Exception ex)
                {
                    System.out.println("Exception:"+ex);
                    String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
                    String uname = "root";
                    String pass = "sc13111998";
                    String query = "select * from employee";
                    //Connection conn=Connect.getconnected();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    System.out.println("insert into employee values("+EmployeeTable.e.getEid()+",'"+EmployeeTable.e.getEname()+"','"+EmployeeTable.e.getPhno()+"','"+EmployeeTable.e.getAddress()+"',"+EmployeeTable.e.getSal()+","+EmployeeTable.e.getUp()+","+EmployeeTable.e.getDown()+","+EmployeeTable.e.getSid()+",'"+EmployeeTable.e.getDoj()+"');");
                    st.executeUpdate("insert into employee values("+EmployeeTable.e.getEid()+",'"+EmployeeTable.e.getEname()+"','"+EmployeeTable.e.getPhno()+"','"+EmployeeTable.e.getAddress()+"',"+EmployeeTable.e.getSal()+","+EmployeeTable.e.getUp()+","+EmployeeTable.e.getDown()+","+EmployeeTable.e.getSid()+",'"+EmployeeTable.e.getDoj()+"');");
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
        EmployeeTable.insertemployee.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(EmployeeTable.e!=null)
        {
            ch=2;
            head.setText("Modify Employee");
            teid.setText(String.valueOf(EmployeeTable.e.getEid()));
            tename.setText(String.valueOf(EmployeeTable.e.getEname()));
            tphno.setText(String.valueOf(EmployeeTable.e.getPhno()));
            taddress.setText(String.valueOf(EmployeeTable.e.getAddress()));
            tsal.setText(String.valueOf(EmployeeTable.e.getSal()));
            tup.setText(String.valueOf(EmployeeTable.e.getUp()));
            tdown.setText(String.valueOf(EmployeeTable.e.getDown()));
            tsid.setText(String.valueOf(EmployeeTable.e.getSid()));
            tdoj.setValue(LocalDate.parse(EmployeeTable.e.getDoj()));
        }
        else{
            ch=1;
        }
    }
}
