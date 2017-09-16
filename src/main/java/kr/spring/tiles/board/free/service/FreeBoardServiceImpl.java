package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import kr.spring.tiles.board.free.model.dao.Free_boardDAOImpl;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

// ���� Ŭ������ ���������� �����ϴ� service bean���� ���
@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	// MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	Free_boardDAOImpl freeboardDao;
	
	// 01. ��ü ȸ�� ��� ��ȸ
	@Override
	public List<Free_boardVO> boardList() {
		return freeboardDao.boardList();
	}

}
