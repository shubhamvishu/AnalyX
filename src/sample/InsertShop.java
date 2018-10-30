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

public class InsertShop {


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
        try {
            Integer a=Integer.parseInt(tsid.getText());
            String b=tsname.getText();
            Integer c=Integer.parseInt(trev.getText());
            Integer d=Integer.parseInt(tcatid.getText());
            String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname = "root";
            String pass = "sc13111998";
            String query = "select * from shop";
            //Connection conn=Connect.getconnected();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            String q="insert into shop values("+a+",'"+b+"',"+c+","+d+");";
            System.out.println("Query:"+"insert into shop values(\"+a+\",'\"+b+\"',\"+c+\",'\"+d+\");");

            if(a!=null && !b.isEmpty() && c!=null && d!=null)
             st.executeUpdate(q);
            else
                throw new Exception();
            ShopTable.insertshop.close();


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
        ShopTable.insertshop.close();
    }

}
