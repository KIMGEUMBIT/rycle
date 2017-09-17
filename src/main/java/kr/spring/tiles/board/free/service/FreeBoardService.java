package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

public interface FreeBoardService {
	
	// 01. �Խñ� �ۼ�
	public void create(Free_boardVO vo) throws Exception;
	/*	// 02. �Խñ� �󼼺���
	public Free_boardVO read(int bno) throws Exception;
	// 03. �Խñ� ����
	public void update(Free_boardVO vo) throws Exception;
	// 04. �Խñ� ����
	public void delete(int bno) throws Exception;*/
	// 05. �Խñ� ��ü ���
	public List<Free_boardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
/*	// 06. �Խñ� ��ȸ
	public void increaseViewcnt(int bno, HttpSession session) throws Exception;
	// 07. �Խñ� ���ڵ� ����
*/	public int countArticle(String searchOption, String keyword) throws Exception;
/*	// 08. �Խñ� ÷������ ���
	public List<String> getAttach(int bno);
	// 09. �Խñ� ÷������ ����
	public void deleteFile(String fullname);*/
	
}
