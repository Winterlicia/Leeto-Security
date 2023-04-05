import java.io.*;

//Rating a password out of 5 based on the features: length, punctuation, numbers, capital letters, and if the password has words that appear in the dictionary.

class PasswordCheckingAlgorithm {

    public String ratePassword(String password) throws Exception {
        FileReader fileReader = new FileReader("englishdictionary.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

        int rating = 0;

        if (password.length() >= 8) {
            rating += 1;
        } 
        if (password.matches(".*[\\p{Punct}].*")){
            rating += 1;
        }
        if (password.matches(".*\\d+.*")) {
            rating += 1;
        }

        boolean hasUppercase = false;
        for (int i = 0; i < password.length(); i++) {
            char letter = password.charAt(i);
            if (Character.isUpperCase(letter)) {
                hasUppercase = true;
            }
        }

        if (hasUppercase == true) {
            rating += 1;
        }

        String line;
        boolean inDictionary = false;
        while ((line = bufferedReader.readLine()) != null) {
            if (password.equals(line)) {
                inDictionary = true;
                break;
            }
        }

        bufferedReader.close();
        fileReader.close();

        if (!inDictionary) {
            rating += 1;
        }

        String strRating = "";
        switch (rating) {
            case 1:
               strRating = "Very weak. You need to make improvements to your password, such as including more symbols for complexity or making it longer.";
               break;
            case 2:
               strRating = "Weak. Although not the worst, try to make more improvements to further strengthen your password.";
               break;
            case 3:
               strRating = "Ok. It can still be improved, but at least your password can defend against less experienced attackers.";  
               break;
            case 4:
               strRating = "Strong. Consider adding just a little more improvements to your password, and it will be extremely strong!";
               break;
            case 5:
               strRating = "Very strong. Your complex password, partnered with our advanced hashing algorithms, will surely defend against all types of attackers.";  
               break;

        }
        return strRating;         
    }

}    