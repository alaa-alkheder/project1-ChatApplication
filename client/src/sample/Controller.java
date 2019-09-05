package sample;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Controller {


    @FXML // fx:id="UserList"
    private ListView<?> UserList; // Value injected by FXMLLoader

    @FXML // fx:id="massList"
    private ListView<?> massList; // Value injected by FXMLLoader

    @FXML // fx:id="massge"
    private TextArea massge; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader

    @FXML // fx:id="pass"
    private PasswordField pass; // Value injected by FXMLLoader
    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader
    @FXML // fx:id="login"
    private Button login1; // Value injected by FXMLLoader

    @FXML // fx:id="send"
    private Button send; // Value injected by FXMLLoader
    @FXML
    void returnUserName(MouseEvent event) {

    }


    ArrayList<Object> mass= new ArrayList<Object>();
    ObjectOutputStream out  ;
    InputStream input ;
    listner1 lis;
    @FXML
    void initialize() {


        UserList.setVisible(false);
        massList.setVisible(false);
        massge.setVisible(false);
//        login1.setVisible(false);
    }

    public void Login(javafx.event.ActionEvent actionEvent) {
//        System.out.println("SSSSSSSS");
//        try {
////          Main.sock = new Socket("localhost", 8080);
//            ObjectOutputStream out = new ObjectOutputStream(Main.sock.getOutputStream());
//            input=Main.sock.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        lis=new listner1(Main.input);
        lis.start();
        String m="aa";

        mass.add(0, m);
        m="mm";
        System.out.println("pass"+m);
         mass.add(1,m);
//
        try {
          Main.out.writeObject(mass);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public void Send(javafx.event.ActionEvent actionEvent) {
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


