/**
 * 
 */
package kr.letech.study.springBoot.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.letech.study.springBoot.board.dao.BoardDao;
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
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardDao boardDAO; 
	
	@Value("${upload.dir}")
	private String uploadDir;
	
	/**
	 * 게시판 전체 리스트 조회
	 */
	@Override
	public List<BoardVO> selectBoardList() {
		return boardDAO.selectBoardList();
	}

	/**
	 * 게시판 상세 조회
	 */
	@Override
	public List<BoardVO> selectBoard(String boardId) {
		return boardDAO.selectBoard(boardId);
	}

	/**
	 * 게시물 등록
	 */
	@Override
	public String insertBoard(String userId, BoardVO board, List<MultipartFile> multipartFiles) {

		board.setUserId(userId);
		board.setUpdtId(userId);
		board.setRgstId(userId);
		board.setUserId(userId);

		int insertRows = boardDAO.insertBoard(board);

		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				// 파일 사이즈가 0보다 큰지 확인하여 파일이 선택되었는지 체크
				if (file.getSize() > 0) {
					FileVO fileVO = new FileVO();
					UUID fileId = UUID.randomUUID();
					UUID fileNm = UUID.randomUUID();
					String fileIdString = fileId.toString();
					String fileNmString = fileNm.toString();

					fileVO.setFileId(fileIdString);
					fileVO.setBoardId(boardDAO.boardMax()); // boardId 가지고 와야함
					fileVO.setFileOgnm(file.getOriginalFilename());
					fileVO.setFileNm(fileNmString);
					fileVO.setFileSize(file.getSize());
					fileVO.setFilePath(uploadDir);

					// 파일업로드
					boardDAO.fileUpload(fileVO);
					InputStream is = null;

					String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
					// 로컬에 파일 저장
					String fileSavePath = uploadDir + File.separator + fileNmString + "." + fileExtension; // 파일이 저장될 전체
																											// 경로
					try {
						is = file.getInputStream(); // byte 배열을 InputStream으로 변환

						OutputStream os = new FileOutputStream(fileSavePath); // 저장할 파일의 OutputStream

						IOUtils.copyLarge(is, os);

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (is != null) {
							try {
								is.close(); // InputStream 닫기
							} catch (IOException e) {
								// InputStream을 닫는 과정에서 예외가 발생할 경우의 예외 처리
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		if (insertRows > 0) {
			return "등록 성공";
		} else {
			return "등록 삭제";
		}
	}

	/**
	 * 게시물 등록
	 */
//	@Override
//	public String insertBoard(String userId, BoardVO board) {
//		
//		board.setUserId(userId);
//		board.setUpdtId(userId);
//		board.setRgstId(userId);
//		board.setUserId(userId);
//		
//		int insertRows = boardDAO.insertBoard(board);
//		
//		if (insertRows > 0) {
//			return "등록 성공";
//		} else {
//			return "등록 삭제";
//		}
//	}
	
	/**
	 * 게시물 수정
	 */
	@Override
	public String updateBoard(String userId, BoardVO board) {
		int updateRows = boardDAO.updateBoard(board);
		
		if (updateRows > 0) {
			return "수정 성공";
		} else {
			return "수정 실패";
		}
	}

	/**
	 * 게시물 삭제
	 * @return 
	 */
	@Override
	public String deleteBoard(String boardId) {
		int deletedRows = boardDAO.deleteBoard(boardId);

		if (deletedRows > 0) {
			return "삭제 성공";
		} else {
			return "삭제 실패";
		}
	}

	/**
	 * 게시물 검색
	 */
	@Override
	public List<BoardVO> searchBoard(String boardCd, String keyword) {
		return boardDAO.searchBoard(boardCd, keyword);
	}

	/**
	 * 파일 업로드
	 */
	@Override
	public void fileUpload(FileVO file) {
		
	}

	/**
	 * 최근 ID 가져오기
	 */
	@Override
	public int boardMax() {
		return boardDAO.boardMax();
	}

	/**
	 * 파일 다운로드
	 */
	@Override
	public FileVO downloadFile(String fileId, HttpServletResponse resp) throws IOException {

		FileVO file = boardDAO.downloadFile(fileId, resp);
		String originalFileName = file.getFileOgnm(); // 원본 파일 이름
		//System.out.printf("----->", originalFileName);
		String fileExtension = FilenameUtils.getExtension(originalFileName); // 확장자 가져오기
		String fileNameWithExtension = file.getFileNm() + "." + fileExtension; // 확장자를 추가한 파일 이름

		File f = new File(uploadDir, fileNameWithExtension);

		// 파일명 인코딩
		String encodedFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");

		// file 다운로드 설정
		//resp.setContentType("application/download");
		resp.setContentLength((int) f.length());
		resp.setHeader("Content-Disposition", "attatchment;filename=\"" + encodedFileName + "\"");

		// 다운로드 시 저장되는 이름은 Response Header의 "Content-Disposition"에 명시
		OutputStream os = resp.getOutputStream();

		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis, os);
		return file;

	}

}
