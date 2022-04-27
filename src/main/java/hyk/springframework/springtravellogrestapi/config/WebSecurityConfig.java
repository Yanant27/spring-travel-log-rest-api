package hyk.springframework.springtravellogrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hyk")
                .password("{noop}yanant")
//                .authorities("ADMIN")
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password("{noop}guest")
//                .authorities("USER")
                .roles("USER");
//        auth.inMemoryAuthentication()
//                .withUser("guest")
//                .password("guest")
//                .roles("GUEST")
//                .authorities("GUEST");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    // permit all request with "GET" for specified ant pattern
                    authorize.antMatchers(HttpMethod.GET, "/api/v1/travellogs").permitAll()
                    // permit all request with "GET" for specified mvc pattern
                    .mvcMatchers(HttpMethod.GET, "/api/v1/travellogs/{id}")
                            .hasAnyRole("ADMIN", "USER")
//                            .hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT).hasRole("ADMIN");
                })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // csrf is default in spring
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("hyk")
//                .password("yanant")
////                .roles("ADMIN")
//                .authorities("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("guest")
//                .password("guest")
////                .roles("USER")
//                .authorities("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
