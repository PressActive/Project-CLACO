package com.academyinfo.board.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "testboard")
public class BoardEntity extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bindex;
	
	@Column(length = 10, nullable = false)
	private String writer; 
	
	@Column(length = 20, nullable = false) 
	private String title;
	
	// @Column(length = 200, nullable = false)
	@Column(columnDefinition = "TEXT", nullable = false)
	private String contents;
	
	@Column(columnDefinition = "integer default 0")
	private int count;
	
	@Builder
	public BoardEntity(int bindex, String title, String contents, String writer, int count) {
		this.bindex = bindex;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.count = count;
	}
}
