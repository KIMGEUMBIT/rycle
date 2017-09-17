package kr.spring.tiles.board.free.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

//���� Ŭ������ DAO bean���� ��Ͻ�Ŵ
@Repository
public class Fb_categoryDAOImpl implements Fb_categoryDAO {
	
	// SqlSession ��ü�� ���������� �����Ͽ� ���Խ����ش�.
	// �������� ����(Dependency Injection, DI)
	// ������ ����
	// IoC(Inversion of Control, ������ ����)
	@Inject
	// Inject�ֳ����̼��� ������ sqlSession�� null����������
	// Inject�ֳ����̼��� ������ �ܺο��� ��ü�� ���Խ����ְ� �ȴ�. 
	// try catch��, finally��, ��ü�� close�� �ʿ䰡 ��������.
	SqlSession sqlSession;

	// 01. ī�װ� ��������
	@Override
	public List<Fb_categoryVO> listAll() throws Exception {
		return sqlSession.selectList("fb_category.listAll");
	}

}
