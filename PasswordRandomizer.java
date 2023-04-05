//Creation of the password by randomizing a 4-letter String value. Will then be used in the hashing algorithm, which secures the password.

class PasswordRandomizer {

    /*public PasswordRandomizer(int numLetters) {
        int intLetters = numLetters;
    }*/
    public String randomize(int letterCount) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?-().";
        String generatedPassword = "";
        char randomLetter = ' ';
        StringBuilder generate = new StringBuilder(letterCount);
        int maximum = characters.length();

        // Keep appending the generatedPassword with StringBuilder, adding one random character at a time
        for (int i = 0; i < letterCount; i++) {
            randomLetter = characters.charAt((char) (Math.random() * (maximum + 1)));
            generatedPassword = (generate.append(randomLetter)).toString();
            
        }

        // If the first letter starts with a '-' or a '.'; the letter has to change
        while (generatedPassword.charAt(0) == '-' || generatedPassword.charAt(0) == '.') {
            randomLetter = characters.charAt((char) (Math.random() * 26 + 'a'));
            generatedPassword = randomLetter + generatedPassword.substring(1);
        }

        return generatedPassword;

    }

}