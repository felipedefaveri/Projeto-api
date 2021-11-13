package org.serratec.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
      @Autowired
      public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception {
          auth.inMemoryAuthentication()
          .withUser("felipe").password("1234").roles("usuario").and()
          .withUser("admin").password("4321").roles("admin","usuario");
      
      }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
        .and()
        .authorizeRequests()            
        .antMatchers("/h2-console").permitAll()
        .antMatchers ( HttpMethod . POST , "/cliente/**" ) .hasAuthority ("usuario")
        .antMatchers ( HttpMethod . GET , "/cliente/**" ) .hasAuthority ("usuario")
        .antMatchers ( HttpMethod . PUT , "/cliente/**" ) . hasAuthority ("usuario")
        .antMatchers ( HttpMethod . DELETE , "/cliente/**" ) . hasAuthority ("usuario")
        .antMatchers ( HttpMethod . POST , "/categoria/**" ) . permitAll ()
        .antMatchers ( HttpMethod . GET , "/categoria/**" ) . permitAll ()
        .antMatchers ( HttpMethod . PUT , "/categoria/**" ) . hasAuthority ("admin")
        .antMatchers ( HttpMethod . DELETE , "/categoria/**" ) . hasAuthority ("admin")
        .antMatchers ( HttpMethod . POST , "/produto/**" ) . permitAll ()
        .antMatchers ( HttpMethod . GET , "/produto/**" ) . permitAll ()
        .antMatchers ( HttpMethod . PUT , "/produto/**" ) . hasAuthority ("admin")
        .antMatchers ( HttpMethod . DELETE , "/produto/**" ) . hasAuthority ("admin")
        .antMatchers ( HttpMethod . POST , "/pedido/**" ) .hasAuthority ("usuario")
        .antMatchers ( HttpMethod . GET , "/pedido/id/" ) . permitAll ()
        .antMatchers ( HttpMethod . PUT , "/pedido/**" ) . hasAuthority ("usuario")
        .antMatchers ( HttpMethod . DELETE , "/pedido/**" ) . hasAuthority ("admin")
        .and()
        .csrf().disable()
        .formLogin().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
