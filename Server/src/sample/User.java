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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUnqeuName() {
        return UnqeuName;
    }

    public void setUnqeuName(String unqeuName) {
        UnqeuName = unqeuName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
}
