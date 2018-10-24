package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ShopTable implements Initializable {

    public static Stage insertshop;

    @FXML
    TableView<Shop> tableshop;
    @FXML
    LineChart<String,Number> linechart;
    @FXML
    BarChart<String,Number> barchart;
    @FXML
    private TableColumn<Shop, Integer> sid;

    @FXML
    private TableColumn<Shop, String> sname;

    @FXML
    private TableColumn<Shop, Integer> rev;

    @FXML
    private TableColumn<Shop, Integer> catid;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Shop> list= FXCollections.observableArrayList(
            new Shop(56,"shubham",2000,4)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sid.setCellValueFactory(new PropertyValueFactory<Shop,Integer>("sid"));
        sname.setCellValueFactory(new PropertyValueFactory<Shop,String>("sname"));
        rev.setCellValueFactory(new PropertyValueFactory<Shop,Integer>("rev"));
        catid.setCellValueFactory(new PropertyValueFactory<Shop,Integer>("catid"));
        tableshop.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tableshop.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from shop";

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
                    System.out.println(rs.getString("sid") +" "+ rs.getString("sname")+" "+ rs.getString("rev")+" "+ rs.getString("catid") );
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a=Integer.parseInt(rs.getString("sid"));
                    //System.out.println("C");
                    String b=rs.getString("sname");
                    //System.out.println("D");
                    Integer c=Integer.parseInt(rs.getString("rev"));
                    //System.out.println("E");
                    Integer d=Integer.parseInt(rs.getString("catid"));
                    tableshop.getItems().add(new Shop(a,b,c,d));
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
        String query = "select * from shop";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tableshop.getItems().clear();
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
                Integer a=Integer.parseInt(rs.getString("sid"));
                //System.out.println("C");
                String b=rs.getString("sname");
                //System.out.println("D");
                Integer c=Integer.parseInt(rs.getString("rev"));
                //System.out.println("E");
                Integer d=Integer.parseInt(rs.getString("catid"));
                tableshop.getItems().add(new Shop(a,b,c,d));
            }

        } catch (SQLException e) {

            System.out.println("D "+e);
            st.executeUpdate(cmd.getText());
            System.out.println("E");
            ResultSet rs = st.executeQuery("select * from shop");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a=Integer.parseInt(rs.getString("sid"));
                //System.out.println("C");
                String b=rs.getString("sname");
                //System.out.println("D");
                Integer c=Integer.parseInt(rs.getString("rev"));
                //System.out.println("E");
                Integer d=Integer.parseInt(rs.getString("catid"));
                tableshop.getItems().add(new Shop(a,b,c,d));

            }


        }
    }
    public void add(ActionEvent event) throws IOException {
        insertshop = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertshop1.fxml"));
        insertshop.setTitle("New Category");
        insertshop.initStyle(StageStyle.UNDECORATED);
        insertshop.setResizable(false);
        insertshop.setScene(new Scene(root, 420, 512));
        insertshop.show();
    }
    public void btn(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
        String uname = "root";
        String pass = "sc13111998";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        linechart.getData().clear();
        XYChart.Series<String,Number> series=new XYChart.Series<String,Number>();
        ResultSet r=st.executeQuery("select c.catname,count(pid) from category c,product p where c.catid=p.catid group by p.catid order by c.catid;");
        while (r.next())
        {
            //System.out.println(r.getString("c.catname")+" "+r.getString("count(pid)"));
            String str=r.getString("c.catname");
            Number number = NumberFormat.getInstance().parse(r.getString("count(pid)"));
            System.out.println(number.intValue());
            //series.getData().add(new XYChart.Data<String, Number>());
            series.getData().add(new XYChart.Data<String, Number>(str,number));
            //series.getData().add(new XYChart.Data<String, Number>("C",376));
        }
        linechart.getXAxis().setAnimated(false);
        linechart.getData().add(series);

    }
    public void btn1(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
        String uname = "root";
        String pass = "sc13111998";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        barchart.getData().clear();
        XYChart.Series series=new XYChart.Series<>();
        ResultSet r=st.executeQuery("select c.catname,count(pid) from category c,product p where c.catid=p.catid group by p.catid order by c.catid;");
       while (r.next())
        {
            //System.out.println(r.getString("c.catname")+" "+r.getString("count(pid)"));
            String str=r.getString("c.catname");
            Integer a =Integer.parseInt(r.getString("count(pid)"));
        //series.getData().add(new XYChart.Data<String, Number>());
        series.getData().add(new XYChart.Data<>(str,a));
        //series.getData().add(new XYChart.Data<String, Number>("C",376));
         }
        barchart.getData().add(series);
    }

}
