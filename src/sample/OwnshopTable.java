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
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OwnshopTable implements Initializable {

    public static Stage insertownshop;
    public static Ownshop own;

    @FXML
    BarChart barchart1;
    @FXML
    JFXTextArea text1;
    @FXML
    TableView<Ownshop> tableownshop;
    @FXML
    StackPane stackpane;
    @FXML
    private TableColumn<Ownshop, Integer> oid;

    @FXML
    private TableColumn<Ownshop, Integer> sid;

    @FXML
    private Label lab;

    @FXML
    private TextField cmd;

    public ObservableList<Ownshop> list= FXCollections.observableArrayList(
            new Ownshop(3,2)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oid.setCellValueFactory(new PropertyValueFactory<Ownshop,Integer>("oid"));
        sid.setCellValueFactory(new PropertyValueFactory<Ownshop,Integer>("sid"));
        tableownshop.setCenterShape(true);
        tableownshop.setItems(list);
        try {
            addfromdb();
        } catch (Exception e) {

        }
    }

    public void addfromdb() throws  Exception
    {

        tableownshop.getItems().clear();
        try {
            String url="jdbc:mysql://localhost:3306/galleria?useSSL=false";
            String uname="root";
            String pass="sc13111998";
            String query="select * from ownshop order by oid,sid";

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
                    Integer b=Integer.parseInt(rs.getString("sid"));
                    tableownshop.getItems().add(new Ownshop(a,b));

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
        String query = "select * from ownshop";


        //Connection conn=Connect.getconnected();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        //st.executeLargeUpdate(query);
        //tablecustomer.getItems().clear();
        //st.executeUpdate(cmd.getText());
        tableownshop.getItems().clear();
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
                Integer b=Integer.parseInt(rs.getString("sid"));
                tableownshop.getItems().add(new Ownshop(a,b));
            }

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tableownshop.getItems().clear();
            ResultSet rs = st.executeQuery("select * from ownshop");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("oid"));
                Integer b = Integer.parseInt(rs.getString("sid"));
                tableownshop.getItems().add(new Ownshop(a, b));

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
                ResultSet rs = st.executeQuery("select * from ownshop");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("oid"));
                    Integer b = Integer.parseInt(rs.getString("sid"));
                    tableownshop.getItems().add(new Ownshop(a, b));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tableownshop.getItems().clear();
                ResultSet rs = st.executeQuery("select * from ownshop");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("oid"));
                    Integer b = Integer.parseInt(rs.getString("sid"));
                    tableownshop.getItems().add(new Ownshop(a, b));

                }
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("WRONG SYNTAX !!!");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }
            catch (SQLException sq) {
                try{
                tableownshop.getItems().clear();
                ResultSet result = st.executeQuery("select * from ownshop");
                System.out.println("F");
                while (result.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(result.getString("oid"));
                    Integer b = Integer.parseInt(result.getString("sid"));
                    tableownshop.getItems().add(new Ownshop(a, b));

                }
                StringBuilder str = new StringBuilder("");
                Class.forName("com.mysql.cj.jdbc.Driver");
                //String url = "jdbc:mysql://localhost:3306/stud?allowPublicKeyRetrieval=true&useSSL=false";
                //Connection con = DriverManager.getConnection(url,"root","sc13111998");
                //Statement st=con.createStatement();
                ResultSet rs = st.executeQuery(cmd.getText());
                ResultSetMetaData rsm = rs.getMetaData();
                String s = DBTablePrinter.printResultSet(rs);
                System.out.println(rsm);
                System.out.println(rsm.getColumnCount());
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    str.append(rsm.getColumnName(i) + "           ");
                }
                str.append("\n----------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------------\n");
                while (rs.next()) {
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        str.append(rs.getString(i) + "            ");
                    }
                    str.append("\n");
                }
                JFXDialogLayout content = new JFXDialogLayout();
                Label label = new Label(" OUTPUT ");
                label.setStyle("-fx-text-fill:#fff;-fx-font-weight:bold;-fx-font-size:30px;-fx-alignment:center;-fx-font-family:Lato;-fx-border-color:#fff;-fx-border-width:4px;-fx-border-radius:10px;");
                label.setAlignment(Pos.CENTER);
                content.setHeading(label);
                TextArea textArea = new TextArea(s);
                textArea.setStyle("-fx-font-weight:bold;");
                content.setBody(textArea);
                JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.RIGHT);
                content.setStyle("-fx-background-color:#2ECC71;-fx-pref-width:600px;-fx-pref-height:450px;-fx-text-fill:#ff0000;-fx-text-color:#ff0000;");
                dialog.setContent(content);
                JFXButton button = new JFXButton("Okay");
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
            catch (Exception e)
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("OOPS!!!");
                al.setHeaderText(null);
                al.setContentText("WRONG INPUT!!!");
                al.showAndWait();
                //TimeUnit.SECONDS.sleep(3);
                al.close();
            }
            }


        }
    }
    public void clear(ActionEvent event)
    {
        cmd.setText("");
    }
    public void add(ActionEvent event) throws IOException {
        own=null;
        insertownshop = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertownshop1.fxml"));
        insertownshop.setTitle("New OwnShop");
        insertownshop.initStyle(StageStyle.UNDECORATED);
        insertownshop.setResizable(false);
        insertownshop.setScene(new Scene(root, 444, 492));
        insertownshop.show();
    }
    public void modify(ActionEvent event) throws IOException{
        own=tableownshop.getSelectionModel().getSelectedItem();
        if(own!=null) {
            insertownshop = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertownshop1.fxml"));
            insertownshop.setTitle("Modify OwnShop");
            insertownshop.initStyle(StageStyle.UNDECORATED);
            insertownshop.setResizable(false);
            insertownshop.setScene(new Scene(root, 444, 492));
            insertownshop.show();
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("Choose a record to modify");
            al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            al.close();
        }
    }
    public void deletesel(ActionEvent event) throws Exception {
        System.out.println("Shubham Chaudhary");
        Ownshop o=tableownshop.getSelectionModel().getSelectedItem();
        if(own!=null) {
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
            Optional<ButtonType> op = al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            try {
                if (op.get() == ButtonType.OK && op.isPresent()) {
                    System.out.println("1");
                    System.out.println("delete from ownshop where oid=" + o.getOid() + " and sid=" + o.getSid() + ";");
                    st.executeUpdate("delete from ownshop where oid=" + o.getOid() + " and sid=" + o.getSid() + ";");
                    addfromdb();
                    //System.out.println(c.getCid() + " " + c.getCname() + " " + c.getEmail() + " " + c.getPhno());
                } else {
                    System.out.println("2");
                    al.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("OOPS!!!");
            al.setHeaderText(null);
            al.setContentText("Choose a record to delete");
            al.showAndWait();
            //TimeUnit.SECONDS.sleep(3);
            al.close();
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
        StringBuilder stb=new StringBuilder("OID"+"\t\t"+"Oname"+"\t\t"+"ShopsOwned\n\n");
        ResultSet r=st.executeQuery(" select own.oid,o.oname,count(own.sid) from owner o,ownshop own where o.oid=own.oid group by own.oid order by own.oid;");
        while (r.next())
        {
            //System.out.println(r.getString("c.catname")+" "+r.getString("count(pid)"));
            String str=r.getString("o.oname");
            Integer num =Integer.parseInt(r.getString("count(own.sid)"));
            //series.getData().add(new XYChart.Data<String, Number>());
            series.getData().add(new XYChart.Data<>(str,num));
            stb.append(r.getString("own.oid")+"\t\t"+str+"\t\t"+num+"\n");
            //series.getData().add(new XYChart.Data<String, Number>("C",376));
        }
        text1.setText(stb.toString());
        barchart1.getData().add(series);
    }

}
