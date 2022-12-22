package lmp.util;

import java.security.MessageDigest;

public class ShaPasswordEncoder {

    public static String encrypt(String txt) throws Exception{
    	
        StringBuffer sbuf = new StringBuffer();

        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        mDigest.update(txt.getBytes());

        byte[] msgStr = mDigest.digest() ;

        for (byte b : msgStr) {
            sbuf.append(String.format("%02x",b));
        }

        return sbuf.toString();
    }

    public static boolean matches(String pw, String encryptPassword) throws Exception {
        if (encryptPassword.equals(encrypt(pw))) {
            return true;
        } else {
            return false;
        }
    }

}
