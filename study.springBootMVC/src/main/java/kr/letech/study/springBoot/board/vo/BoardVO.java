/**
 * 
 */
package kr.letech.study.springBoot.board.vo;

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
 *  2023-12-18  JS			최초 생성
 */
@Getter
@Setter
@ToString
public class BoardVO {
	private int boardId;		// 게시판 ID
	private String boardCd; 	// 게시판 구분	
	private String boardTitle; 	// 게시판 제목  
	private String boardCnt;    // 게시판 내용
	private String userNm;		// 사용자 이름
	private String userId;		// 사용자 ID
	private String rgstDt;		// 등록일시
	private String rgstId;		// 등록 ID
	private String updtDt;		// 수정일시
	private String updtId;		// 수정 ID
	private int delYn;			// 삭제여부
	
	
//	private UserVO userVO;
	private FileVO file;
	private String fileNm;
	private String fileOgnm;
	private String fileId;
	//private List<FileVO> fileList;
   private String keyword;
	
}
