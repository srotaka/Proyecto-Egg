package grupo7.egg.nutrividas.security;

import grupo7.egg.nutrividas.servicios.CredencialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(credencialServicio).passwordEncoder(encoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/bootstrap/**","/venobox/**","/isotope-layout/**","/boxicons/**","/icofont/**","/owl.carousel/**","/php-email-form/**","/img/**","/jquery/easing/**","/jquery/**","/js/**","/css/**","/", "/login", "/signup/*", "/error", "/login-error","/registro/usuario").permitAll() // Recursos permitidos
                .antMatchers("/**").permitAll() // Recursos protegidos
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/login?error=true")      //URL when authentication failse
                    .defaultSuccessUrl("/",true)
                    .permitAll()//Transition destination when authentication is successful
                .and()
                .logout()	//Logout settings
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       //Logout processing path
                    .logoutSuccessUrl("/login?logout=true")                                        //Path when logout is completed
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .anyRequest()
                    .fullyAuthenticated();
    }
}
