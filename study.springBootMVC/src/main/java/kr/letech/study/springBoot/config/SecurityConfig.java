/**
 * 
 */
package kr.letech.study.springBoot.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <pre>
 * 
 * </pre>
 *  
 * << 개정이력 >>
 *   
 *  수정일      수정자		수정내용
 *  ------------------------------------------------
 *  2023-12-19  JS			최초 생성
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
        .and()
        //로그인관련설정(페이지,아이디이름비번어케할건지)
        .formLogin()
      //  .loginPage("") 
        .loginProcessingUrl("/api/v1/users")
        .usernameParameter("userId")
        .passwordParameter("userPw")
       // .defaultSuccessUrl("/")
        .permitAll()
        .and()
        .headers().frameOptions().sameOrigin();
        return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {// PasswordEncoder는 BCryptPasswordEncoder 의 인터페이스임
		return new BCryptPasswordEncoder();
	}

}
