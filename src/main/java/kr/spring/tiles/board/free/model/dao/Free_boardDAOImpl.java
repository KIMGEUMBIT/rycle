package kr.spring.tiles.board.free.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.free.model.dto.Free_boardVO;

//���� Ŭ������ DAO bean���� ��Ͻ�Ŵ
@Repository
public class Free_boardDAOImpl implements Free_boardDAO {
	
	// SqlSession ��ü�� ���������� �����Ͽ� ���Խ����ش�.
	// �������� ����(Dependency Injection, DI)
	// ������ ����
	// IoC(Inversion of Control, ������ ����)
	@Inject
	// Inject�ֳ����̼��� ������ sqlSession�� null����������
	// Inject�ֳ����̼��� ������ �ܺο��� ��ü�� ���Խ����ְ� �ȴ�. 
	// try catch��, finally��, ��ü�� close�� �ʿ䰡 ��������.
	SqlSession sqlSession;
	
	// 01. ��ü ȸ�� ��� ��ȸ
	@Override
	public List<Free_boardVO> boardList() {
		return sqlSession.selectList("freeboard.boardList");
	}

}
