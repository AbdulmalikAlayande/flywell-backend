package com.example.airlinereservation.utils.appUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

import static com.example.airlinereservation.utils.appUtils.Constants.TEMPLATE_LOAD_FAILED;

@Slf4j
public class TemplateLoader {
	
	public static String loadTemplateContent(Resource templateResource){
		Formatter formatter = new Formatter();
		try {
			InputStream inputStream = templateResource.getInputStream();
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			return result.toString(StandardCharsets.UTF_8);
		} catch (IOException exception) {
			log.error(TEMPLATE_LOAD_FAILED, exception);
			return formatter.format("%s%s", TEMPLATE_LOAD_FAILED, exception.getMessage()).toString();
		}
	}
}
