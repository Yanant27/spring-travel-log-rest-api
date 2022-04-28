package hyk.springframework.springtravellogrestapi.config;

import hyk.springframework.springtravellogrestapi.security.CustomPasswordEncoderFactories;
import hyk.springframework.springtravellogrestapi.security.RestHeaderAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new StandardPasswordEncoder();
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return CustomPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hyk")
                .password("{ldap}{SSHA}/dVP3h3b7eSjNGkf4mDOa8e1r9ocoxwqn72msQ==")
//                .authorities("ADMIN")
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password("{sha256}f020cfc4a39ab63937befaf3e310d7f419f49c5861e8c2fded1244acc664a5670211a7f80d71c376")
//                .authorities("USER")
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("editor")
                .password("{bcrypt}$2a$10$TG/J6ztXz/lBckCpIdchHuJLDqAkg3apfXc2Jqk91YZzLfr.twQHO")
                .roles("EDITOR");
//                .authorities("EDITOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests(authorize -> {
                    // permit all request with "GET" for specified ant pattern
                    authorize.antMatchers(HttpMethod.GET, "/api/v1/travellogs").permitAll()
                    // permit all request with "GET" for specified mvc pattern
                    .mvcMatchers(HttpMethod.GET, "/api/v1/travellogs/{id}")
                            .hasAnyRole("ADMIN", "USER", "EDITOR")
//                            .hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "EDITOR");
                })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable(); // csrf is enabled by default in spring
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
