package com.board.project.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.DATE;

@Getter
@Setter
@ToString
public class BoardComment {
    private int commentNo;
    private String userId;
    private DATE commentDate;
    private String commentContent;
    private int commentGroupNo;
    private int commentUpperNo;
    private int imageNo;
    private int boardNo;
}
