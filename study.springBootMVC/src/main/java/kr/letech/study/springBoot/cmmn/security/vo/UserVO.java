/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@Getter
@Setter
public class UserVO {
	private String userId;      // 사용자ID
	private String userPw;		// 사용자 비밀번호
	private String userNm;		// 사용자 이름
	private String userTelNo;   // 사용자 전화번호
	private String userEmail;   // 사용자 이메일
	private String userAddr;    // 사용자 주소
	private String rgstDt;		// 등록일시
	private String rgstId;		// 등록 ID
	private String updtDt;		// 수정일시
	private String updtId;		// 수정 ID
	private String delYn;		// 삭제여부
}
