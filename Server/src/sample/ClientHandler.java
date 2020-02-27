package sample;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

import static sample.Main.user;

public class ClientHandler extends Thread {
    final int prot = 8888;
    User curentUser;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    static HashSet<ClientHandler> handlers = new HashSet<ClientHandler>();
    static HashMap<String, ClientHandler> Active = new HashMap<String, ClientHandler>();
    ArrayList<Object> Mass;
    ArrayList<ArrayList<Object>> MassageQueue = new ArrayList<ArrayList<Object>>();

    public ClientHandler(Socket s) throws IOException {
        curentUser = null;
        socket = s;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());

    }

    @Override
    public void run() {
        /** the function check the password to login*/
        CheckPasswordToLogin();
        /////////////////!!!!!!!!!!!! The User is logined !!!!!!!!!!!!!!!!///////////////////////////

        try {
            /**Update the Online User in the Client*/
            UpdateTheOnlineListForAllUser();
            /** !!!!!!!!!!!!!!send a massage to user for allow to send your massage*/


            /////////////////////////!!!!!!!!!!!! the Old massage was send to user !!!!!!!!!!!!!!!!///////////////////////////

            // Start Chat

            while (true) {
                ReadMassageFromUser();
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } finally {
            //remove the Client from list Online User
            handlers.remove(this);
            UpdateTheOnlineListForAllUser();
            //!!!!!!!!Update the list for All user
            //!!!!!!!!Save the massage in file
            System.out.println("finally");
            try {
                socket.close();
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }

    }

    //
    public void UpdateTheOnlineListForAllUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "2");
        jsonObject.put("size", "" + handlers.size());
        int i = 0;
        for (ClientHandler c : handlers) {
            jsonObject.put("user" + i, c.curentUser.UnqeuName);
            i++;
        }
        sendMassageToAllUser(jsonObject);
    }

    //
    public void UpdateTheUserListForAllUser() {
        //the Type is 3

    }

    //
    public void sendMassage(JSONObject v) {
        //!!!!!!!!Filter The massage in the files
        try {
            out.writeObject(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void sendMassageToAllUser(JSONObject v) {
        //!!!!!!!!Filter The massage in the files
        for (ClientHandler c : handlers) {
            c.sendMassage(v);
        }

    }

    //
    public void SaveMassageInUserFile(String path, ArrayList<ArrayList<Object>> Massages) {

    }

    //
    public void SaveMassageInTempFile(String path, ArrayList<ArrayList<Object>> Massages) {

    }

    //
    public void CheckPasswordToLogin() {
        do {                                                    /** To Check password and login*/
            try {
                JSONObject js = (JSONObject) in.readObject();
                String userName = (String) js.get("username");
                String Password = (String) js.get("password");
                if (user.get(userName) == null) {
                    System.out.println("the User Name is error");
                    //return the User Name is error
                    JSONObject errorMsg = new JSONObject();
                    errorMsg.put("type", "0");
                    errorMsg.put("loginMsg", "the User Name is error");
                    errorMsg.put("loginMsgNum", "0");
                    sendMassage(errorMsg);
                    return;
                }
                String pass = user.get(userName).password;
                System.out.println("passs" + pass);
                if (pass.matches(Password)) {
                    curentUser = user.get(userName);
                    System.out.println("the login is successful");
                    JSONObject loginMsg = new JSONObject();
                    loginMsg.put("type", "0");
                    loginMsg.put("loginMsg", "the login is successful");
                    loginMsg.put("loginMsgNum", "1");
                    sendMassage(loginMsg);
                    //return the login is successful
                } else {
                    System.out.println("the password is error");
                    JSONObject loginMsg = new JSONObject();
                    loginMsg.put("type", "0");
                    loginMsg.put("loginMsg", "the password  is Error");
                    loginMsg.put("loginMsgNum", "0");
                    sendMassage(loginMsg);
                    //return the password is error
                }
            } catch (IOException e) {

                System.out.println("user log out");
                return;
            } catch (ClassNotFoundException e) {
                System.out.println("user log out");
            }
        } while (curentUser == null);
        handlers.add(this);
//////
    }

    //
    public void ReadMassageFromUser() throws ClassNotFoundException, IOException {
        boolean temp = false; //to save a maasage in the userFile OR Temp File
        JSONObject reciveMassage = (JSONObject) in.readObject();
        System.out.println("+++" + String.valueOf(reciveMassage.get("UserSender")));
        for (ClientHandler handler : handlers) {
            if (handler.curentUser.UnqeuName.matches(String.valueOf(reciveMassage.get("UserSender")))) {
                System.out.println(handler.curentUser.UnqeuName + "=" + String.valueOf(reciveMassage.get(1)));
                reciveMassage.remove("UserSender");
                reciveMassage.put("UserSender", curentUser.UnqeuName);
                handler.sendMassage(reciveMassage);
                temp = true;
                break;
            }
        }
        if (temp == false) {
            //save a massage in the Temp File
            //SaveMassageInTempFile(,);

        } else { //the masssage is sended
            //save a massage in the user File
            //SaveMassageInUserFile(,);
        }
    }

    //
    public void CreateNewUser(User user) {

    }

    //End the Class ClientHandler
}
