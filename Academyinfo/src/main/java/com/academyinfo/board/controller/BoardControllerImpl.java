package com.academyinfo.board.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.academyinfo.board.dto.BoardDto;
import com.academyinfo.board.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardControllerImpl implements BoardController {
private BoardService boardService;
	
	@GetMapping("/test")
	public String test() {
		return "Hello, World!";
	}
	
	// 게시글 목록 출력
	// Model 객체를 통해 View에 데이터 전달
	@GetMapping("/freeboard")
	public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
		// page 이름으로 넘어오면 파라미터를 받고, 없으면 기본값 1 설정
			
		List<BoardDto> boardList = boardService.getBoardlist(pageNum);
		Integer[] pageList = boardService.getPageList(pageNum);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageList", pageList);
		model.addAttribute("currentPage", pageNum); // 현재 페이지에 특수효과 주기 위한 attribute
		
		addPrevNext(pageList, pageNum, model);
		
		// return "freeboard/list.html";
		return "/freeboard/list";
	}
	
	// 게시글 등록화면으로 이동
	@GetMapping("/freeboard/post")
	public String write(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인한 사용자의 정보를 가져온다
		String id = ((UserDetails)principal).getUsername(); // 사용자의 Id
		
		model.addAttribute("id", id);
		
		return "/freeboard/write";
	}
	
	// 게시글 등록화면에서 데이터 넘어오면 DB 저장
	@PostMapping("/freeboard/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		
		return "redirect:/freeboard";
	}
	
	// 게시글 상세보기
	@GetMapping("/freeboard/post/{no}")
    public String detail(@PathVariable("no") int no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        boardService.updateCount(no); // 조회수 증가
        
        model.addAttribute("boardDto", boardDTO);
        // return "freeboard/detail.html";
        return "/freeboard/detail";
    }

	// 게시글 수정
    @GetMapping("/freeboard/post/edit/{no}")
    public String edit(@PathVariable("no") int no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        // return "freeboard/update.html";
        return "/freeboard/update";
    }

    // 게시글 수정 값 DB 저장
    @PutMapping("/freeboard/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/freeboard";
    }
    
    // 게시글 삭제 실행
    @DeleteMapping("/freeboard/post/{no}")
    public String delete(@PathVariable("no") int no) {
        boardService.deletePost(no);

        return "redirect:/freeboard";
    }
    
    @GetMapping("/freeboard/search")
    public String search(@RequestParam(value="keyword", defaultValue="") String keyword, @RequestParam(value="page", defaultValue = "1") Integer pageNum, @RequestParam(value="filter", defaultValue="title") String filter, Model model) {
    	// List<BoardDto> boardDtoList = boardService.searchPosts(keyword, pageNum);
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword, filter, pageNum);
        Integer[] pageList = boardService.getPageList(pageNum, keyword, filter);
        
        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", pageNum);
        addPrevNext(pageList, pageNum, keyword, filter, model);
        
        // return "freeboard/list.html";
        return "/freeboard/list";
    }
    
    
    public void addPrevNext(Integer [] pageList, Integer pageNum, Model model) {
    	addPrevNext(pageList, pageNum, "", "", model);
    }
    
    // prev, next 버튼 구현
    public void addPrevNext(Integer [] pageList, Integer pageNum, String keyword, String filter, Model model) {
    	
		int prev = boardService.getPrev(pageNum, keyword, filter);  // << 버튼값
		int next = boardService.getNext(pageNum, keyword, filter);  // >> 버튼값
    	
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
    }
}
