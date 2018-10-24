package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class InsertEmployee {


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
        try {
            Integer a=Integer.parseInt(teid.getText());
            String b=tename.getText();
            String c=tphno.getText();
            String d=taddress.getText();
            Integer e=Integer.parseInt(tsal.getText());
            Integer f=Integer.parseInt(tup.getText());
            Integer g=Integer.parseInt(tdown.getText());
            Integer h=Integer.parseInt(tsid.getText());
            LocalDate i=tdoj.getValue();
            String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname = "root";
            String pass = "sc13111998";
            String query = "select * from employee";
            //Connection conn=Connect.getconnected();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            String q="insert into employee values("+a+",'"+b+"','"+c+"','"+d+"',"+e+","+f+","+g+","+h+",'"+i+"');";
            System.out.println("Query:"+"insert into employee values(\"+a+\",'\"+b+\"','\"+c+\"','\"+d+\"',\"+e+\",\"+f+\",\"+g+\",\"+h+\",'\"+i+\"');");
            if(a!=null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty() && e!=null && f!=null && g!=null && h!=null && !i.toString().isEmpty())
             st.executeUpdate(q);
            else
                throw new Exception();
            OpentableController.prim.setOpacity(1.0);


        }
        catch (Exception e)
        {
            System.out.println("Ex:"+e);
            Alert al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("Unable to insert the record");
            al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            al.close();
        }


    }
    public void cancelInsert(ActionEvent event)
    {
        EmployeeTable.insertemployee.close();
        OpentableController.prim.setOpacity(1.0);
    }

}
