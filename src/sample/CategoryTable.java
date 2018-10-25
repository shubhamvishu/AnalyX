package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CategoryTable implements Initializable {

    public static Stage insertcategory;

    @FXML TableView<Category> tablecategory;
    @FXML
    private TableColumn<Category, Integer> catid;

    @FXML
    private TableColumn<Category, String> catname;

    @FXML
    private TableColumn<Category, String> desp;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Category> list= FXCollections.observableArrayList(
            new Category(56,"shubham","yoyoyoyoyoyoyo")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catid.setCellValueFactory(new PropertyValueFactory<Category,Integer>("catid"));
        catname.setCellValueFactory(new PropertyValueFactory<Category,String>("catname"));
        desp.setCellValueFactory(new PropertyValueFactory<Category,String>("Desp"));
        tablecategory.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tablecategory.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from category";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            //System.out.println("name1");
            lab.setText("CONNECTED");
            String sqlq = "insert into category values(10,\"Zara\",\"abcd@gmail.com\",\"988874874\");";
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
                    System.out.println("ABCD");
                    System.out.println(rs.getString("catid") +" "+ rs.getString("catname")+" "+ rs.getString("desp") );
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a=Integer.parseInt(rs.getString("catid"));
                    //System.out.println("C");
                    String b=rs.getString("catname");
                    //System.out.println("D");
                    String c=rs.getString("desp");
                    //System.out.println("E");
                    tablecategory.getItems().add(new Category(a,b,c));
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
        String query = "select * from category";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tablecategory.getItems().clear();
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
                //System.out.println(rs.getString("catid") +" "+ rs.getString("catname")+" "+ rs.getString("desp") );
                //System.out.println("A");
                //System.out.println("B");
                Integer a=Integer.parseInt(rs.getString("catid"));
                //System.out.println("C");
                String b=rs.getString("catname");
                //System.out.println("D");
                String c=rs.getString("desp");
                //System.out.println("E");
                tablecategory.getItems().add(new Category(a,b,c));
            }

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tablecategory.getItems().clear();
            ResultSet rs = st.executeQuery("select * from category");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                System.out.println(rs.getString("catid") + " " + rs.getString("catname") + " " + rs.getString("desp"));
                //System.out.println("A");
                //System.out.println("B");
                Integer a = Integer.parseInt(rs.getString("catid"));
                //System.out.println("C");
                String b = rs.getString("catname");
                //System.out.println("D");
                String c = rs.getString("desp");
                //System.out.println("E");
                tablecategory.getItems().add(new Category(a, b, c));

            }
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("WRONG SYNTAX !!!");
            al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            al.close();
        }
        catch (SQLException e) {

            try {
                System.out.println("D " + e);
                st.executeUpdate(cmd.getText());
                System.out.println("E");
                ResultSet rs = st.executeQuery("select * from category");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    System.out.println(rs.getString("catid") + " " + rs.getString("catname") + " " + rs.getString("desp"));
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a = Integer.parseInt(rs.getString("catid"));
                    //System.out.println("C");
                    String b = rs.getString("catname");
                    //System.out.println("D");
                    String c = rs.getString("desp");
                    //System.out.println("E");
                    tablecategory.getItems().add(new Category(a, b, c));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tablecategory.getItems().clear();
                ResultSet rs = st.executeQuery("select * from category");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    System.out.println(rs.getString("catid") + " " + rs.getString("catname") + " " + rs.getString("desp"));
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a = Integer.parseInt(rs.getString("catid"));
                    //System.out.println("C");
                    String b = rs.getString("catname");
                    //System.out.println("D");
                    String c = rs.getString("desp");
                    //System.out.println("E");
                    tablecategory.getItems().add(new Category(a, b, c));

                }
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("WRONG SYNTAX !!!");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }


        }
    }
    public void add(ActionEvent event) throws IOException {
        insertcategory = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertcategory1.fxml"));
        insertcategory.setTitle("New Category");
        insertcategory.initStyle(StageStyle.UNDECORATED);
        insertcategory.setResizable(false);
        insertcategory.setScene(new Scene(root, 420, 512));
        insertcategory.show();
    }



}
