package com.board.project.domain.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class CommentDeleteDTO {

    private int commentNo;

    private int commentUpperNo;

    private int commentGroupNo;
}
