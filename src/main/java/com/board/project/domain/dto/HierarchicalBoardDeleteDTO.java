package com.board.project.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class HierarchicalBoardDeleteDTO {

    private int boardNo;

    private int boardUpperNo;

    private int boardGroupNo;
}
