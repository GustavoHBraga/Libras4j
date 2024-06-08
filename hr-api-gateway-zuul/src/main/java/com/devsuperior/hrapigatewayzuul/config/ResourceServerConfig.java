package com.devsuperior.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;


@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtTokenStore jwtTokenStore;

    private static final String[] PUBLIC = {"/hr-oauth/oauth/token"};
    private static final String[] OPERATOR = {"/hr-worker/**"};
    private static final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**","/actuator/**", "/hr-worker/actuator/**", "/hr-oauth/actuator/**"};

    /*@Value("${http.role-public}")
    public static List<String> PUBLIC;

    @Value("${http.role-operator}")
    public static List<String> OPERATOR;

    @Value("${http.role-admin}")
    public static List<String> ADMIN;*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET,OPERATOR).hasAnyRole("OPERATOR","ADMIN")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated(); // Qualquer outra rota pode acessar se estiver autenticado
    }


}
