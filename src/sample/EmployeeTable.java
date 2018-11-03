package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeTable implements Initializable {

    public static Stage insertemployee;

    @FXML
    TableView<Employee> tableemployee;
    @FXML
    StackPane stackpane;
    @FXML
    BarChart<String,Number> barchart1;
    @FXML
    JFXTextArea text1;
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

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tableemployee.getItems().clear();
            ResultSet rs = st.executeQuery("select * from employee");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("eid"));
                //System.out.println("C");
                String b = rs.getString("ename");
                //System.out.println("D");
                String c = rs.getString("phno");
                //System.out.println("E");
                String d = rs.getString("address");
                Integer e = Integer.parseInt(rs.getString("sal"));
                Integer f = Integer.parseInt(rs.getString("up"));
                Integer g = Integer.parseInt(rs.getString("down"));
                Integer h = Integer.parseInt(rs.getString("sid"));
                String i = rs.getString("doj");
                tableemployee.getItems().add(new Employee(a, b, c, d, e, f, g, h, i));

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
                ResultSet rs = st.executeQuery("select * from employee");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("eid"));
                    //System.out.println("C");
                    String b = rs.getString("ename");
                    //System.out.println("D");
                    String c = rs.getString("phno");
                    //System.out.println("E");
                    String d = rs.getString("address");
                    Integer e = Integer.parseInt(rs.getString("sal"));
                    Integer f = Integer.parseInt(rs.getString("up"));
                    Integer g = Integer.parseInt(rs.getString("down"));
                    Integer h = Integer.parseInt(rs.getString("sid"));
                    String i = rs.getString("doj");
                    tableemployee.getItems().add(new Employee(a, b, c, d, e, f, g, h, i));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tableemployee.getItems().clear();
                ResultSet rs = st.executeQuery("select * from employee");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("eid"));
                    //System.out.println("C");
                    String b = rs.getString("ename");
                    //System.out.println("D");
                    String c = rs.getString("phno");
                    //System.out.println("E");
                    String d = rs.getString("address");
                    Integer e = Integer.parseInt(rs.getString("sal"));
                    Integer f = Integer.parseInt(rs.getString("up"));
                    Integer g = Integer.parseInt(rs.getString("down"));
                    Integer h = Integer.parseInt(rs.getString("sid"));
                    String i = rs.getString("doj");
                    tableemployee.getItems().add(new Employee(a, b, c, d, e, f, g, h, i));

                }
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("WRONG SYNTAX !!!");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }
            catch (SQLException sq)
            {   tableemployee.getItems().clear();
                ResultSet result = st.executeQuery("select * from employee");
                System.out.println("F");
                while (result.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(result.getString("eid"));
                    //System.out.println("C");
                    String b = result.getString("ename");
                    //System.out.println("D");
                    String c = result.getString("phno");
                    //System.out.println("E");
                    String d = result.getString("address");
                    Integer e = Integer.parseInt(result.getString("sal"));
                    Integer f = Integer.parseInt(result.getString("up"));
                    Integer g = Integer.parseInt(result.getString("down"));
                    Integer h = Integer.parseInt(result.getString("sid"));
                    String i = result.getString("doj");
                    tableemployee.getItems().add(new Employee(a, b, c, d, e, f, g, h, i));

                }
                StringBuilder str=new StringBuilder("");
                Class.forName("com.mysql.cj.jdbc.Driver");
                //String url = "jdbc:mysql://localhost:3306/stud?allowPublicKeyRetrieval=true&useSSL=false";
                //Connection con = DriverManager.getConnection(url,"root","sc13111998");
                //Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(cmd.getText());
                ResultSetMetaData rsm=rs.getMetaData();
                String s=DBTablePrinter.printResultSet(rs);
                System.out.println(rsm);
                System.out.println(rsm.getColumnCount());
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    str.append(rsm.getColumnName(i)+"           ");
                }
                str.append("\n----------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------------\n");
                while (rs.next()) {
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        str.append(rs.getString(i)+"            ");
                    }
                    str.append("\n");
                }
                JFXDialogLayout content=new JFXDialogLayout();
                Label label=new Label(" OUTPUT ");
                label.setStyle("-fx-text-fill:#fff;-fx-font-weight:bold;-fx-font-size:30px;-fx-alignment:center;-fx-font-family:Lato;-fx-border-color:#fff;-fx-border-width:4px;-fx-border-radius:10px;");
                label.setAlignment(Pos.CENTER);
                content.setHeading(label);
                TextArea textArea=new TextArea(s);
                textArea.setStyle("-fx-font-weight:bold;");
                content.setBody(textArea);
                JFXDialog dialog=new JFXDialog(stackpane,content,JFXDialog.DialogTransition.LEFT);
                content.setStyle("-fx-background-color:#2ECC71;-fx-pref-width:600px;-fx-pref-height:450px;-fx-text-fill:#ff0000;-fx-text-color:#ff0000;");
                dialog.setContent(content);
                JFXButton button=new JFXButton("Okay");
                button.setStyle("-fx-background-color:#fff;-fx-text-fill:#000;-fx-font-weight:bold;-fx-pref-width:150px;-fx-pref-height:40px;-fx-background-radius:20px;-fx-border-radius:20px;");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                    }
                });
                content.setActions(button);
                dialog.show();
            }


        }
    }
    public void clear(ActionEvent event)
    {
        cmd.setText("");
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
    public void deletesel(ActionEvent event) throws Exception {
        System.out.println("Shubham Chaudhary");
        Employee e=tableemployee.getSelectionModel().getSelectedItem();
        String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
        String uname = "root";
        String pass = "sc13111998";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("ALERT!!!");
        al.setHeaderText(null);
        al.setContentText("Sure u want to delete??");
        Optional<ButtonType> op=al.showAndWait();
        //TimeUnit.SECONDS.sleep(3);
        try {
            if(op.get()==ButtonType.OK && op.isPresent()) {
                System.out.println("1");
                st.executeUpdate("delete from employee where eid=" + e.getEid()+ ";");
                addfromdb();
                //System.out.println(c.getCid() + " " + c.getCname() + " " + c.getEmail() + " " + c.getPhno());
            }
            else {
                System.out.println("2");
                al.close();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }


    }
    public void loadbar1(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        String url = "jdbc:mysql://localhost:3306/galleria?useSSL=false";
        String uname = "root";
        String pass = "sc13111998";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        barchart1.getData().clear();
        XYChart.Series series=new XYChart.Series<>();
        XYChart.Series series1=new XYChart.Series<>();
        StringBuilder stb=new StringBuilder("Oname"+"\t\t"+"UP"+"\t\t"+"DOWN\n\n");
        ResultSet r=st.executeQuery("select eid,ename,up,down from employee e;");
        while (r.next())
        {
            //System.out.println(r.getString("c.catname")+" "+r.getString("count(pid)"));
            String str=r.getString("ename");
            Integer num1 =Integer.parseInt(r.getString("up"));
            Integer num2 =Integer.parseInt(r.getString("down"));
            //series.getData().add(new XYChart.Data<String, Number>());
            series.getData().add(new XYChart.Data<>(str,num1));
            series1.getData().add(new XYChart.Data<>(str,num2));
            stb.append(r.getString("ename")+"\t\t"+num1+"\t\t"+num2+"\n");
            //series.getData().add(new XYChart.Data<String, Number>("C",376));
        }
        text1.setText(stb.toString());
        barchart1.getData().addAll(series,series1);
    }

}
