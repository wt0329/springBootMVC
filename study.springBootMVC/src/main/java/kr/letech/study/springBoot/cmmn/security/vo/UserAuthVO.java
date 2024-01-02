/**
 * 
 */
package kr.letech.study.springBoot.cmmn.security.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@Data
public class UserAuthVO {
	private String userId;		// 사용자 ID
	private String authCd;		// 권한 코드
	private String rgstDt;		// 등록일시
	private String rgstId;		// 등록 ID
	private String updtDt;		// 수정일시
	private String updtId;		// 수정 ID
	private int delYn;			// 삭제여부
}
