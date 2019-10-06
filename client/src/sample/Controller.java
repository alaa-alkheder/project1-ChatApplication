package sample;


import javafx.application.Platform;
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
    private    ListView<String> UserList; // Value injected by FXMLLoader

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
        System.out.println(UserList.getSelectionModel().getSelectedItems());
    }
    public void UpdateUserList(ArrayList <Object> x){
        UserList.getItems().removeAll();
        for (int i = 1; i <x.size() ; i++) {
            String a=String.valueOf(x.get(i));
            System.out.println(a);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    UserList.getItems().add(a);
                }
            });


        }}

    ArrayList<Object> mass= new ArrayList<Object>();
    ObjectOutputStream out  ;
    InputStream input ;
    listner1 listner1;
    String UserSender;
    @FXML
    void initialize() {



        massList.setVisible(false);
        massge.setVisible(false);
//        login1.setVisible(false);
        UserList.getItems().add("************");




    }

    public void Login(javafx.event.ActionEvent actionEvent) {

        listner1=new listner1(Main.input);
        listner1.start();
        String Temp=userName.getText().toString();
        mass.add(0, Temp);
        System.out.println();
//        m=pass.getText().toString();
        Temp="mm";
        System.out.println("pass"+Temp);
         mass.add(1,Temp);
//
        try {
          Main.out.writeObject(mass);
        } catch (IOException e) {
//            e.printStackTrace();
        }
        UserList.setVisible(true);
        massList.setVisible(true);
        massge.setVisible(true);
//        userName.setVisible(false);
        pass.setVisible(false);
//        login1.setVisible(false);
//        img.removeAll();
    }

    public void Send(javafx.event.ActionEvent actionEvent) {
        UserSender= userName.getText().toString();
        mass =new ArrayList<Object>();
        mass.add(0,"4");
        mass.add(1,UserSender);
        mass.add(2,massge.getText().toString());
        try {
            Main.out.writeObject(mass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheackUser(MouseEvent mouseEvent) {
        UserSender= userName.getText().toString();
        System.out.println(UserSender );
    }

    public class listner1 extends Thread {
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
            while (true) {
                ArrayList mass = null;
                try {


                    mass = (ArrayList<Object>) in.readObject();
                    int x=Integer.valueOf(String.valueOf(mass.get(0)));
                    System.out.println("Type mass recieved"+x);
                    switch (x){
                        case 0://Login mass
                            System.out.println(mass);


                            break;
                        case 2://Update Online User
                            UpdateUserList(mass);
                             break;
                        case 3://Update User list
//                            UpdateUserList(mass);
                            break;
                        case 4://Update User list
                            System.out.println(String.valueOf(mass.get(2)));
                            break;

                    }
                } catch (IOException e) {
//                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
                }
                System.out.println(mass);
//                System.out.println("____" + String.valueOf(mass.get(0)));
            }

        }

        }
    }