package com.board.project.domain.entity;

import java.sql.Date;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HierarchicalBoard {

	private int boardNo;
	private String boardTitle;
	private String userId;
	private String boardContent;
	private Date boardDate;
	private int boardGroupNo;
	private int boardUpperNo;
	private int boardIndent;
}
