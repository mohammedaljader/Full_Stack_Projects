package org.project.joboffers.Security;

import lombok.RequiredArgsConstructor;
import org.project.joboffers.Security.Filter.CustomAuthenticationFilter;
import org.project.joboffers.Security.Filter.CustomAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    String jobPublisher = "Job_publisher";
    String jobSeeker = "Job_seeker";
    String Admin = "Admin";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    // TODO: 11/13/2021 change application controller to job_seeker role
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/api/user/register", "/api/token/refresh").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/jobs/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/applications/**").permitAll();
        http.authorizeRequests().antMatchers("/api/application/**").permitAll();
        http.authorizeRequests().antMatchers("/chat/**").permitAll();
        http.authorizeRequests().antMatchers("/api/favorite/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").permitAll();
        http.authorizeRequests().antMatchers("/api/job/file/**","/api/job/candidates/**" , "/api/files/{id}","/api/cv/files/**" ).permitAll();
        http.authorizeRequests().antMatchers("/api/job/candidates/**").permitAll();
        http.authorizeRequests().antMatchers("/api/jobs/category/**").permitAll();
        http.authorizeRequests().antMatchers("/api/jobs/filter/**").permitAll();
        http.authorizeRequests().antMatchers("/api/favorite/**").permitAll();
        http.authorizeRequests().antMatchers("/api/msg/**").hasAnyAuthority(Admin, jobPublisher, jobSeeker);
        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority(Admin);
        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority(Admin);
        http.authorizeRequests().antMatchers(GET, "/api/categories/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/category/**").hasAnyAuthority(Admin);
        http.authorizeRequests().antMatchers(GET, "/api/job/**").hasAnyAuthority(Admin,jobPublisher,jobSeeker);
        http.authorizeRequests().antMatchers(POST, "/api/job/**").hasAnyAuthority(Admin,jobPublisher);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000/")); // <-- you may change "*"
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList(
                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
