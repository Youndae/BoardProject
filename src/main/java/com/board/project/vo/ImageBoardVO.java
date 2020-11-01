package com.board.project.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageBoardVO {

	private int ImageNo;
	private String ImageTitle;
	private String ImageContent;
	private String UserId;
	private Date ImageDate;
}
