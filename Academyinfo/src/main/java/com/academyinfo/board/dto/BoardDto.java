package com.academyinfo.board.dto;

import java.time.LocalDateTime;

import com.academyinfo.board.domain.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	private int bindex;
	private String writer;
	private String title;
	private String contents;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private int count;
	
	public BoardEntity toEntity() {
		BoardEntity boardEntity = BoardEntity.builder().bindex(bindex).writer(writer).title(title).contents(contents).count(count).build();
		
		return boardEntity;
	}
	
	@Builder
	public BoardDto(int bindex, String title, String contents, String writer, int count, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		this.bindex = bindex;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.count = count;
	}
}
