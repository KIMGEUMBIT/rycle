package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.tiles.board.free.model.dao.Fb_categoryDAOImpl;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

// ���� Ŭ������ ���������� �����ϴ� service bean���� ���
@Service
public class FbCategoryServiceImpl implements FbCategoryService {
	
	// MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	Fb_categoryDAOImpl fbcategoryDao;
	
	// 01. ī�װ� ��������
	public List<Fb_categoryVO> listAll() throws Exception{
	    return fbcategoryDao.listAll();
	}

}
