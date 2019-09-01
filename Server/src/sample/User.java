package sample;

import java.io.Serializable;

public class User implements Serializable {

    public  String Name ;
    public String UnqeuName; //we use unqeu Name to login
    public String Birthday;
    public String Email;
    public String Gender;
    public String Description;
    public String password;
    public int Position;      // zero in a manger  // one is a IT manger  // two is a employ
    public  boolean isWork =true; //after the employ finish  work in the company  convert is work to false


}
