/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.letech.study.springBoot.cmmn.security.service.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	
	
	    private final CustomAuthenticationProvider authentication;
	    private final UserLoginServiceImpl service;
	    
	
	    @PostMapping("/api/v1/login")
	    public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String userPw) {
	        try {
	            // 사용자의 로그인 인증을 위해 CustomAuthenticationProvider를 사용
	            Authentication authResult = authentication.authenticate(
	                    new UsernamePasswordAuthenticationToken(userId, userPw)
	            );
	            // 인증 객체를 SecurityContext에 설정
	            SecurityContextHolder.getContext().setAuthentication(authResult);
	            // 인증이 성공하면 로그인 성공 메시지 반환
	            return ResponseEntity.ok("로그인 성공");
	        } catch (AuthenticationException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
	        }
	    }
	}

