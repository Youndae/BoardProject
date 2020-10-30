package com.board.project.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageBoardVO {

	private int boardNo;
	private String imageTitle; 
	private String userId;
	private Date imageDate;
}
