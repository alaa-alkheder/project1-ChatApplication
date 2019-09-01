package sample;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler extends Thread {
    final int prot =8888;
    User curentUser;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    static Set<ClientHandler> handlers = new HashSet<ClientHandler>();
    ArrayList<Object> Mass;
    public ClientHandler(Socket s) throws  IOException
    {
        curentUser =null;
        socket =s;
          in =new ObjectInputStream(s.getInputStream());
          out =new ObjectOutputStream(s.getOutputStream());

    }
    @Override
    public void run() {

        do {                                                    /* To Check password and login*/
            try {
                Mass = (ArrayList<Object>) in.readObject();
                String userName= (String) Mass.get(0);
                String Password= (String) Mass.get(1);
                if(Main.user.get(userName)==null) {
                    System.out.println("the User Name is error");
                    //return the User Name is error
              String      m="the User Name is error";
            Mass.add(0,m);
            sendMassage(Mass);
                }
                String pass=Main.user.get(userName).password;
                System.out.println("passs"+pass);
                if(pass.matches(Password)){
                    curentUser=Main.user.get(userName);
                    System.out.println("the login is seecsssfuly");
                    String m="the login is seecsssfuly";
                    Mass.add(0,m);
                    sendMassage(Mass);
                    //return the login is seecsssfuly
                }else {
                    System.out.println("the password is error");
                    String m="the password  is Error";
                    Mass.add(0,m);
                    sendMassage(Mass);
                    //return the password is error
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }while (curentUser==null);

        System.out.println("the login is secssfuly");
///////////////////////!!!!!!!!!!!! The User is logined !!!!!!!!!!!!!!!!///////////////////////////

//send a massage to user for allow to send your massage
///////////////////////!!!!!!!!!!!! the Old massage was send to user !!!!!!!!!!!!!!!!///////////////////////////
        try {
            System.out.println("Server up");
            while (true){
            ArrayList<Object> v = (ArrayList<Object>) in.readObject();
            String m = String.valueOf(v.get(0));
            System.out.println("|||||||" + m);
        }
//            m="AAAAAAAAA111";
//            v.add(0,m);
//            sendMassage(v);
//            ob=m;
//            out.writeObject(v);

//            socket.close();
//
        } catch (IOException e) {
//            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }

    }


    public  void  sendMassage (ArrayList<Object> v ){

        try {
            out.writeObject(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
