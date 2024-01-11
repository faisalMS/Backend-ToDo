package com.example.fullstacktodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FullStackTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackTodoApplication.class, args);
    }

//    @Bean
//    public WebMvcAutoConfiguration corsConfigurer(){
//        return new WebMvcAutoConfiguration() {
//
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("GET","PUT", "POST", "DELETE").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOrigins("http://localhost:3000");
        }
    };
}
}
