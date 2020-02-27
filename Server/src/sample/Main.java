package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.net.ServerSocket;
import java.util.HashMap;


public class Main extends Application {
    static HashMap<String, User> user = new HashMap<String, User>();
    static Stage stage;
    static final String userFile = "user.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 390));
        primaryStage.setResizable(false);
        primaryStage.show();
        Parent root1 = FXMLLoader.load(getClass().getResource("creatNewUser.fxml"));
        stage = new Stage();
        stage.setScene(new Scene(root1, 640, 390));

    }

    public static void showCreateUserWindow() {
        stage.show();
    }

    static ServerSocket s = null;

    public static void main(String[] args) {

        try {
            s = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fin = new FileInputStream(userFile);
            ObjectInputStream ois = new ObjectInputStream(fin);

            user = (HashMap<String, User>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        JSONObject js=new JSONObject();
     /*   User a=new User();
        a.Name="alaa";
        a.UnqeuName="aa";
        a.password="mm";
        user.put(a.UnqeuName,a);
        a=null;
        a=new User();
        a.Name="abd";
        a.UnqeuName="abd";
        a.password="mm";
        user.put(a.UnqeuName,a);
        a=null;
        a=new User();
        a.Name="abd1";
        a.UnqeuName="abd";
        a.password="mm";
        user.put(a.UnqeuName,a);
        a=null;
        a=new User();
        a.Name="abd2";
        a.UnqeuName="abd";
        a.password="mm";
        user.put(a.UnqeuName,a);
        a=null;
        a=new User();
        a.Name="abd3";
        a.UnqeuName="abd";
        a.password="mm";
        user.put(a.UnqeuName,a);
        try {
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        launch(args);
////        a.isWork=false;
//////        js.put;
////        String path = "json.json";
////a=(User)js.get("1");
////        try {
////            FileWriter file = new FileWriter(path);
////            file.write(js.toString());
////            file.flush();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//               ServerSocket  s= null;
//        try {
//            s = new ServerSocket(8080);
//            while (true){
//                new ClientHandler(s.accept()).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
