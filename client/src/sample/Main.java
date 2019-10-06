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
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
    static Socket sock ;
static ObjectOutputStream out = null;
static   InputStream input= null;
  public static void main(String[] args)  {

//      System.exit(-1);
//      BufferedReader be=new BufferedReader(new InputStreamReader(System.in));
//
//
//      try {
      try {
          sock = new Socket("127.0.0.1", 8080);
      } catch (IOException e) {
          e.printStackTrace();
      }
          ArrayList<Object> mass= new ArrayList<Object>();

      try {
          out = new ObjectOutputStream(sock.getOutputStream());
      } catch (IOException e1) {
          e1.printStackTrace();
      }

      try {
          input = sock.getInputStream();
      } catch (IOException e1) {
          e1.printStackTrace();
      }launch(args);
//          ObjectInputStream in = new ObjectInputStream(input);
//          System.out.println("ssss");
//          String t;
////          do {

//          String m=be.readLine();
//
//          mass.add(0, m);
//m=be.readLine();
////              m= "mm";
//              System.out.println(m);
//
//          mass.add(1,m);
////
//          out.writeObject(mass);
//
////              mass = (ArrayList<Object>) in.readObject();
//              System.out.println("11" + String.valueOf(mass.get(0))+mass.get(1));
//              t= (String) mass.get(1);
//              System.out.println("44"+t);
////          }while (!t.matches("BB"));
////          in=null;
//          listner1 lis=new listner1(input); lis.start();
////          System.out.println("/////");
//
//          System.out.println("*******");
//////
//          mass=new ArrayList<Object>();
//          mass.add(0,"K");
//          mass.add(1,"abd");
//          out.writeObject(mass);
////          out.writeObject(v);




//          sock.close();


//      } catch (IOException e) {
//          e.printStackTrace();
//      } catch (ClassNotFoundException e) {
//          e.printStackTrace();
      }
//      catch (ClassNotFoundException e) {
//          e.printStackTrace();
//      }

  }
