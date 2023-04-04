import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;


public class pbkdf2testing {
    private static final int SALT_LENGTH = 16; // length of salt in bytes
    private static final int ITERATIONS = 100000; // number of iterations for PBKDF2

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a password: ");
        String password = sc.nextLine();

        String hashedPassword = hashPassword(password);
        System.out.println(hashedPassword);

        System.out.println("Original Password? ");
        String guess = sc.nextLine();

        boolean isVerified = verifyPassword(guess, hashedPassword);
        String result = isVerified == true ? "Passed" : "Failed";
        System.out.println(result);

        sc.close();
    }

    public static String hashPassword(String password) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        // Use PBKDF2 with SHA-256 to derive a 256-bit hash of the password
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = pbkdf2(password.toCharArray(), salt, ITERATIONS, 512);

        // Encode the salt and hash values as a base64 string for storage in the database
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encodedHash = Base64.getEncoder().encodeToString(hash);

        return encodedSalt + "$" + encodedHash; // concatenate salt and hash with a delimiter
    }

    public static boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split("\\$");
        String encodedSalt = parts[0];
        String encodedHash = parts[1];
        byte[] salt = Base64.getDecoder().decode(encodedSalt);
        byte[] expectedHash = Base64.getDecoder().decode(encodedHash);

        // Hash the input password with the same salt and iteration count as the stored hash
        byte[] actualHash = pbkdf2(password.toCharArray(), salt, ITERATIONS, expectedHash.length * 8);

        //The actual key value is always going to be the same even if the salt and hash is randomized, which is why it can checked in this way.
        System.out.println(actualHash);

        // Compare the actual hash with the expected hash
        return MessageDigest.isEqual(expectedHash, actualHash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKey key = factory.generateSecret(spec);
        return key.getEncoded();
    }
}
