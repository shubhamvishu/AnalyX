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
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
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
    StackPane stackpane;
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

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tablecustomer.getItems().clear();
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
                tablecustomer.getItems().add(new Customer(a, b, c, d));

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
                    tablecustomer.getItems().add(new Customer(a, b, c, d));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tablecustomer.getItems().clear();
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
                    tablecustomer.getItems().add(new Customer(a, b, c, d));

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
            {   tablecustomer.getItems().clear();
                ResultSet result = st.executeQuery("select * from customer");
                System.out.println("F");
                while (result.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(result.getString("cid"));
                    //System.out.println("C");
                    String b = result.getString("cname");
                    //System.out.println("D");
                    String c = result.getString("email");
                    //System.out.println("E");
                    String d = result.getString("phno");
                    //System.out.println("G");
                    tablecustomer.getItems().add(new Customer(a, b, c, d));

                }
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
