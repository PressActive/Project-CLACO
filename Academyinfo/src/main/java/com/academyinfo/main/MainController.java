package com.academyinfo.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.academyinfo.board.dto.BoardDto;
import com.academyinfo.board.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
	
	private BoardService boardService;
	
	@GetMapping("/")
	public String index(Model model) {
		Integer pageNum = 1;
		List<BoardDto> boardList = boardService.getBoardlist(pageNum);
		
		model.addAttribute("boardList", boardList);
		
        return "/index";
    }
}
