//package com.xlzhou.system.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Auther: zxl
// * @Date: 2020/11/24 - 11 - 24 - 15:51
// * 解决跨域问题   此跨域问题解决需在controller上加@CrossOrigin
// */
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
////    @Bean
////    public FilterRegistrationBean corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        // 设置你要允许的网站域名，*表示任意域名
//////        config.addAllowedOrigin("http://127.0.0.1");
////        config.addAllowedOrigin("*");
////
////        config.addAllowedHeader("*");//* 表示任意头部信息
////        config.addAllowedMethod("GET,POST,PUT,DELETE,HEAD,OPTIONS");
////        source.registerCorsConfiguration("/**", config); //设置跨域的路径
////        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        // 这个顺序很重要，为避免麻烦请设置在最前
////        bean.setOrder(0);
////        return bean;
////    }
//
//
//        @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/*")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                .allowedHeaders("*")  //是否可以携带cookie
//                .maxAge(3600);
//    }
//
//}
