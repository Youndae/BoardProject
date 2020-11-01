package com.board.project.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HierarchicalBoardVO {

	private int BoardNo;
	private String BoardTitle;
	private String UserId;
	private String BoardContent;
	private Date BoardDate;
	private int BoardGroupNo;
	private int BoardUpperNo;
	private int BoardIndent;
}
