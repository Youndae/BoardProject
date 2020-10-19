package com.board.project.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageBoardVO {

	private int imageNo;
	private String imageTitle;
	private String userId;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private Date imageDate;
}
