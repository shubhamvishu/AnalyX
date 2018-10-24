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

public class EmployeeTable implements Initializable {

    public static Stage insertemployee;

    @FXML
    TableView<Employee> tableemployee;
    @FXML
    LineChart<String,Number> linechart;
    @FXML
    BarChart<String,Number> barchart;
    @FXML
    private TableColumn<Employee, Integer> eid;

    @FXML
    private TableColumn<Employee, String> ename;

    @FXML
    private TableColumn<Employee, String> phno;

    @FXML
    private TableColumn<Employee, String> address;

    @FXML
    private TableColumn<Employee, Integer> sal;

    @FXML
    private TableColumn<Employee, Integer> up;

    @FXML
    private TableColumn<Employee, Integer> down;

    @FXML
    private TableColumn<Employee, Integer> sid;

    @FXML
    private TableColumn<Employee, String> doj;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Employee> list= FXCollections.observableArrayList(
            new Employee(56,"shubham","8238923","blr",35000,90,83,3, "2018-09-09")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eid.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("eid"));
        ename.setCellValueFactory(new PropertyValueFactory<Employee,String>("ename"));
        phno.setCellValueFactory(new PropertyValueFactory<Employee,String>("phno"));
        address.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        sal.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("sal"));
        up.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("up"));
        down.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("down"));
        sid.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("sid"));
        doj.setCellValueFactory(new PropertyValueFactory<Employee,String>("doj"));
        tableemployee.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tableemployee.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from employee";

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
                    System.out.println(rs.getString("eid") +" "+ rs.getString("ename")+" "+ rs.getString("phno")+" "+ rs.getString("address")+" "+ rs.getString("sal")+" "+ rs.getString("up")+" "+ rs.getString("down")+" "+ rs.getString("sid") );
                    //System.out.println("A");
                    //System.out.println("B");
                    Integer a=Integer.parseInt(rs.getString("eid"));
                    //System.out.println("C");
                    String b=rs.getString("ename");
                    //System.out.println("D");
                    String c=rs.getString("phno");
                    //System.out.println("E");
                    String d=rs.getString("address");
                    Integer e=Integer.parseInt(rs.getString("sal"));
                    Integer f=Integer.parseInt(rs.getString("up"));
                    Integer g=Integer.parseInt(rs.getString("down"));
                    Integer h=Integer.parseInt(rs.getString("sid"));
                    String i=rs.getString("doj");
                    tableemployee.getItems().add(new Employee(a,b,c,d,e,f,g,h,i));
                    //System.out.println("J");
               // }
            }
            System.out.println("------------------------------------------------");
            ResultSet r=st.executeQuery("select c.catname,count(pid) from category c,product p where c.catid=p.catid and c.catid<=5 group by p.catid;");
            while (r.next())
            {
                System.out.println(r.getString("c.catname")+" "+r.getString("count(pid)"));
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
        String query = "select * from employee";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tableemployee.getItems().clear();
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
                Integer a=Integer.parseInt(rs.getString("eid"));
                //System.out.println("C");
                String b=rs.getString("ename");
                //System.out.println("D");
                String c=rs.getString("phno");
                //System.out.println("E");
                String d=rs.getString("address");
                Integer e=Integer.parseInt(rs.getString("sal"));
                Integer f=Integer.parseInt(rs.getString("up"));
                Integer g=Integer.parseInt(rs.getString("down"));
                Integer h=Integer.parseInt(rs.getString("sid"));
                String i=rs.getString("doj");
                tableemployee.getItems().add(new Employee(a,b,c,d,e,f,g,h,i));
            }

        } catch (SQLException ex) {

            System.out.println("D "+ex);
            st.executeUpdate(cmd.getText());
            System.out.println("E");
            ResultSet rs = st.executeQuery("select * from employee");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a=Integer.parseInt(rs.getString("eid"));
                //System.out.println("C");
                String b=rs.getString("ename");
                //System.out.println("D");
                String c=rs.getString("phno");
                //System.out.println("E");
                String d=rs.getString("address");
                Integer e=Integer.parseInt(rs.getString("sal"));
                Integer f=Integer.parseInt(rs.getString("up"));
                Integer g=Integer.parseInt(rs.getString("down"));
                Integer h=Integer.parseInt(rs.getString("sid"));
                String i=rs.getString("doj");
                tableemployee.getItems().add(new Employee(a,b,c,d,e,f,g,h,i));

            }


        }
    }
    public void add(ActionEvent event) throws IOException {
        insertemployee = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertemployee1.fxml"));
        insertemployee.setTitle("New Category");
        insertemployee.initStyle(StageStyle.UNDECORATED);
        insertemployee.setResizable(false);
        insertemployee.setScene(new Scene(root, 527, 794));
        insertemployee.show();
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
