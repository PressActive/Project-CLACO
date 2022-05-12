package com.academyinfo.board.service;

import java.util.List;

import com.academyinfo.board.domain.entity.BoardEntity;
import com.academyinfo.board.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> getBoardlist(Integer pageNum);
	public BoardDto getPost(int id);
	public int savePost(BoardDto boardDto);
    public void deletePost(int index);
    // public List<BoardDto> searchPosts(String keyword, Integer pageNum);
    public List<BoardDto> searchPosts(String keyword, String filter, Integer pageNum);
    public int getBoardCount(String keyword, String filter);
    public Integer[] getPageList(Integer curPageNum);
	public Integer[] getPageList(Integer curPageNum, String keyword, String filter);
	
	public int getPrev(Integer pageNum, String keyword, String filter);
	public int getNext(Integer pageNum, String keyword, String filter);
	public int updateCount(int no);
}
