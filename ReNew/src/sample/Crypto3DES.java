package sample;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Crypto3DES {
	private KeySpec keySpec = null;
	private SecretKey key = null;
	private IvParameterSpec iv = null;
	
	public Crypto3DES(byte[] myKey, byte[] myIV) {
		try {
			keySpec = new DESedeKeySpec(myKey);
			key = SecretKeyFactory.getInstance("DESede").generateSecret(keySpec);
			iv = new IvParameterSpec(myIV);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public byte[] encrypt(byte[] myData) {
		try {
			Cipher ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key, iv);
			return ecipher.doFinal(myData);
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public byte[] decrypt(byte[] myData) {
		try {
			Cipher dcipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			dcipher.init(Cipher.DECRYPT_MODE, key, iv);
			return dcipher.doFinal(myData);
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}


} 
