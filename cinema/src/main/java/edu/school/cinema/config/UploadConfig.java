//package edu.school.cinema.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.multipart.support.MultipartFilter;
//
//@Configuration
//public class UploadConfig {
//
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
//        multipart.setMaxUploadSize(3 * 1024 * 1024);
//        return multipart;
//    }
//
//    @Bean
//    public MultipartFilter multipartFilter() {
//        MultipartFilter multipartFilter = new MultipartFilter();
//        multipartFilter.setMultipartResolverBeanName("multipartResolver");
//        return multipartFilter;
//    }
//}