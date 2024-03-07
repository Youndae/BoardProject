package com.board.project.domain.entity;


import lombok.*;

import java.sql.Date;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class Comment {
    private int commentNo;
    private String userId;
    private Date commentDate;
    private String commentContent;
    private int commentGroupNo;
    private int commentUpperNo;
    private int commentIndent;
    private Integer imageNo;
    private Integer boardNo;
    private int commentStatus;

    public Comment(int commentNo
                    , String userId
                    , Date commentDate
                    , String commentContent
                    , int commentGroupNo
                    , int commentUpperNo
                    , int commentIndent
                    , Integer imageNo
                    , Integer boardNo
                    , int commentStatus) {
        this.commentNo = commentNo;
        this.userId = userId;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.commentGroupNo = commentGroupNo;
        this.commentUpperNo = commentUpperNo;
        this.commentIndent = commentIndent;
        this.imageNo = imageNo;
        this.boardNo = boardNo;
        this.commentStatus = commentStatus;
    }
}
