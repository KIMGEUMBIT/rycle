
package kr.spring.tiles.board.free.controller;

import java.io.File;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartHttpServletRequest; //파일 업로드
   
import kr.spring.tiles.board.util.BoardPager;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

import kr.spring.tiles.board.free.service.FreeBoardService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class Free_BoardController {
   
   private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
   
   // MemberService 객체를 스프링에서 생성하여 주입시킴
   @Inject
   FreeBoardService freeBoardService;
   
   // 01 회원 목록
   // url pattern mapping
   @RequestMapping("/board/free_board_list.do")
   public String free_Board_List(Model model, 
         @RequestParam(value="searchOption", defaultValue="all") String searchOption,
            @RequestParam(value="searchKeyword", defaultValue="") String keyword,
            @RequestParam(value="curPage", defaultValue="1") int curPage) throws Exception{

       logger.info("검색값"+searchOption);
       logger.info("키워드값"+keyword);
        

      //게시판 목록s
/*      List<Free_boardVO> list = boardService.boardList();
        model.addAttribute("list", list);*/
        
       // 레코드의 갯수 계산
       int count = freeBoardService.countArticle(searchOption, keyword);
   
       // 페이지 나누기 관련 처리
       BoardPager boardPager = new BoardPager(count, curPage);
       int start = boardPager.getPageBegin();
       int end = boardPager.getPageEnd();
       int PAGE_SCALE = boardPager.getPAGE_SCALE();
       logger.info("키워드값2"+keyword);
       List<Free_boardVO> list = freeBoardService.listAll(start, end, searchOption, keyword);
        
       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("list", list); // list
       map.put("count", count); // 레코드의 갯수
       map.put("searchOption", searchOption); // 검색옵션
       map.put("keyword", keyword); // 검색키워드
       map.put("boardPager", boardPager);
       map.put("PAGE_SCALE", PAGE_SCALE); //페이지당 게시물 수        
       map.put("board_name", "free_board"); //게시물명
       model.addAttribute("map", map);
       
      return "board/free/free_board_list";
   }
   
   
   @RequestMapping("/board/free_board_write.do")
   public String free_Board_Write(Model model) throws Exception{
      
      List<Fb_categoryVO> fblist = freeBoardService.listAll();
      
      Map<String, Object> fbmap = new HashMap<String, Object>();
       fbmap.put("fblist", fblist);
      
       model.addAttribute("fbmap", fbmap);
       
      return "board/free/free_board_write";
   }
  
   
   //게시글 작성처리
   @RequestMapping(value = "/board/free_board_insert.do", method= RequestMethod.POST )
   public String free_Board_insert( @ModelAttribute Free_boardVO freeboard , HttpSession session) throws Exception{


      // session에 저장된 userId를 writer에 저장
/*      String id = (String) session.getAttribute("userId");
      String writer = (String) session.getAttribute("userWriter");*/      
      
      // vo에 writer를 세팅
      freeboard.setId("slr2"); 
      freeboard.setWriter("홍길동");


      logger.info("freeboard 값 체크 [ "
            + "freeboard.getId()"+freeboard.getId()
            + "freeboard.Writer="+freeboard.getWriter()+", subject="+freeboard.getSubject() 
            + "content="+freeboard.getContent()+", cate_chk="+freeboard.getCate_chk() 
            );

        //파일 e
        
      freeBoardService.create(freeboard);
      
      return "redirect:/board/free_board_list.do";
   }
   
   // 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
   // @RequestParam : get/post방식으로 전달된 변수 1개
   // HttpSession 세션객체
   @RequestMapping("/board/free_board_view.do")
   public String free_Board_View(@RequestParam int bno, @RequestParam String cate_chk, HttpSession session, Model model) throws Exception{
       // 조회수 증가 처리
	   freeBoardService.increaseViewcnt(bno, session);
	   
       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("dto",  freeBoardService.read(bno)); // view     
       
       logger.info("cate_chk"+cate_chk);
       map.put("cdto",  freeBoardService.cateName(cate_chk)); // view       
       
       model.addAttribute("map", map);
       
       return "board/free/free_board_view";
   }
   
   
   // 04. 게시글 수정
   // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
   @RequestMapping(value="/board/free_board_update.do", method=RequestMethod.GET)
   public String free_Board_Update(int bno, Model model) throws Exception{
	   
	   List<Fb_categoryVO> fblist = freeBoardService.listAll();

       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("dto",  freeBoardService.read(bno)); // view    
       map.put("fblist",  fblist); // 카테고리 목록
       
       model.addAttribute("map", map);
       
       return "board/free/free_board_update";
   }
   
   
   // 04. 게시글 수정
   // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
   @RequestMapping(value="/board/free_board_update.do", method=RequestMethod.POST)
   
   public String free_Board_Update(@RequestParam int bno, @ModelAttribute Free_boardVO freeboard, HttpSession session, Model model) throws Exception{
      
	   
	   logger.info("free_Board_Update실행[ bno="+bno+"]");
	      // session에 저장된 userId를 writer에 저장
	   /*      String id = (String) session.getAttribute("userId");
	         String writer = (String) session.getAttribute("userWriter");*/      
	         
	         // vo에 writer를 세팅
	     freeboard.setId("slr2_m"); 
	     freeboard.setWriter("홍길동_m");
	     
	     freeboard.setNo(bno); 
	      logger.info("freeboard 값 체크 [ "
	              + "freeboard.getId()"+freeboard.getId()
	              + "freeboard.Writer="+freeboard.getWriter()+", subject="+freeboard.getSubject() 
	              + "content="+freeboard.getContent()+", cate_chk="+freeboard.getCate_chk() 
	              );

	          //파일 e
	          
	        freeBoardService.update(freeboard);

	        free_Board_View(bno, freeboard.getCate_chk(), session, model);
	        
	        return "board/free/free_board_view";
   }
   
   /*
   // 05. 게시글 삭제
   @RequestMapping("delete.do")
   public String free_Board_Delete(@RequestParam int bno) throws Exception{
       boardService.delete(bno);
       return "redirect:list.do";
   }*/
   
   
}