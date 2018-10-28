package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import java.util.ResourceBundle;

public class BuyTable implements Initializable {

    public static Stage insertbuy;

    @FXML TableView<Buy> tablebuy;
    @FXML
    StackPane stackpane;
    @FXML LineChart linechart;
    @FXML private TableColumn<Buy, Integer> cid;
    @FXML private TableColumn<Buy, Integer> pid;
    @FXML private TableColumn<Buy, Integer> sid;
    @FXML private TableColumn<Buy, String> dop;
    @FXML private TableColumn<Buy, Integer> qty;
    @FXML private Label lab;
    @FXML private Label lab1;
    @FXML private Label lab2;
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
            catch (SQLException sq)
            {   tablebuy.getItems().clear();
                ResultSet result= st.executeQuery("select * from buy");
                System.out.println("F");
                while (result.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(result.getString("cid"));
                    //System.out.println("C");
                    Integer b = Integer.parseInt(result.getString("pid"));
                    //System.out.println("C");
                    Integer c = Integer.parseInt(result.getString("sid"));
                    //System.out.println("C");
                    String d = result.getString("dop");
                    //System.out.println("C");
                    Integer e = Integer.parseInt(result.getString("qty"));
                    //System.out.println("C");
                    tablebuy.getItems().add(new Buy(a, b, c, d, e));

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
                JFXDialog dialog=new JFXDialog(stackpane,content,JFXDialog.DialogTransition.TOP);
                content.setStyle("-fx-background-color:#45B39D;-fx-pref-width:600px;-fx-pref-height:450px;-fx-text-fill:#ff0000;-fx-text-color:#ff0000;");
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
    public void add(ActionEvent event) throws IOException {
        insertbuy = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertbuy1.fxml"));
        insertbuy.setTitle("New Purchase");
        insertbuy.initStyle(StageStyle.UNDECORATED);
        insertbuy.setResizable(false);
        insertbuy.setScene(new Scene(root, 458, 596));
        insertbuy.show();
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
        XYChart.Series<String,Number> series1=new XYChart.Series<String,Number>();
        ResultSet r=st.executeQuery("select year(dop),month(dop),count(cid) from buy group by year(dop),month(dop);");
        StringBuilder stb=new StringBuilder("");
        while (r.next())
        {
            System.out.println(r.getString("year(dop)"));
            if(r.getString("year(dop)").equals("2017")) {
                stb.append(r.getString("month(dop)") + "   " + r.getString("count(cid)") + "\n");
                String str = r.getString("month(dop)");
                Number number = NumberFormat.getInstance().parse(r.getString("count(cid)"));
                System.out.println("yeahhh"+number.intValue());
                //series.getData().add(new XYChart.Data<String, Number>());
                series.getData().add(new XYChart.Data<String, Number>(str, number));
            }
            else if(r.getString("year(dop)").equals("2018"))
            {
                stb.append(r.getString("month(dop)") + "   " + r.getString("count(cid)") + "\n");
                String str = r.getString("month(dop)");
                Number number = NumberFormat.getInstance().parse(r.getString("count(cid)"));
                 System.out.println("yeahhh1111"+number.intValue());
                //series.getData().add(new XYChart.Data<String, Number>());
                series1.getData().add(new XYChart.Data<String, Number>(str, number));

            }
            //series.getData().add(new XYChart.Data<String, Number>("C",376));
        }
        lab1.setText(stb.toString());
       /* XYChart.Series<String,Number> series1=new XYChart.Series<String,Number>();
        ResultSet rs1=st.executeQuery("select month(dop),count(cid) where year(dop)=2018 from buy group by month(dop);");
        StringBuilder stb1=new StringBuilder("");
        while (r.next())
        {
            stb.append(r.getString("month(dop)")+"   "+r.getString("count(cid)")+"\n");
            String str=r.getString("month(dop)");
            Number number = NumberFormat.getInstance().parse(r.getString("count(cid)"));
            System.out.println(number.intValue());
            //series.getData().add(new XYChart.Data<String, Number>());
            series1.getData().add(new XYChart.Data<String, Number>(str,number));
            //series.getData().add(new XYChart.Data<String, Number>("C",376));
        }
        lab1.setText(stb.toString());*/
        linechart.getData().addAll(series,series1);

    }



}
