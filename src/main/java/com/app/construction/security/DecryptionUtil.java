package com.app.construction.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.app.construction.dto.DecryptDataDto;
import com.app.construction.dto.EncryptData;

public class DecryptionUtil {

	public static SecretKey stringToSecretKey(String secretKeyString) {
		byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
		SecretKey secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
		return secretKey;
	}

	public static Object decrypt(DecryptDataDto encryptedData) throws Exception {
		SecretKey key = stringToSecretKey(encryptedData.getSecretKey());
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decodedData = Base64.getDecoder().decode(encryptedData.getEncryptedData());
			byte[] decryptedData = cipher.doFinal(decodedData);
//			EncryptData dataDto = deserialize(decryptedData, EncryptData.class);
			ByteArrayInputStream bis = new ByteArrayInputStream(decryptedData);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object object = ois.readObject();
			ois.close();
			return object;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	private static <T> T deserialize(byte[] bytes, Class<T> clazz) {
//		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
//			Object obj = in.readObject();
//			return clazz.cast(obj);
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
