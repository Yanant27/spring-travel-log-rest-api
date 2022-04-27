package hyk.springframework.springtravellogrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        // Use this method instead of PasswordEncoder id {noop} at configure() method
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new LdapShaPasswordEncoder();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hyk")
                .password("yanant")
//                .authorities("ADMIN")
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password("ba788111341388ba1f140df78e8bd27b90496248111ffd463a6cd0a022e5ce2d4b5f0d4f29a44131")
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
