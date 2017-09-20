
package kr.spring.tiles.board.free.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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
   
    // xml에 설정된 리소스 참조
    // bean의 id가 uploadPath인 태그를 참조
    @Resource(name="uploadPath")
    String uploadPath;
   
   // 01 회원 목록
   // url pattern mapping
   @RequestMapping("/board/free_board_list.do")
   public String free_Board_List(Model model, 
         @RequestParam(value="searchOption", defaultValue="all") String searchOption,
            @RequestParam(value="searchKeyword", defaultValue="") String keyword,
            @RequestParam(value="curPage", defaultValue="1") int curPage) throws Exception{

     
        

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
       
      return "free_board_list";
   }
   
   
   @RequestMapping("/board/free_board_write.do")
   public String free_Board_Write(Model model) throws Exception{
      
      List<Fb_categoryVO> fblist = freeBoardService.listAll();
      
      Map<String, Object> fbmap = new HashMap<String, Object>();
      fbmap.put("fblist", fblist);
      
       model.addAttribute("fbmap", fbmap);
       
      return "free_board_write";
   }
  
   
   //게시글 작성처리
   
   @RequestMapping(value = "/board/free_board_insert.do", method= RequestMethod.POST , consumes ={"multipart/form-data"})
   
   public String insert( /*@ModelAttribute Free_boardVO freeboard, */ MultipartHttpServletRequest multi , HttpSession session) throws Exception{
      
      //저장경로 설정
      String path = "/SpringTiles/fileupload";
      
        String newFileName = ""; // 업로드 되는 파일명
        
        File dir = new File(path);
        
        //경로 없을 경우 생성
        if(!dir.isDirectory()){
            dir.mkdir();
        }

        Iterator<String> files = multi.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
                         
            MultipartFile mFile = multi.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
             
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("id : " + multi.getParameter("id"));
        System.out.println("pw : " + multi.getParameter("pw"));
         


      
      
      
      
      
      // session에 저장된 userId를 writer에 저장
/*      String id = (String) session.getAttribute("userId");
      String writer = (String) session.getAttribute("userWriter");*/      
/*      
      // vo에 writer를 세팅
      freeboard.setId("slr2"); 
      freeboard.setWriter("홍길동");
      
      freeboard.setSubject("홍길동");
      freeboard.setContent("홍길동");
      freeboard.setCate_chk("홍길동");

      logger.info("freeboard 값 체크 [ "
            + "freeboard.getId()"+freeboard.getId()
            + "freeboard.Writer="+freeboard.getWriter()+", subject="+freeboard.getSubject() 
            + "content="+freeboard.getContent()+", cate_chk="+freeboard.getCate_chk() 
            );
      */
      
/*      //파일 s
        logger.info("파일이름 :"+file.getOriginalFilename());
        logger.info("파일크기 : "+file.getSize());
        logger.info("컨텐트 타입 : "+file.getContentType());

        String savedName = file.getOriginalFilename();

        File target = new File(uploadPath, savedName);
        
        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
        // FileCopyUtils.copy(바이트배열, 파일객체)
        FileCopyUtils.copy(file.getBytes(), target);     */   
        
        //파일 e
        /*
      freeBoardService.create(freeboard);
      */
      return "redirect:/board/free_board_list.do";
   }
}