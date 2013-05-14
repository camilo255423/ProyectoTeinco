package com.proyecto.seguridad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Codificador {
	 
    public static final String SHA512 = "SHA-512";
 
    public static String getEncoded(String texto, String algoritmo) throws NoSuchAlgorithmException {
        MessageDigest md;
        String output = "";
        try {
            md= MessageDigest.getInstance(algoritmo);
            md.update(texto.getBytes());
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                output += s;
            }
        } catch (NoSuchAlgorithmException nsae) {
        	 Log.d("SHA","Output: "+nsae.getMessage());
        }
        catch (Exception nsae) {
        	Log.d("SHA","Output: "+nsae.getMessage());
        }
        
        return output;
    }
}