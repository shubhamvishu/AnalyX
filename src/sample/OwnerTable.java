package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class OwnerTable implements Initializable {

    public static Stage insertowner;

    @FXML
    TableView<Owner> tableowner;
    @FXML
    private TableColumn<Owner, Integer> oid;

    @FXML
    private TableColumn<Owner, String> oname;

    @FXML
    private TableColumn<Owner, String> phno;

    @FXML
    private TableColumn<Owner, String> address;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Owner> list= FXCollections.observableArrayList(
            new Owner(10,"abc","32312412","delhi")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oid.setCellValueFactory(new PropertyValueFactory<Owner,Integer>("oid"));
        oname.setCellValueFactory(new PropertyValueFactory<Owner,String>("oname"));
        phno.setCellValueFactory(new PropertyValueFactory<Owner,String>("phno"));
        address.setCellValueFactory(new PropertyValueFactory<Owner,String>("address"));
        tableowner.setCenterShape(true);
        tableowner.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tableowner.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from owner";

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
              /*  if(rs.getString("lname")==null) {
                    System.out.println("SHUBH");
                    System.out.println(rs.getString("usn") + " " + rs.getString("fname") + rs.getString("lname") + rs.getString("city")+" "+rs.getString("mobno")+" "+rs.getString("dob"));
                    table.getItems().add(new Stud("1NH16CS757","cshubham","cstud",5,"Bangalore", 973959087,"1998-11-13"));
                    table.getItems().add(new Stud(rs.getString("usn").toString(), rs.getString("fname").toString(), rs.getString("lname").toString(),Integer.parseInt(rs.getString("sem")), rs.getString("city").toString(), Long.parseLong("mobno"), rs.getString("dob").toString()));
                }*/
                //else{
                    Integer a=Integer.parseInt(rs.getString("oid"));
                    String b=rs.getString("oname");
                    String c=rs.getString("phno");
                    String d=rs.getString("address");
                    tableowner.getItems().add(new Owner(a,b,c,d));

                    //System.out.println("J");
               // }
            }

            //System.out.println("name2");
            // st.executeUpdate(sqlq);
            // ResultSet r=st.executeQuery(query);
            // while(r.next())
            // {
            //     table.getItems().add(new Stud(Integer.parseInt(r.getString("id")),r.getString("name"),Integer.parseInt(r.getString("marks"))));
            // }
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
        String query = "select * from owner";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tableowner.getItems().clear();
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
                Integer a=Integer.parseInt(rs.getString("oid"));
                String b=rs.getString("oname");
                String c=rs.getString("phno");
                String d=rs.getString("address");
                tableowner.getItems().add(new Owner(a,b,c,d));
            }

        } catch (SQLException ex) {

            System.out.println("D "+ex);
            st.executeUpdate(cmd.getText());
            System.out.println("E");
            ResultSet rs = st.executeQuery("select * from owner");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a=Integer.parseInt(rs.getString("oid"));
                String b=rs.getString("oname");
                String c=rs.getString("phno");
                String d=rs.getString("address");
                tableowner.getItems().add(new Owner(a,b,c,d));

            }


        }
    }
    public void add(ActionEvent event) throws IOException {
        insertowner = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertowner1.fxml"));
        insertowner.setTitle("New Product");
        insertowner.initStyle(StageStyle.UNDECORATED);
        insertowner.setResizable(false);
        insertowner.setScene(new Scene(root, 444, 555));
        insertowner.show();
    }


}
