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

public class InsertBuy {


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
        try {
            System.out.println("shubham1");
            Integer a=Integer.parseInt(tcid.getText());
            Integer b=Integer.parseInt(tpid.getText());
            Integer c=Integer.parseInt(tsid.getText());
            System.out.println("shubham2");
            LocalDate d=tdop.getValue();
            System.out.println("shubham3");
            System.out.println("date="+d.toString());
            Integer e=Integer.parseInt(tqty.getText());
            String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname = "root";
            String pass = "sc13111998";
            String query = "select * from category";
            //Connection conn=Connect.getconnected();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            String q="insert into buy values("+a+","+b+","+c+",'"+d.toString()+"',"+e+");";
            System.out.println("Query:"+"insert into buy values("+a+","+b+","+c+",'"+d.toString()+"',"+e+");");

            if(a!=null && b!=null && c!=null && d!=null && e!=null)
             st.executeUpdate(q);
            else
                throw new Exception();
            BuyTable.insertbuy.close();


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
        BuyTable.insertbuy.close();
    }

}
