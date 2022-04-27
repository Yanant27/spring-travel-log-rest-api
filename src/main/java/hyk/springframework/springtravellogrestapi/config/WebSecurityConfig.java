package hyk.springframework.springtravellogrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // permit all request with "GET" for specified ant pattern
//                .antMatchers(HttpMethod.GET, "/api/v1/travellogs/**").permitAll()
                // permit all request with "GET" for specified mvc pattern
                .mvcMatchers(HttpMethod.GET, "/api/v1/travellogs/{id}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // csrf is default in spring
    }
}
