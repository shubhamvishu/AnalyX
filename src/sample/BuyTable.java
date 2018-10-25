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

public class BuyTable implements Initializable {

    public static Stage insertbuy;

    @FXML TableView<Buy> tablebuy;
    @FXML private TableColumn<Buy, Integer> cid;
    @FXML private TableColumn<Buy, Integer> pid;
    @FXML private TableColumn<Buy, Integer> sid;
    @FXML private TableColumn<Buy, String> dop;
    @FXML private TableColumn<Buy, Integer> qty;
    @FXML private Label lab;
    @FXML
    private TextField cmd;

    public ObservableList<Buy> list= FXCollections.observableArrayList(
            new Buy(1,2,3, "2018-09-09",5)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cid.setCellValueFactory(new PropertyValueFactory<Buy,Integer>("cid"));
        pid.setCellValueFactory(new PropertyValueFactory<Buy,Integer>("pid"));
        sid.setCellValueFactory(new PropertyValueFactory<Buy,Integer>("sid"));
        dop.setCellValueFactory(new PropertyValueFactory<Buy,String>("dop"));
        qty.setCellValueFactory(new PropertyValueFactory<Buy,Integer>("qty"));
        tablebuy.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {
        System.out.println("in the buy table1");
        tablebuy.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from buy";
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
                    System.out.println(rs.getString("cid") +" "+ rs.getString("pid")+" "+ rs.getString("sid")+" "+ rs.getString("dop")+" "+ rs.getString("qty") );
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a=Integer.parseInt(rs.getString("cid"));
                    //System.out.println("C");
                    Integer b=Integer.parseInt(rs.getString("pid"));
                    //System.out.println("C");
                    Integer c=Integer.parseInt(rs.getString("sid"));
                    //System.out.println("C");
                    String d=rs.getString("dop");
                    //System.out.println("C");
                    Integer e=Integer.parseInt(rs.getString("qty"));
                    //System.out.println("C");
                    tablebuy.getItems().add(new Buy(a,b,c,d,e));
                    //System.out.println("J");
               // }
            }
            System.out.println("in the buy table3");
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
        String query = "select * from buy";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tablebuy.getItems().clear();
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
                Integer a=Integer.parseInt(rs.getString("cid"));
                //System.out.println("C");
                Integer b=Integer.parseInt(rs.getString("pid"));
                //System.out.println("C");
                Integer c=Integer.parseInt(rs.getString("sid"));
                //System.out.println("C");
                String d=rs.getString("dop");
                //System.out.println("C");
                Integer e=Integer.parseInt(rs.getString("qty"));
                //System.out.println("C");
                tablebuy.getItems().add(new Buy(a,b,c,d,e));
            }

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tablebuy.getItems().clear();
            ResultSet rs = st.executeQuery("select * from buy");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("cid"));
                //System.out.println("C");
                Integer b = Integer.parseInt(rs.getString("pid"));
                //System.out.println("C");
                Integer c = Integer.parseInt(rs.getString("sid"));
                //System.out.println("C");
                String d = rs.getString("dop");
                //System.out.println("C");
                Integer e = Integer.parseInt(rs.getString("qty"));
                //System.out.println("C");
                tablebuy.getItems().add(new Buy(a, b, c, d, e));

            }
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("WRONG SYNTAX !!!");
            al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            al.close();
        }
        catch (SQLException ex) {

            try {
                System.out.println("D " + ex);
                st.executeUpdate(cmd.getText());
                System.out.println("E");
                ResultSet rs = st.executeQuery("select * from buy");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("cid"));
                    //System.out.println("C");
                    Integer b = Integer.parseInt(rs.getString("pid"));
                    //System.out.println("C");
                    Integer c = Integer.parseInt(rs.getString("sid"));
                    //System.out.println("C");
                    String d = rs.getString("dop");
                    //System.out.println("C");
                    Integer e = Integer.parseInt(rs.getString("qty"));
                    //System.out.println("C");
                    tablebuy.getItems().add(new Buy(a, b, c, d, e));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tablebuy.getItems().clear();
                ResultSet rs = st.executeQuery("select * from buy");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("cid"));
                    //System.out.println("C");
                    Integer b = Integer.parseInt(rs.getString("pid"));
                    //System.out.println("C");
                    Integer c = Integer.parseInt(rs.getString("sid"));
                    //System.out.println("C");
                    String d = rs.getString("dop");
                    //System.out.println("C");
                    Integer e = Integer.parseInt(rs.getString("qty"));
                    //System.out.println("C");
                    tablebuy.getItems().add(new Buy(a, b, c, d, e));

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
        insertbuy = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertbuy1.fxml"));
        insertbuy.setTitle("New Purchase");
        insertbuy.initStyle(StageStyle.UNDECORATED);
        insertbuy.setResizable(false);
        insertbuy.setScene(new Scene(root, 458, 596));
        insertbuy.show();
    }



}
