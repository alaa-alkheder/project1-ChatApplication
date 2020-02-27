package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Controller {

    public javafx.scene.control.Label error;
    @FXML // fx:id="usernameTextfield"
    private TextField usernameTextfield; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="connect"
    private Button connect1; // Value injected by FXMLLoader
//
//    @FXML // fx:id="error"
//    private Label error; // Value injected by FXMLLoader

    @FXML // fx:id="UserList"
    private ListView<String> UserList; // Value injected by FXMLLoader

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

    public void UpdateUserList(ArrayList<Object> x) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                /*   UserList.getItems().clear();*/

            }
        });
        for (int i = 1; i < x.size(); i++) {
            String a = String.valueOf(x.get(i));
            System.out.println(a);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    UserList.getItems().add(a);
                }
            });


        }
    }

    //    ArrayList<Object> mass = new ArrayList<Object>();
    ObjectOutputStream out;
    InputStream input;
    listner1 listner1;
    String UserSender;

    @FXML
    void initialize() {


      /*  massList.setVisible(false);
        massge.setVisible(false);
//        login1.setVisible(false);
        UserList.getItems().add("************");*/


    }

 /*   public void Login(javafx.event.ActionEvent actionEvent) {
        JSONObject jsonObject = new JSONObject();


        listner1 = new listner1(Main.input);
        listner1.start();
        String username = userName.getText().toString();
        mass.add(0, username);
        jsonObject.put("username", username);
        String password = "mm";
        mass.add(1, password);
        jsonObject.put("password", password);
        try {
            Main.out.writeObject(jsonObject);
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
    }*/

    public void Send(javafx.event.ActionEvent actionEvent) {
        System.out.println("DDDDDDDDDD");
        UserSender = userName.getText().toString();
        JSONObject jsonObject = new JSONObject();

//        mass.add(0, );
//        mass.add(1, UserSender);
//        mass.add(2, massge.getText().toString());
        jsonObject.put("type", "4");
        jsonObject.put("UserSender", UserSender);
        jsonObject.put("massge", massge.getText());

        try {
            Main.out.writeObject(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheackUser(MouseEvent mouseEvent) {
        UserSender = userName.getText().toString();
        System.out.println(UserSender);
    }

    boolean connect = false;

    public void loginButtonAction(ActionEvent actionEvent) {
        JSONObject jsonObject = new JSONObject();

        if (connect == false) {
            listner1 = new listner1(Main.input);
            listner1.start();
        }
        String username = usernameTextfield.getText().toString();
        jsonObject.put("username", username);
        String temp = password.getText().toString();
        jsonObject.put("password", temp);

        try {
            Main.out.writeObject(jsonObject);
            System.out.println("FFFFFFFFFFFFFF");
        } catch (IOException e) {
//            e.printStackTrace();
        }

    }



    private void editErrorMsg(String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                error.setText(msg);
            }
        });
    }

    public class listner1 extends Thread {
        ObjectInputStream in;

        listner1(InputStream input) {
            try {
                connect = true;
                in = new ObjectInputStream(input);
                System.out.println("8888");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean t = true;
            while (t) {
                JSONObject mass = null;
                try {
                    mass = (JSONObject) in.readObject();
                    System.out.println(mass.toString());
                    int x = Integer.valueOf(String.valueOf(mass.get("type")));
                    System.out.println("Type mass recieved" + x);
                    switch (x) {
                        case 0://Login mass
                            /** !!!!change the GUI*/
                            System.out.println(mass.get("loginMsg"));
                            editErrorMsg((String) mass.get("loginMsg"));
                            int j=Integer.valueOf(String.valueOf(mass.get("loginMsgNum")));
                            if (j==1)
                            Main.ShowChatWidow();
                            break;
                        //Update Online User
                        case 2: {
                            ArrayList<Object> OnlineUser = new ArrayList<>();
                            int size = Integer.valueOf(String.valueOf(mass.get("size")));
                            for (int i = 0; i < size; i++) {
                                OnlineUser.add(mass.get("user" + i));
                            }
                            UpdateUserList(OnlineUser);
                            break;
                        }
                        case 3://Update User list
//                            UpdateUserList(mass);
                            break;
                        case 4://Update User list
                            System.out.println(String.valueOf(mass.get(2)));
                            break;

                    }
                } catch (IOException e) {
                    t = false;//!!!!!!CONVERT TO CONTINU
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