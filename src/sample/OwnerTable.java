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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
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
    StackPane stackpane;
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

        }
        catch (SQLSyntaxErrorException syntax)
        {
            tableowner.getItems().clear();
            ResultSet rs = st.executeQuery("select * from owner");
            System.out.println("F");
            while (rs.next()) {   //System.out.println(rs.next());
                Integer a = Integer.parseInt(rs.getString("oid"));
                String b = rs.getString("oname");
                String c = rs.getString("phno");
                String d = rs.getString("address");
                tableowner.getItems().add(new Owner(a, b, c, d));

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
                ResultSet rs = st.executeQuery("select * from owner");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("oid"));
                    String b = rs.getString("oname");
                    String c = rs.getString("phno");
                    String d = rs.getString("address");
                    tableowner.getItems().add(new Owner(a, b, c, d));

                }
            }
            catch (SQLSyntaxErrorException syntax)
            {
                tableowner.getItems().clear();
                ResultSet rs = st.executeQuery("select * from owner");
                System.out.println("F");
                while (rs.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(rs.getString("oid"));
                    String b = rs.getString("oname");
                    String c = rs.getString("phno");
                    String d = rs.getString("address");
                    tableowner.getItems().add(new Owner(a, b, c, d));

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
            {   tableowner.getItems().clear();
                ResultSet result = st.executeQuery("select * from owner");
                System.out.println("F");
                while (result.next()) {   //System.out.println(rs.next());
                    Integer a = Integer.parseInt(result.getString("oid"));
                    String b = result.getString("oname");
                    String c = result.getString("phno");
                    String d = result.getString("address");
                    tableowner.getItems().add(new Owner(a, b, c, d));

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
        insertowner = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Insertowner1.fxml"));
        insertowner.setTitle("New Product");
        insertowner.initStyle(StageStyle.UNDECORATED);
        insertowner.setResizable(false);
        insertowner.setScene(new Scene(root, 444, 555));
        insertowner.show();
    }


}
