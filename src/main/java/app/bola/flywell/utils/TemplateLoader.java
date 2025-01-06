package app.bola.flywell.utils;

import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static app.bola.flywell.utils.Constants.TEMPLATE_LOAD_FAILED;

@Slf4j
public class TemplateLoader {
	
	public static String loadTemplateContent(Resource templateResource) throws InvalidRequestException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		try {
			InputStream inputStream = templateResource.getInputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
		} catch (IOException exception) {
			log.error(TEMPLATE_LOAD_FAILED, exception);
			throw new InvalidRequestException(TEMPLATE_LOAD_FAILED, exception);
		}
		return result.toString(StandardCharsets.UTF_8);
	}
}
