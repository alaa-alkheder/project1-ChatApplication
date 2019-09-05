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
      BufferedReader be=new BufferedReader(new InputStreamReader(System.in));


      try {
          Socket sock = new Socket("localhost", 8080);
          ArrayList<Object> mass= new ArrayList<Object>();
          ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
          InputStream input=sock.getInputStream();
//          ObjectInputStream in = new ObjectInputStream(input);
          System.out.println("ssss");
          String t;
//          do {


          String m=be.readLine();

          mass.add(0, m);
m=be.readLine();
//              m= "mm";
              System.out.println(m);

          mass.add(1,m);
//
          out.writeObject(mass);

//              mass = (ArrayList<Object>) in.readObject();
              System.out.println("11" + String.valueOf(mass.get(0))+mass.get(1));
              t= (String) mass.get(1);
              System.out.println("44"+t);
//          }while (!t.matches("BB"));
//          in=null;
          listner1 lis=new listner1(input);
//          System.out.println("/////");

          System.out.println("*******");
////
          mass=new ArrayList<Object>();
          mass.add(0,"K");
          mass.add(1,"abd");
          out.writeObject(mass);
//          out.writeObject(v);
          lis.start();



//          sock.close();


      } catch (IOException e) {
          e.printStackTrace();
//      } catch (ClassNotFoundException e) {
//          e.printStackTrace();
      }
//      catch (ClassNotFoundException e) {
//          e.printStackTrace();
//      }

  }

    public static class listner1 extends Thread {
        ObjectInputStream in;
        listner1(InputStream input)  {
            try {

                in = new ObjectInputStream(input);
                System.out.println("8888");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            while (true){
                ArrayList mass= null;
                try {
                    mass = (ArrayList<Object>) in.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("____"+String.valueOf(mass.get(0)));
            }

        }
    }

}
