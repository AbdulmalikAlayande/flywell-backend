package app.bola.flywell.services.userservice;

import app.bola.flywell.dtos.Response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
	ApiResponse<?> uploadFile(MultipartFile multipartFile);
	
}
