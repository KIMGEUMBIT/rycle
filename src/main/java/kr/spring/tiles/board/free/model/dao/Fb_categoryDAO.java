package kr.spring.tiles.board.free.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.service.FbCategoryService;
  
public interface Fb_categoryDAO {
	
	// 01. ī�װ� ��������
	public List<Fb_categoryVO> listAll() throws Exception;

	
}
