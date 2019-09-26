package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserFile implements WriteAndReadUser {
    private String s = "C:\\Users\\Viktor\\Desktop\\user.txt";
    @Override
    public void write(User user) {

     try {

         File file = new File(s);
         FileWriter writer = new FileWriter(file, true);
         BufferedWriter bufferedWriter = new BufferedWriter(writer);
         bufferedWriter.write(  user.getName() + " | " + user.getPassword() +
                              " | " + user.getEmail() + " | " + user.getCountry() + "\n");
         bufferedWriter.close();
     }catch (IOException e) {
         System.out.println(e);
     }
    }

       @Override
    public List<User> readUser() {
           List<User> list = new ArrayList<>();
           try (Scanner scanner = new Scanner(
                   new FileInputStream(s))) {

               while (scanner.hasNextLine()) {
                   String line = scanner.nextLine();
                   String[] tokens = line.split("\\|");
                   String name = tokens[0];
                   String password = tokens[1];
                   String email = tokens[2];
                   String country = tokens[3];
                   list.add(new User(name, password, email, country));
               }

           } catch (IOException e) {
               System.out.println(e);
           }
           return list;
       }

}
