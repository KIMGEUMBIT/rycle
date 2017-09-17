
package kr.spring.tiles.board.free.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.board.util.BoardPager;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

import kr.spring.tiles.board.free.service.FreeBoardService;
import kr.spring.tiles.board.free.service.FbCategoryService;

@Controller // ������ Ŭ������ controller bean�� ��Ͻ�Ŵ
public class Free_BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
	
	// MemberService ��ü�� ���������� �����Ͽ� ���Խ�Ŵ

	@Inject
	FreeBoardService freeBoardService;
	
	@Inject
	FbCategoryService fbcategoryservice;
	
	// 01 ȸ�� ���
	// url pattern mapping
	@RequestMapping("/board/free_board_list.do")
	public String free_Board_List(Model model, 
			@RequestParam(value="searchOption", defaultValue="all") String searchOption,
            @RequestParam(value="searchKeyword", defaultValue="") String keyword,
            @RequestParam(value="curPage", defaultValue="1") int curPage) throws Exception{
		
		//�Խ��� ���s
/*		List<Free_boardVO> list = boardService.boardList();
  		model.addAttribute("list", list);*/
  		
	    // ���ڵ��� ���� ���
	    int count = freeBoardService.countArticle(searchOption, keyword);
	    
	    // ������ ������ ���� ó��
	    BoardPager boardPager = new BoardPager(count, curPage);
	    int start = boardPager.getPageBegin();
	    int end = boardPager.getPageEnd();
	    int PAGE_SCALE = boardPager.getPAGE_SCALE();
	    
	    List<Free_boardVO> list = freeBoardService.listAll(start, end, searchOption, keyword);
  		
	    // �����͸� �ʿ� ����
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list); // list
	    map.put("count", count); // ���ڵ��� ����
	    map.put("searchOption", searchOption); // �˻��ɼ�
	    map.put("keyword", keyword); // �˻�Ű����
	    map.put("boardPager", boardPager);
	    map.put("PAGE_SCALE", PAGE_SCALE); //�������� �Խù� �� 	    
	    
	    model.addAttribute("map", map);
	    
		return "free_board_list";
	}
	
	
	@RequestMapping("/board/free_board_write.do")
	public String memberWrite(Model model) throws Exception{
		
		List<Fb_categoryVO> fblist = fbcategoryservice.listAll();
		
		Map<String, Object> fbmap = new HashMap<String, Object>();
		fbmap.put("fblist", fblist);
		
	    model.addAttribute("fbmap", fbmap);
	    
		return "free_board_write";
	}
	
}
