package com.board.project.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HierarchicalBoardVO {

	private int boardNo;
	private String boardTitle;
	private String userId;
	private String boardContent;
	private Date boardDate;
	private int boardGroupNo;
	private int boardUpperNo;
}
