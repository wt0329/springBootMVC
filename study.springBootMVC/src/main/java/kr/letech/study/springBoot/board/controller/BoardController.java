/**
 * 
 */
package kr.letech.study.springBoot.board.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import kr.letech.study.springBoot.board.service.BoardService;
import kr.letech.study.springBoot.board.vo.BoardVO;
import kr.letech.study.springBoot.board.vo.FileVO;
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
 *  2023-12-18  JS			최초 생성
 */
@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	/**
	 * 게시판 전체 조회
	 * @return
	 */
	@GetMapping("/api/v1/board")
	public ResponseEntity<PageInfo<BoardVO>> selectBoardList(@RequestParam(defaultValue = "1") int pageNum
			, @RequestParam(value = "boardCd", required = false) String boardCd
			, @RequestParam(value = "keyword", required = false) String keyword) {
		
		List<BoardVO> selectBoardList = null;
		 if (keyword != null && !keyword.isEmpty()) {
			 selectBoardList = boardService.searchBoard(boardCd, keyword);
		    } else {
		        PageHelper.startPage(pageNum, 5);
		        selectBoardList = boardService.selectBoardList();
		    }
		PageInfo<BoardVO> pageInfo = PageInfo.of(selectBoardList); 
		return ResponseEntity.ok(pageInfo);
	}
	
	/**
	 * 게시판 상세 조회
	 * @param boardId
	 * @return
	 */
	@GetMapping("/api/v1/board/{boardId}")
	public ResponseEntity<List<BoardVO>> selectBoard(@PathVariable String boardId) {
		List<BoardVO> selectBoard = this.boardService.selectBoard(boardId);
		return ResponseEntity.ok(selectBoard);
	}
	
	/**
	 * 게시물 등록
	 * @param board
	 * @param principal
	 * @return
	 */
	@PostMapping("/api/v1/board")
	public ResponseEntity<String> insertBoard(BoardVO board, Principal principal,
	    @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles
	    ) {
	    String userId = principal.getName();
	    String result = this.boardService.insertBoard(userId, board, multipartFiles);
	    return ResponseEntity.ok(result);
	}
	
	/**
	 * 게시물 수정
	 */
	@PutMapping("/api/v1/board/{boardId}")
	public ResponseEntity<String> updateBoard(BoardVO board, Principal principal) {
		String userId = principal.getName();
		String result = this.boardService.updateBoard(userId, board);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 게시판 삭제
	 * @param boardId
	 * @return
	 */
	@DeleteMapping("/api/v1/board/{boardId}")
	public ResponseEntity<String> deleteBoard(@PathVariable String boardId) {
		String result = this.boardService.deleteBoard(boardId);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 파일 다운로드
	 * @param fileId
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/api/v1/board/{fileId}/download")
	public ResponseEntity<FileVO> downloadFile(@PathVariable String fileId, HttpServletResponse resp) throws IOException {
		FileVO selectfile = this.boardService.downloadFile(fileId, resp);
		return ResponseEntity.ok(selectfile);
	}
}
