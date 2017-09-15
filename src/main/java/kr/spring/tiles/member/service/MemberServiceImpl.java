package kr.spring.tiles.member.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import kr.spring.tiles.member.model.dao.MemberDAOImpl;
import kr.spring.tiles.member.model.dto.MemberVO;

// ���� Ŭ������ ���������� �����ϴ� service bean���� ���
@Service
public class MemberServiceImpl implements MemberService {
	
	// MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	MemberDAOImpl memberDao;
	
	// 01. ��ü ȸ�� ��� ��ȸ
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	
	// 02. ȸ�� ���
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	// 04. ȸ�� ���� ���� ó��
	@Override
	public void deleteMember(String userId) {
		memberDao.deleteMember(userId);
	}
	// 05. ȸ�� ���� ���� ó��
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}
	// 06. ȸ�� ���� ���� �� ������ ���� ��й�ȣ üũ
	@Override
	public boolean checkPw(String userId, String userPw) {
		return memberDao.checkPw(userId, userPw);
	}

	   // 01_01. ȸ�� �α���üũ
    @Override
    public boolean loginCheck(MemberVO vo, HttpSession session) {
        boolean result = memberDao.loginCheck(vo);
        if (result) { // true�� ��� ���ǿ� ���
            MemberVO vo2 = viewMember(vo);
            // ���� ���� ���
            session.setAttribute("userId", vo2.getUserId());
            session.setAttribute("userName", vo2.getUserName());
        } 
        return result;
    }

    // 01_02. ȸ�� �α��� ����
    @Override
    public MemberVO viewMember(MemberVO vo) {
        return memberDao.viewMember(vo);
    }

    // 02. ȸ�� �α׾ƿ�
    @Override
    public void logout(HttpSession session) {
        // ���� ���� ���� ����
        // session.removeAttribute("userId");
        // ���� ������ �ʱ�ȭ ��Ŵ
        session.invalidate();
    }

}
