package boteco.tilapia.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import boteco.tilapia.services.ClienteUserDetailsService;
import boteco.tilapia.services.VendedorUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfigProject extends WebSecurityConfigurerAdapter {
    
	@Autowired
    private VendedorUserDetailsService vendedorUserDetailsService;

    @Autowired
    private ClienteUserDetailsService clienteUserDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .antMatchers("/img/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/js/**").permitAll()    
        .antMatchers("/vendor/**").permitAll()
        .anyRequest().authenticated();
    	
    	http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();

        http.logout()
        .logoutRequestMatcher(
            new AntPathRequestMatcher("/logout", "GET")
        )
        .logoutSuccessUrl("/login");

        http.rememberMe()
        .key("keyRemember-me");
        

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clienteUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(vendedorUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

}
