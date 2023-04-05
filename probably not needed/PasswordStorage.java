import java.util.*;
import java.io.*;

class PasswordStorage {
	public ArrayList<String> storage = new ArrayList<>();

	public void processFile(String addPassword) throws Exception {
		storePassword();
		appendFile(addPassword);
	}

	
	public void storePassword() throws Exception {
		FileReader fileReader = new FileReader("passwords.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			storage.add(line);
		}

		bufferedReader.close();
		fileReader.close();
	
	}

	public void appendFile(String addedPassword) throws Exception{
		FileWriter fileWriter = new FileWriter("passwords.txt", false);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		storage.add(addedPassword);

		for (int i = 0; i < storage.size(); i++) {
			String output = storage.get(i);
			bufferedWriter.write(output);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
		fileWriter.close();		
	}
}
