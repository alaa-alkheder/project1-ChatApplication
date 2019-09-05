package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class Controller {
    static HashMap<String,User> user = new HashMap<String, User>();
    ArrayList <String> UserName= new ArrayList<String>();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="end"
    private Button end; // Value injected by FXMLLoader

    @FXML // fx:id="onlineuser"
    private ListView<String> onlineuser; // Value injected by FXMLLoader

    @FXML // fx:id="events"
    private ListView<String> events; // Value injected by FXMLLoader

    @FXML // fx:id="edituser"
    private Button edituser; // Value injected by FXMLLoader

    @FXML // fx:id="clean"
    private Button clean; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private Button start; // Value injected by FXMLLoader

    @FXML // fx:id="adduser"
    private Button adduser; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private Button search; // Value injected by FXMLLoader

    @FXML // fx:id="searchfild"
    private TextField searchfild; // Value injected by FXMLLoader
    @FXML // fx:id="UserList"
    private ToggleGroup UserList; // Value injected by FXMLLoader

    @FXML
    void OnlineUserRadiobutton(ActionEvent event) {
        onlineuser.getItems().clear();
        onlineuser.getItems().add("::::::::");
        for (ClientHandler handler : ClientHandler.handlers) {
           onlineuser.getItems().add(handler.curentUser.UnqeuName);
        }
    }

    @FXML
    void SearchUnqeuName(ActionEvent event) {

    }

    @FXML
    void SelectUserViaMouse(MouseEvent event) {

    }

    @FXML
    void UserRadiobutton(ActionEvent event) {
        onlineuser.getItems().clear();
        onlineuser.getItems().add("*****");
        for (String user1 : UserName ) {
            onlineuser.getItems().add(user1);
        }}
    @FXML
    void adduser(ActionEvent event) {

    }

    @FXML
    void clean(ActionEvent event) {

    }

    @FXML
    void edituser(ActionEvent event) {

    }

    @FXML
    void end(ActionEvent event) {
System.exit(-1);
    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {
        User a=new User();
        a.Name="alaa";
        a.UnqeuName="aa";
        a.password="mm";
        user.put(a.UnqeuName,a);
        UserName.add(a.UnqeuName);
        a=null;
        a=new User();
        a.Name="abd";
        a.UnqeuName="abd";
        a.password="abd";
        user.put(a.UnqeuName,a);
        UserName.add(a.UnqeuName);
//        a.isWork=false;
////        js.put;
//        String path = "json.json";
//a=(User)js.get("1");
//        try {
//            FileWriter file = new FileWriter(path);
//            file.write(js.toString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
events.getItems().add("the server is started");
te r=new te();
r.start();

    }
class  te extends Thread{
    @Override
    public void run() {
        try {

            while (true){
                new ClientHandler(Main.s.accept()).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}

