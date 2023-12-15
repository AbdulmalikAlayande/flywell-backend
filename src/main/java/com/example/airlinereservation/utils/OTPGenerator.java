package com.example.airlinereservation.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.stream.IntStream;

public class OTPGenerator {
	
	@Value("${totp.secret.key}")
	private static String OTPSecret;
	
	private static final char[] BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
	private static final int[] BITS_LOOKUP = new int[128];
	private static final String HMAC_ALGO = "HmacSHA512";
	private static final int TOTP_LENGTH = 6;
	private static final int TIME_STEP = 60;
	
	static {
		for (int i = 0; i < BASE32_CHARS.length; i++) {
			BITS_LOOKUP[BASE32_CHARS[i]] = i;
		}
	}
	
	public static String encodeBase32(String input) {
		StringBuilder encoded = new StringBuilder();
		String dataToBeEncoded = OTPSecret + input;
		int buffer = 0;
		int bufferLength = 0;
		
		for (byte b : dataToBeEncoded.getBytes()) {
			buffer <<= 8;
			buffer |= b & 0xFF;
			bufferLength += 8;
			
			while (bufferLength >= 5) {
				int index = (buffer >> (bufferLength - 5)) & 0x1F;
				encoded.append(BASE32_CHARS[index]);
				bufferLength -= 5;
			}
		}
		while (encoded.length() % 8 != 0) {
			encoded.append('=');
		}
		return encoded.toString();
	}
	
	public static String decodeBase32(String base32) {
		base32 = base32.toUpperCase().replaceAll("[=]", "");
		StringBuilder decoded = new StringBuilder();
		int buffer = 0;
		int bufferLength = 0;
		for (char c : base32.toCharArray()) {
			buffer <<= 5;
			buffer |= BITS_LOOKUP[c];
			bufferLength += 5;
			while (bufferLength >= 8) {
				byte b = (byte) (buffer >> (bufferLength - 8));
				decoded.append((char) b);
				bufferLength -= 8;
			}
		}
		return decoded.toString();
	}
	
	private static String generateTOTP(String secretKey, long timeInterval) {
		try {
			byte[] decodedKey = decodeBase32(secretKey).getBytes();
			byte[] timeIntervalBytes = new byte[8];
			for (int i = 7; i >= 0; i--) {
				timeIntervalBytes[i] = (byte) (timeInterval & 0xFF);
				timeInterval >>= 8;
			}
			Mac hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(decodedKey, HMAC_ALGO));
			byte[] hash = hmac.doFinal(timeIntervalBytes);
			return String.format("%0" + TOTP_LENGTH + "d", getTotp(hash));
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate TOTP", e);
		}
	}
	
	private static int getTotp(byte[] hash) {
		int offset = hash[hash.length - 1] & 0xF;
		long mostSignificantByte = (hash[offset] & 0x7F) << 24;
		long secondMostSignificantByte = (hash[offset + 1] & 0xFF) << 16;
		long thirdMostSignificantByte = (hash[offset + 2] & 0xFF) << 8;
		long leastSignificantByte = hash[offset + 3] & 0xFF;
		
		long binaryCode = mostSignificantByte
				                  | secondMostSignificantByte
				                  | thirdMostSignificantByte
				                  | leastSignificantByte;
		
		return (int) (binaryCode % Math.pow(10, TOTP_LENGTH));
	}
	
	public static String generateTOTP(String secretKey) {
		long timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP;
		return generateTOTP(secretKey, timeInterval);
	}
	
	public static boolean validateTOTP(String secretKey, String inputTOTP) {
		long timeInterval = System.currentTimeMillis() / 1000 / TIME_STEP;
		return IntStream.of(-1, 0, 1)
				        .anyMatch(i -> generateTOTP(secretKey, timeInterval + i).equals(inputTOTP));
	}
}
