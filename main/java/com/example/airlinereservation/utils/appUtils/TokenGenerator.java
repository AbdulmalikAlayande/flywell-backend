package com.example.airlinereservation.utils.appUtils;

import org.apache.kafka.common.protocol.types.Field;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TokenGenerator {
	
	@Contract(pure = true)
	public static @NotNull String generateSessionToken(){
		return "PADI - "+UUID.randomUUID().toString();
	}
	
	@Contract(pure = true)
	public static @NotNull String generateOtp(){
		return "";
	}
}
