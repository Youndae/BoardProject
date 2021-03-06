package com.board.project.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.DATE;

@Getter
@Setter
@ToString
public class CommentVO extends Criteria{
    private int CommentNo;
    private String UserId;
    private DATE CommentDate;
    private String CommentContent;
    private int CommentGroupNo;
    private int CommentUpperNo;
    private int CommentIndent;
    private Integer ImageNo;
    private Integer BoardNo;
}
