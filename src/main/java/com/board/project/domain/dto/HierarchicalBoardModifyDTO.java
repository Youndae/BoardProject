package com.board.project.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HierarchicalBoardModifyDTO {

    private int boardNo;

    private String boardTitle;

    private String boardContent;
}
