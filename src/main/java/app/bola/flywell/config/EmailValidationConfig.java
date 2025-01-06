package app.bola.flywell.config;

import app.bola.flywell.validator.EmailDomainValidator;
import app.bola.flywell.annotations.EmailPattern;
import app.bola.flywell.services.notifications.FieldValidator;
import app.bola.flywell.services.notifications.Validator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Collections;


@Configuration
@ComponentScan(basePackages = "app.bola.flywell.validator",
				basePackageClasses = {
					EmailPattern.class,
					EmailDomainValidator.class
				})
@EnableAutoConfiguration
@Getter
public class EmailValidationConfig {
	public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
	
	@Value("${totp.secret.key}")
	private String totpSecret;
	
	@Value("${mail.api.key}")
	private String brevoApiKey;
	
	@Bean
	public ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("mail/MailMessages");
		return messageSource;
	}
	@Bean
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(templateResolver());
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}
	
	private ITemplateResolver templateResolver(){
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(2);
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public EmailDomainValidator validEmailDomain() {
		return new EmailDomainValidator();
	}

	@Bean
	public Context context() {
	return new Context();
	}
	
	@Bean
	public Validator getValidator(){
		return new FieldValidator();
	}
	@Bean
	public EmailValidationConfig validationConfig(){
		return new EmailValidationConfig();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
}
