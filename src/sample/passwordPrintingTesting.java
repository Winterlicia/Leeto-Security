/*
NOTES:

 */

package sample;

import java.io.*;
import java.util.ArrayList;

public class passwordPrintingTesting {
    public static ArrayList<String> storage = new ArrayList<>();

    /*
    public static void main(String[]args) throws Exception {
        Algorithms algorithm = new Algorithms();
        Password password = new Password("password", "3945", algorithm.getDate_Time(""));
        String name = password.name;
        String key = password.key;
        String time = password.timeCreated;

        storePassword();
        appendFile(name, key, time);

    }*/

    public static void storePassword() throws Exception {
        FileReader fileReader = new FileReader("passwordlist.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            storage.add(line);
        }

        bufferedReader.close();
        fileReader.close();
    }
    public static void appendFile(String addedPassword, String key, String time) throws Exception{
        FileWriter fileWriter = new FileWriter("passwordlist.txt", false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        storage.add(addedPassword+"/"+key+"/"+time);

        for (int i = 0; i < storage.size(); i++) {
            String output = storage.get(i);
            bufferedWriter.write(output);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }





    // IN PROCESS OF TESTING; DOESN'T WORK YET:
    public static void writeFile(String name, String key, String time) throws Exception{
        FileWriter fileWriter = new FileWriter("passwordlist.txt", false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        boolean alreadyInFile = false;
    /*
        //check if the password is already within the file
        for (int i = 0; i < storage.size(); i++) {
            String splitName = storage.get(i);
            String[] parts = splitName.split(",");
            if (storage.get(i).equals(parts[0])){
                alreadyInFile = true;
                break;
            }
        }*/
        //If not in the file, then add the new password with the key and time created
        if (alreadyInFile == false) {
            storage.add(name + "," + key + "," + time);
        }

        for (int i = 0; i < storage.size(); i++) {
            String output = storage.get(i);
            bufferedWriter.write(output);
            bufferedWriter.newLine();
        }

    }
}
