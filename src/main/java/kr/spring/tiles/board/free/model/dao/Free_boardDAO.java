package kr.spring.tiles.board.free.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.service.FreeBoardService;
  
public interface Free_boardDAO {
	
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
/*	// 06. �Խñ� ��ȸ ����
	public void increaseViewcnt(int bno) throws Exception;*/
	// 07. �Խñ� ���ڵ� ���� �޼��� �߰�
	public int countArticle(String searchOption, String keyword) throws Exception;
/*	// 08. �Խù� ÷������ �߰�
	public void addAttach(String fullName);
	// 09. �Խù� ÷������ ���
	public List<String> getAttach(int bno);
	// 10. �Խñ� ÷������ ����ó��
	public void deleteFile(String fullname);
	// 11. �Խñ� ÷������ ������Ʈó��
	public void updateAttach(String fullName, int bno);*/

	
}
