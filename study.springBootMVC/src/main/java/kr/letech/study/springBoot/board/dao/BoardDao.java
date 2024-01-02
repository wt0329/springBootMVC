/**
 * 
 */
package kr.letech.study.springBoot.board.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface BoardDao {

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
	public int insertBoard(BoardVO board);
	
	/**
	 * 게시물 수정
	 * @param board
	 * @return
	 */
	public int updateBoard(BoardVO board);
	
	/**
	 * 게시물 삭제
	 * @param boardId
	 * @return
	 */
	public int deleteBoard(String boardId);
	
	/**
	 * 게시물 검색
	 * @param boardCd
	 * @param keyword
	 * @return
	 */
	public List<BoardVO> searchBoard(@Param("boardCd")String boardCd, @Param("keyword")String keyword);
	
	/**
	 * 파일 업로드
	 */
	public int fileUpload(FileVO file);
	
	/**
	 * 최근 ID
	 * @return
	 */
	public int boardMax();
	
	/**
	 * 파일 다운로드
	 * @param fileId
	 * @param resp
	 * @return
	 */
	public FileVO downloadFile(String fileId, HttpServletResponse resp);
}
