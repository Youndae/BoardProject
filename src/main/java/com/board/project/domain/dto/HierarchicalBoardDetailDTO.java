package com.board.project.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class HierarchicalBoardDetailDTO {

    private int boardNo;

    private String boardTitle;

    private String boardContent;

    private String userId;

    private Date boardDate;
}
