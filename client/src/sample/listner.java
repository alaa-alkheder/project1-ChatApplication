//package sample;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class listner extends Thread {
//    ObjectInputStream in;
//    listner(InputStream input)  {
//        try {
//
//            in = new ObjectInputStream(input);
//            System.out.println("8888");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void run() {
//        while (true){
//            ArrayList mass= null;
//            try {
//                mass = (ArrayList<Object>) in.readObject();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            System.out.println("____"+String.valueOf(mass.get(0)));
//        }
//
//    }
//}
