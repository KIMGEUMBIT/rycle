package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

public interface FreeBoardService {
	
	// ȸ�� ��� 
	public List<Free_boardVO> boardList();
	
}
