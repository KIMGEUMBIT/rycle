package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

public interface FbCategoryService {
 
	// 01. ī�װ� ��������
	public List<Fb_categoryVO> listAll() throws Exception;

}
