package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertCustomer {


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
        System.out.println("shubham");
        try {
            Integer a=Integer.parseInt(tcid.getText());
            String b=tcname.getText();
            System.out.println("b:"+b);
            String c=temail.getText();
            System.out.println("c:"+c);
            String d=tphno.getText();
            System.out.println("d:"+d);
            String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname = "root";
            String pass = "sc13111998";
            String query = "select * from customer";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            String q="insert into customer values("+a+",'"+b+"','"+c+"','"+d+"');";
            System.out.println("Query:"+"insert into customer values("+a+",'"+b+"','"+c+"','"+d+"');");

            if(a!=null && !b.isEmpty() && !c.isEmpty() && !d.isEmpty())
            {
                st.executeUpdate(q);
                //SendMail sm=new SendMail(temail.getText());
                //SendSms sms=new SendSms("9876543210","Thankyou dear customer for choosing galleria......hope to see u soon");
            }
            else
                throw new Exception();
            CustomerTable.insert.close();


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
        CustomerTable.insert.close();

    }

}
