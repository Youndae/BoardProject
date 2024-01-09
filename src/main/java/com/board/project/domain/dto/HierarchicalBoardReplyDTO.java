package com.board.project.domain.dto;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class HierarchicalBoardReplyDTO {

    private int boardNo;

    private int boardIndent;

    private int boardGroupNo;
}
