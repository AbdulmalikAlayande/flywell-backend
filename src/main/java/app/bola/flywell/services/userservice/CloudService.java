package app.bola.flywell.services.userservice;

import app.bola.flywell.dto.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
	ApiResponse<?> uploadFile(MultipartFile multipartFile);
	
}
