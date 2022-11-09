package com.example.trua_nay_an_gi.config;

//import com.example.vn_social_network.service.app_users.UserService;
import com.example.trua_nay_an_gi.config.filter.JwtAuthenticationFilter;
import com.example.trua_nay_an_gi.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class AppSecConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/users").hasRole("ADMIN")
                .and()
//                .authorizeRequests().antMatchers("/api/**add ").hasRole("ADMIN")
//                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/dangxuat"));
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/login/**","/register").permitAll()
//                .and().authorizeRequests().anyRequest().authenticated()
//                .and().csrf().disable();


//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling();
//        http.cors().configurationSource(
//                request -> new CorsConfiguration().applyPermitDefaultValues());

        http.cors().configurationSource(c -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.applyPermitDefaultValues();
            configuration.addAllowedOriginPattern("*");
//            configuration.addAllowedMethod(CorsConfiguration.ALL);
            configuration.addAllowedMethod(HttpMethod.DELETE);
            configuration.addAllowedMethod(HttpMethod.GET);
            configuration.addAllowedMethod(HttpMethod.POST);
            configuration.addAllowedMethod(HttpMethod.PUT);
            configuration.addAllowedMethod(HttpMethod.HEAD);
            configuration.addAllowedMethod(HttpMethod.PATCH);
            return configuration;
        });
    }

    // xắc thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
