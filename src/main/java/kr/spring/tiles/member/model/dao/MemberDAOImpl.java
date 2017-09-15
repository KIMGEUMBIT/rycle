package kr.spring.tiles.member.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.member.model.dto.MemberVO;

//���� Ŭ������ DAO bean���� ��Ͻ�Ŵ
@Repository
public class MemberDAOImpl implements MemberDAO {
	
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
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}
	// 02. ȸ�� ���
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
	}

	// 04. ȸ�� ���� ���� ó��
	@Override
	public void deleteMember(String userId) {
		sqlSession.delete("member.deleteMember",userId);
	}
	// 05. ȸ�� ���� ���� ó��
	@Override
	public void updateMember(MemberVO vo) {
		sqlSession.update("member.updateMember", vo);

	}
	// 06. ȸ�� ���� ���� �� ������ ���� ��й�ȣ üũ
	@Override
	public boolean checkPw(String userId, String userPw) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		int count = sqlSession.selectOne("member.checkPw", map);
		if(count == 1) result= true;
		return result;
	}
	// 01_01. ȸ�� �α���üũ
    @Override
    public boolean loginCheck(MemberVO vo) {
        String name = sqlSession.selectOne("member.loginCheck", vo);
        return (name == null) ? false : true;
    }
    // 01_02. ȸ�� �α��� ����
    @Override
    public MemberVO viewMember(MemberVO vo) {
        return sqlSession.selectOne("member.viewMember", vo);
    }
 // 02. ȸ�� �α׾ƿ�
    @Override
    public void logout(HttpSession sessin) {
    }

}
