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
 *  2023-12-18  JS	   최초 생성
 */
@Getter
@Setter
@ToString
public class FileVO {

	private String fileId;		// 파일 ID
	private int boardId;		// 게시판 ID
	private long fileSize;		// 파일 크기
	private String fileOgnm;	// 파일 원본 이름
	private String fileNm;		// 파일 이름
	private String filePath;	// 파일 경로
	private String rgstDt;		// 등록일시
	private String rgstId;		// 등록 ID
	private String updtDt;		// 수정일시
	private String updtId;		// 수정 ID
	private String delYn;		// 삭제여부
}
