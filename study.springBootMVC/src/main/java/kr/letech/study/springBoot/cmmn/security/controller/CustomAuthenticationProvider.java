/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.controller;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.letech.study.springBoot.cmmn.security.vo.UserDetailsVO;
import lombok.RequiredArgsConstructor;

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
@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String) authentication.getPrincipal();
        String userPw = (String) authentication.getCredentials();

        UserDetailsVO user = (UserDetailsVO) userDetailsService.loadUserByUsername(userId);
        
        // DB에 저장된 암호화된 비밀번호와 사용자가 입력한 비밀번호를 BCryptPasswordEncoder로 비교
        if (!passwordEncoder.matches(userPw, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException("계정이 활성화되어 있지 않습니다.");
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return new UsernamePasswordAuthenticationToken(userId, userPw, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
