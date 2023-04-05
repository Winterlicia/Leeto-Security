import java.util.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.math.BigInteger;

/* The goal of the code provided below is to convert passwords into salted hash passwords.
 * When the passwords are stored, it will be stored in the hashed format.
 * The combination of using a SHA-512 hash algorithm with an added randomized salt makes password hacking extremely difficult.
 */

public class hashingtestingv2 {
    //private static final int SALT_LENGTH = 16;
    //private static final int ITERATIONS = 100000;

    // just doing some testing:
    public static void main(String[]args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int count = scan.nextInt();

        PasswordRandomizer generate = new PasswordRandomizer();
        DateAndTime dt = new DateAndTime();

        String password = generate.randomize(count);
        String hashed = hashString(password);
        String date = dt.getDate_Time(password);

        System.out.println("The ORIGINAL password, "+ password +", was created on "+date);
        System.out.println("The HASHED password, "+ hashed +", was created on "+date);

        scan.close();
    }

    public static byte[] obtainSHA(String input) throws NoSuchAlgorithmException {
        // Create a new MessageDigest object using the SHA-256 hashing algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256"); //might need to change this to SHA-1 for length purposes.
        
        // Convert the input string to a byte array using the UTF-8 character set
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        
        // Compute the hash of the input byte array using the MessageDigest object
        byte[] hashedBytes = md.digest(inputBytes); 
        
        return hashedBytes;

    }

    public static String conversion(byte[] hash) {
        //Generating random bytes:
        SecureRandom random = new SecureRandom();
        //byte[] salt = new byte[SALT_LENGTH];
        //random.nextBytes(salt);

        //Convert the byte array in the signum representation
        //BigInteger class to perform operators on large integers, such as the ones used in this code.
        BigInteger num = new BigInteger(1, hash);

        // Create a new StringBuilder object to build the large hashed string / convert message digest to hex value
        StringBuilder convert = new StringBuilder(num.toString(16));

        //Padding with the leading zeros
        while (convert.length() < 32) {
            convert.insert(0, '0');
        }

        //MessageDigest digest = MessageDigest.getInstance("SHA-256");
        //String encodedSalt = Base64.getEncoder().encodeToString(salt);

        //System.out.println(convert.toString());
        return convert.toString();
    }

    public static String hashString(String input) throws NoSuchAlgorithmException {
        String hashed = conversion(obtainSHA(input));
        return hashed;
    }

}
