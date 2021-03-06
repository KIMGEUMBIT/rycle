package kr.spring.tiles.board.free.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.free.model.dto.Free_boardVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Free_boardDAOImpl implements Free_boardDAO {
	
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)
	@Inject
	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.
	SqlSession sqlSession;
	
	// 01. 전체 회원 목록 조회
	@Override
	public List<Free_boardVO> boardList() {
		return sqlSession.selectList("freeboard.boardList");
	}

}
