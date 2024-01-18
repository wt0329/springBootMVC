/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.letech.study.springBoot.cmmn.security.dao.UserDao;
import kr.letech.study.springBoot.cmmn.security.vo.UserAuthVO;
import kr.letech.study.springBoot.cmmn.security.vo.UserDetailsVO;
import kr.letech.study.springBoot.cmmn.security.vo.UserVO;
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
public class UserLoginServiceImpl implements UserDetailsService{

	private final UserDao userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO userInfo = userDAO.getInfoId(userId);
		if (userInfo == null) {
			return null;
		} else {

			UserDetailsVO userDetailsVO = new UserDetailsVO();
			userDetailsVO.setUsername(userInfo.getUserId());
			userDetailsVO.setPassword(userInfo.getUserPw());
			userDetailsVO.setAuthorities(userDAO.getRole(userId));
			return userDetailsVO;
		}
	}
	
	public void registerUser(UserVO user) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getUserPw()); // BCryptPasswordEncoder로 암호화
        user.setUserPw(encodedPassword); 

        // 사용자 정보 저장
        userDAO.insertUser(user);
        
        // 권한 정보 등록
        UserAuthVO userAuthInfo = new UserAuthVO();
        userAuthInfo.setUserId(user.getUserId());
        userAuthInfo.setAuthCd("ROLE_USER"); // 기본값 'USER'로 설정

        userDAO.insertRole(userAuthInfo); // 해당 메서드는 UserMapper에 있어야 합니다.
	}

}
