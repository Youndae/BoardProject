package com.board.project.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Deprecated
@Getter
@Setter
@ToString
public class SearchCriteria extends Criteria2 {
	
	private String searchType ="";
	private String keyword = "";
}
