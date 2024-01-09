package com.board.project.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class HierarchicalBoardListDTO {

    private int boardNo;

    private String boardTitle;

    private String userId;

    private Date boardDate;

    private int boardIndent;
}
