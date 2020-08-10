//package com.coderby.myapp.board.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.coderby.myapp.board.dao.IBoardService;
//import com.coderby.myapp.board.model.BoardVO;
//
//@Controller
//@RequestMapping("/board/*")
//public class BoardController {
//	
//	@Autowired
//	IBoardService boardService;
//	
//	@GetMapping("list")
//	public ModelAndView list() throws Exception{
//		list<BoardVO> list = boardService.listAll();
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("board/list");
//		mav.addObject("list", list);
//		return mav;
//    }
//    
//    // 02_01. 게시글 작성화면
//    // @RequestMapping("board/write.do")
//    // value="", method="전송방식"
//    @GetMapping("write")
//    public String write(){
//        return "board/write"; // write.jsp로 이동
//    }
//    
//    // 02_02. 게시글 작성처리
//    @GetMapping("insert")
//    public String insert(@ModelAttribute BoardVO vo) throws Exception {
//        boardService.create(vo);
//        return "redirect:list";
//    }
//    
//    // 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
//    // @RequestParam : get/post방식으로 전달된 변수 1개
//    // HttpSession 세션객체
//    @GetMapping("view")
//    public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception {
//        // 조회수 증가 처리
//        boardService.increaseViewcnt(bno, session);
//        // 모델(데이터)+뷰(화면)를 함께 전달하는 객체
//        ModelAndView mav = new ModelAndView();
//        // 뷰의 이름
//        mav.setViewName("board/view");
//        // 뷰에 전달할 데이터
//        mav.addObject("dto", boardService.read(bno));
//        return mav;
//    }
//    
//    // 04. 게시글 수정
//    // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
//    @PostMapping("update")
//    public String update(@ModelAttribute BoardVO vo) throws Exception {
//        boardService.update(vo);
//        return "redirect:list";
//    }
//    
//    // 05. 게시글 삭제
//    @GetMapping("delete")
//    public String delete(@RequestParam int bno) throws Exception {
//        boardService.delete(bno);
//        return "redirect:list";
//    }
//    
//}

