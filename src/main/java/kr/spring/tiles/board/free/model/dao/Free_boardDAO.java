package kr.spring.tiles.board.free.model.dao;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.service.FreeBoardService;
  
public interface Free_boardDAO {
	
	// �Խ��� ��� 
	public List<Free_boardVO> boardList();
	
}
