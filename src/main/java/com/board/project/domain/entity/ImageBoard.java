package com.board.project.domain.entity;

import java.sql.Date;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageBoard {

	private int imageNo;
	private String imageTitle;
	private String imageContent;
	private String userId;
	private Date imageDate;
}
