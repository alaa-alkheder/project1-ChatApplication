package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Main extends Application {
    static HashMap<String,User> user = new HashMap<String, User>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)  {
//        JSONObject js=new JSONObject();
        User a=new User();
        a.Name="alaa";
        a.UnqeuName="aa";
        a.password="mm";
        user.put(a.UnqeuName,a);
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
               ServerSocket  s= null;
        try {
            s = new ServerSocket(8888);
            while (true){
                new ClientHandler(s.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        launch(args);



    }
}
