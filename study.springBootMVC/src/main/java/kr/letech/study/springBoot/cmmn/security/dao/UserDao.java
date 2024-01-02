/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study.springBoot.board.vo.FileVO;
import kr.letech.study.springBoot.cmmn.security.vo.UserAuthVO;
import kr.letech.study.springBoot.cmmn.security.vo.UserVO;

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
@Mapper
public interface UserDao {
	UserVO getInfoId(String userId);
	
	/**
	 * 회원가입
	 * @param user
	 * @return
	 */
	void insertUser(UserVO user);
	
	/**
	 * 권한
	 */
	List<String> getRole(String userId);
	
	/**
	 * 권한 삽입
	 * @param userAuthInfo
	 */
	void insertRole(UserAuthVO userAuthInfo);
	
	/**
	 * 파일 등록
	 * @param file
	 */
	void insertFile(FileVO file);
}
