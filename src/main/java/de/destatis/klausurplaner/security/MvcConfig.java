package de.destatis.klausurplaner.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
        registry.addResourceHandler("/static/**")
          .addResourceLocations("../../../../Klausurplaner-Frontend/klausurplaner/src");
        registry.addResourceHandler("/*.js")
          .addResourceLocations("../../../../Klausurplaner-Frontend/klausurplaner/src");
        registry.addResourceHandler("/*.json")
          .addResourceLocations("../../../../Klausurplaner-Frontend/klausurplaner/src");
        registry.addResourceHandler("/*.ico")
          .addResourceLocations("../../../../Klausurplaner-Frontend/klausurplaner/src");
        registry.addResourceHandler("/index.html")
          .addResourceLocations("../../../../Klausurplaner-Frontend/klausurplaner/src");
    }
    */
}
