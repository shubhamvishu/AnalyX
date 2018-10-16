package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerTable implements Initializable {

    public static Stage insert;
    @FXML PieChart pie1;
    @FXML PieChart pie2;
    @FXML TableView<Customer> tablecustomer;
    @FXML
    private TableColumn<Customer, Integer> cid;

    @FXML
    private TableColumn<Customer, String> cname;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableColumn<Customer, String> phno;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Customer> list= FXCollections.observableArrayList(
            new Customer(56,"shubham","yoboy@gmail.com","9620860256")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cid.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("cid"));
        cname.setCellValueFactory(new PropertyValueFactory<Customer,String>("cname"));
        email.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));
        phno.setCellValueFactory(new PropertyValueFactory<Customer,String>("phno"));
        tablecustomer.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tablecustomer.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from customer";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            //System.out.println("name1");
            lab.setText("CONNECTED");
            String sqlq = "insert into customer values(10,\"Zara\",\"abcd@gmail.com\",\"988874874\");";
            Statement st=con.createStatement();
            //ResultSet r=st.executeQuery(sqlq);
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {   System.out.println("IM HERE");

                //else{
                    System.out.println("ABCD");
                    System.out.println(rs.getString("cid") +" "+ rs.getString("cname")+" "+ rs.getString("email") + " " + rs.getString("phno"));
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a=Integer.parseInt(rs.getString("cid"));
                    //System.out.println("C");
                    String b=rs.getString("cname");
                    //System.out.println("D");
                    String c=rs.getString("email");
                    //System.out.println("E");
                    String d=rs.getString("phno");
                    //System.out.println("G");
                    tablecustomer.getItems().add(new Customer(a,b,c,d));
                    //System.out.println("J");
               // }
            }


            st.close();
            try {
                con.close();
            } catch (SQLException e) {

            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            lab.setText("DISCONNECTED");
        }

    }

    public void exec() throws SQLException, ClassNotFoundException {
        //System.out.println("Hereeee");
        String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
        String uname = "root";
        String pass = "sc13111998";
        String query = "select * from customer";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tablecustomer.getItems().clear();
        try {
            System.out.println("A");
            ResultSet rs = st.executeQuery(cmd.getText());
            System.out.println("Meta:"+rs.getMetaData());
            /*while (rs.next())
            {
                System.out.println(rs.getString("cname")+" "+rs.getString("count(cid)"));
            }*/
            System.out.println("C");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("cid"));
                //System.out.println("C");
                String b = rs.getString("cname");
                //System.out.println("D");
                String c = rs.getString("email");
                //System.out.println("E");
                String d = rs.getString("phno");
                //System.out.println("G");
                tablecustomer.getItems().add(new Customer(a,b,c,d));
            }

        } catch (SQLException e) {

            System.out.println("D "+e);
            st.executeUpdate(cmd.getText());
            System.out.println("E");
            ResultSet rs = st.executeQuery("select * from customer");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("cid"));
                //System.out.println("C");
                String b = rs.getString("cname");
                //System.out.println("D");
                String c = rs.getString("email");
                //System.out.println("E");
                String d = rs.getString("phno");
                //System.out.println("G");
                tablecustomer.getItems().add(new Customer(a,b,c,d));

            }


        }
    }
    public void add(ActionEvent event) throws IOException {
        insert = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertcustomer1.fxml"));
        insert.setTitle("New Customer");
        insert.initStyle(StageStyle.UNDECORATED);
        insert.setResizable(false);
        insert.setScene(new Scene(root, 420, 512));
        insert.show();
    }

    @FXML
    public void piedata1(ActionEvent event)
    {
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList(
                new PieChart.Data("Java",60),
                new PieChart.Data("C++",45),
                new PieChart.Data("Python",25)
        );
        pie1.setData(list);

    }
    @FXML
    public void piedata2(ActionEvent event)
    {
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList(
                new PieChart.Data("Java",60),
                new PieChart.Data("C++",45),
                new PieChart.Data("Python",25)
        );
        pie2.setData(list);
    }

}
