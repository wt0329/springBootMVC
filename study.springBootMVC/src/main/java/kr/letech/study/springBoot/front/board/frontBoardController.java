/**
 * 
 */
package kr.letech.study.springBoot.front.board;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.letech.study.springBoot.board.vo.BoardVO;
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
 *  2024-01-03  JS			최초 생성
 */
@Controller
public class frontBoardController {
	
	@GetMapping("/front/index")
	public String index(Model model) {
		model.addAttribute("name", "홍길동");
		return "index";
	}
	
	/**
	 * 게시판 목록 조회
	 * @return
	 */
	@GetMapping("/front/board/list")
	public String list() {
		return "board/list";
	}
	
	/**
	 * 게시글 상세보기
	 * @param boardId
	 * @param model
	 * @return
	 */
	@GetMapping("/front/board/details/{boardId}")
	public String boardDetails(@PathVariable String boardId, Model model) {
		model.addAttribute("boardId", boardId);
		return "board/BoardDetails";
	}
	
	/**
	 * 게시물 등록 페이지
	 * @return
	 */
	@GetMapping("/front/board/insert") 
	public String insertBoardForm() {
		return "board/BoardInsert";
	}
	
	/**
	 * 게시물 등록
	 * @param principal
	 * @param board
	 * @return
	 */
	@PostMapping("/front/board/insert")
	public String insertBoard(Principal principal,@ModelAttribute("board") BoardVO board ) {
		String userId = principal.getName();
		return "redirect:/front/board/list";
	}
	
	/**
	 * 게시물 수정 페이지
	 * @return
	 */
	@GetMapping("/front/board/update/{boardId}")
	public String updateBoardForm(@PathVariable String boardId, Model model) {
		model.addAttribute("boardId", boardId);
		return "board/BoardUpdate";
	}
	
	/**
	 * 게시물 등록
	 * @param principal
	 * @param board
	 * @return
	 */
	@PostMapping("/front/board/update")
	public String updateBoard(Principal principal,@ModelAttribute("board") BoardVO board) {
		String userId = principal.getName();
		return "redirect:/front/board/list";
	}
	
	/**
	 * 게시물 삭제
	 * @param principal
	 * @param boardId
	 * @param model
	 * @return
	 */
	@GetMapping("/front/board/delete/{boardId}")
	public String boardDelete (Principal principal, @PathVariable String boardId, Model model) {
		model.addAttribute("boardId", boardId);
		return "redirect:/front/board/list";
	}
	
	/**
	 * 파일 다운로드
	 * @param principal
	 * @param fileId
	 * @param model
	 * @return
	 */
	@GetMapping("/front/file/download/{fileId}")
	public String fileDownload(Principal principal, @PathVariable String fileId, Model model) {
		model.addAttribute("fileId", fileId);
		return "board/BoardDetails";
	}
	
	/**
	 * 로그인 페이지
	 * @return
	 */
	@GetMapping("/user/login")
	public String userLoginPage() {
		return "user/login";
	}
	
	/**
	 * 사용자 로그인
	 * @param userId
	 * @param userPw
	 * @param user
	 * @return
	 */
	@PostMapping("/user/login")
	public String userLogin(@RequestParam String userId, @RequestParam String userPw, @ModelAttribute("user") UserVO user) {
		return "redirect:/front/board/list";
	}
}
