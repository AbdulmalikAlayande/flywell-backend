package app.bola.flywell.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@EnableAutoConfiguration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}
	
	@Bean
	public UUID getUUID(){
		return new UUID(2, 4);
	}
	
}
