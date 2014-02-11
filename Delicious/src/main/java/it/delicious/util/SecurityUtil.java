/**
 * 
 */
package it.delicious.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.apache.log4j.Logger;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
public class SecurityUtil {
	
	private final static String ALGORITH_DIGEST = "MD5";
	private final static String CHARSET = "UTF-8";
	
	@Inject
	public transient static Logger log;
	
	/**
	 * 
	 * Singleton Pattern
	 * 
	 */
	private SecurityUtil(){
		
	}

	public static String convertStringToMD5(String valor){
    	MessageDigest mDigest;
    	try {
    		mDigest = MessageDigest.getInstance(ALGORITH_DIGEST);
    		
    		byte[] valorMD5 = mDigest.digest(valor.getBytes(CHARSET));
    		
    		StringBuffer sb = new StringBuffer();
    		for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) |
						0x100).substring(1, 3));
			}
    		
    		return sb.toString();
    	} catch (NoSuchAlgorithmException e){
    		e.printStackTrace();
    		log.debug(e);
    		return null;
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    		log.debug(e);
    		return null;
		}
    }
}
