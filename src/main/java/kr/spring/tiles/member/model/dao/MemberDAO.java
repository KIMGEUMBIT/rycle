package kr.spring.tiles.member.model.dao;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.member.model.dto.MemberVO;

public interface MemberDAO {
	// ȸ�� ��� 
	public List<MemberVO> memberList();
	// ȸ�� �Է�
	public void insertMember(MemberVO vo);
	// ȸ������
	public void deleteMember(String userId);
	// ȸ������ ����
	public void updateMember(MemberVO vo);
	// ��й�ȣ üũ
	public boolean checkPw(String userId, String userPw);
	
	  // 01_01. ȸ�� �α��� üũ
    public boolean loginCheck(MemberVO vo);
    // 01_02. ȸ�� �α��� ����
    public MemberVO viewMember(MemberVO vo);
    // 02. ȸ�� �α׾ƿ�
    public void logout(HttpSession session);
	
}
