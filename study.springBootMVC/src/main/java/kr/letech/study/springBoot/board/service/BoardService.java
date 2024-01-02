/**
 * 
 */
package kr.letech.study.springBoot.board.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import kr.letech.study.springBoot.board.vo.BoardVO;
import kr.letech.study.springBoot.board.vo.FileVO;

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
public interface BoardService {

	/**
	 * 게시판 전체 조회
	 * @return
	 */
	public List<BoardVO> selectBoardList();
	
	/**
	 * 게시판 상세 조회
	 * @param boardId
	 * @return
	 */
	public List<BoardVO> selectBoard(String boardId);
	
	/**
	 * 게시물 등록
	 * @param board
	 * @return
	 */
	public String insertBoard(String userId, BoardVO board, List<MultipartFile> multipartFiles); 
	
	/**
	 * 게시물 수정
	 * @param boardId
	 * @param board 
	 * @return
	 */
	public String updateBoard(String userId, BoardVO board);
	
	/**
	 * 게시물 삭제
	 * @param boardId
	 * @return
	 */
	public String deleteBoard(String boardId);
	
	/**
	 * 게시물 검색
	 * @param boardCd
	 * @param keyword
	 * @return
	 */
	public List<BoardVO> searchBoard(String boardCd, String keyword);

	/**
	 * 파일 업로드
	 * @param file
	 */
	public void fileUpload(FileVO file);
	
	/**
	 * 파일 ID 가져오기
	 * @return
	 */
	public int boardMax();

	/**
	 * @param fileId
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	FileVO downloadFile(String fileId, HttpServletResponse resp) throws IOException;
	
}
