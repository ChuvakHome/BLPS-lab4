package ru.itmo.se.bl.lab4.config;

//@Configuration
public class CamundaSecurityFilter {
}

//    @Bean
//    public FilterRegistrationBean<Filter> processEngineAuthenticationFilter() {
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//        registration.setName("camunda-auth");
//        registration.setFilter(getProcessEngineAuthenticationFilter());
//        registration.addInitParameter("authentication-provider",
//                "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
//        registration.addUrlPatterns("/*");
//        registration.setOrder(1);
//        return registration;
//    }
//
//    @Bean
//    public Filter getProcessEngineAuthenticationFilter() {
//        return new ProcessEngineAuthenticationFilter();
//    }
//}
