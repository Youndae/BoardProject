package com.board.project.domain.entity;


import lombok.*;

import java.sql.Date;

@Getter
@Builder
@ToString
@AllArgsConstructor
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
}
