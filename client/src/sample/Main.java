package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


  public static void main(String[] args) {
//        launch(args);
//      BufferedReader be=new BufferedReader(new InputStreamReader(System.in));
//      try {
//          System.out.println(be.readLine());
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
      try {
          Socket sock = new Socket("localhost", 8888);

          ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
          ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
          System.out.println("ssss");
          String m = "aa";
          ArrayList<Object> v = new ArrayList<Object>();
          v.add(0, m);
          m="mm";
          v.add(1,m);
//
          out.writeObject(v);
//          out.writeObject(v);
//          out.writeObject(v);


          v = (ArrayList<Object>) in.readObject();
          System.out.println("11" + String.valueOf(v.get(0)));

//          sock.close();


      } catch (IOException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
//      catch (ClassNotFoundException e) {
//          e.printStackTrace();
//      }

  }
  }
