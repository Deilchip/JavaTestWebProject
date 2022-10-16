package sbsecurity.hospital.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // Load file: validation.properties
        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\logo.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\nuclear.jpg")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\dental.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\ophtalmology.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\surgery.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\x-ray.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\cardio.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\neurology.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\img\\hicon.png")
                .addResourceLocations("file:resources/","file:uploads/","file:C:\\Users\\vlad\\Desktop\\SpringBootSecurityJPA\\src\\main\\resources\\templates\\style.css")
                .setCachePeriod(0);
    }

}