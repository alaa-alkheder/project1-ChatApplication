package sample;
import org.json.simple.JSONObject;
//import org.omg.CORBA.Object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

import static sample.Main.user;

public class ClientHandler extends Thread {
    final int prot =8888;
    User curentUser;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    static HashSet<ClientHandler> handlers = new HashSet<ClientHandler>();
    static HashMap<String,ClientHandler>  Active= new HashMap<String, ClientHandler>();
    ArrayList<Object> Mass;
    ArrayList<ArrayList<Object>> MassageQueue =new ArrayList<ArrayList<Object>>();

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
                if(user.get(userName)==null) {
                    System.out.println("the User Name is error");
                    //return the User Name is error
                    String m="the User Name is error";
                    Mass.add(1,m);
                     Mass.add(0,0);
                      sendMassage(Mass);
                }
                String pass= user.get(userName).password;
                System.out.println("passs"+pass);
                if(pass.matches(Password)){
                    curentUser= user.get(userName);
                    System.out.println("the login is seecsssfuly");
                    String m="the login is seecsssfuly";
                    Mass.add(0,0);
                    Mass.add(1,m);
                    sendMassage(Mass);

                    //return the login is seecsssfuly
                }else {
                    System.out.println("the password is error");
                    String m="the password  is Error";
                    Mass.add(0,0);
                    Mass.add(1,m);
                    sendMassage(Mass);
                    //return the password is error
             }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("user log out");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("user log out");
            }
        }while (curentUser==null);
          handlers.add(this);
///////////////////////!!!!!!!!!!!! The User is logined !!!!!!!!!!!!!!!!///////////////////////////

        try {
                    //Update the Online User in the Client
                      UpdateTheOnlineListForAllUser();
            // !!!!!!!!!!!!!!send a massage to user for allow to send your massage


            /////////////////////////!!!!!!!!!!!! the Old massage was send to user !!!!!!!!!!!!!!!!///////////////////////////


            ///////////////////////!!!!!!!!!!!! Start Chat !!!!!!!!!!!!!!!!///////////////////////////

            while (true){

                boolean temp =false; //to save a maasage in the userFile OR Temp File
                System.out.println("Server up");
                ArrayList<Object> reciveMassage = (ArrayList<Object>) in.readObject();
                System.out.println("+++"+String.valueOf(reciveMassage.get(1)));
                for (ClientHandler handler : handlers) {
                 if(   handler.curentUser.UnqeuName.matches(String.valueOf(reciveMassage.get(1)))){
                     System.out.println(handler.curentUser.UnqeuName+"="+String.valueOf(reciveMassage.get(1)));
                     reciveMassage.set(1,curentUser.UnqeuName);
                     handler.sendMassage(reciveMassage);
                     temp =true;
                     break;
                 }
                }
                if (temp==false){
                    //save a massage in the Temp File
                    //SaveMassageInTempFile(,);

                }else{ //the masssage is sended
                //save a massage in the user File
                //SaveMassageInUserFile(,);
                }

        }

        } catch (IOException e) {
//            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
        finally {
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
public void UpdateTheOnlineListForAllUser(){
    Mass=new ArrayList<Object>();
    Mass=new ArrayList<Object>();
    Mass.add(0,"2");
    System.out.println("____" + String.valueOf(Mass.get(0)));
    for (ClientHandler c: handlers){
        Mass.add(c.curentUser.UnqeuName);
    }
    sendMassageToAllUser(Mass);

}
//
    public void UpdateTheUserListForAllUser(){
        //the Type is 3

    }
//
    public  void  sendMassage (ArrayList<Object> v ){
//!!!!!!!!Filter The massage in the files
        try {
            out.writeObject(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    public  void  sendMassageToAllUser (ArrayList<Object> v ){
//!!!!!!!!Filter The massage in the files
        for (ClientHandler c: handlers){
                        c.sendMassage(v);
                   }

    }
    //
    public void SaveMassageInUserFile (String path,ArrayList<ArrayList<Object>> Massages){

    }
    //
    public void SaveMassageInTempFile (String path,ArrayList<ArrayList<Object>> Massages){

    }


    //End the Class ClientHandler
}
