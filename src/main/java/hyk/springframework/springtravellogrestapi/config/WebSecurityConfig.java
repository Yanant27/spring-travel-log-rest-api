package hyk.springframework.springtravellogrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    // permit all request with "GET" for specified ant pattern
                    authorize.antMatchers(HttpMethod.GET, "/api/v1/travellogs").permitAll()
                    // permit all request with "GET" for specified mvc pattern
                    .mvcMatchers(HttpMethod.GET, "/api/v1/travellogs/{id}")
                            .hasAnyAuthority("ADMIN", "USER")
//                            .hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT).hasAuthority("ADMIN");
                })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // csrf is default in spring
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("hyk")
                .password("yanant")
//                .roles("ADMIN")
                .authorities("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("guest")
                .password("guest")
//                .roles("USER")
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
