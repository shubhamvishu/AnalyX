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

public class InsertProduct {


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
        try {
            Integer a=Integer.parseInt(tpid.getText());
            String b=tpname.getText();
            System.out.println("b:"+b);
            Integer c=Integer.parseInt(tpcost.getText());
            System.out.println("c:"+c);
            Integer d=Integer.parseInt(tprofit.getText());
            System.out.println("d:"+d);
            Integer e=Integer.parseInt(tcatid.getText());
            String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname = "root";
            String pass = "sc13111998";
            String query = "select * from product";
            //Connection conn=Connect.getconnected();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            String q="insert into product values("+a+",'"+b+"',"+c+","+d+","+e+");";
            System.out.println("Query:"+"insert into product values("+a+",'"+b+"',"+c+","+d+","+e+");");
            if(b.isEmpty())
            {
                System.out.println("hhijhiuhij");
            }
            if(a!=null && !b.isEmpty() && c!=null && d!=null && e!=null)
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
        ProductTable.insertproduct.close();
        OpentableController.prim.setOpacity(1.0);
    }

}
