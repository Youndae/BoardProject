package com.board.project.domain.dto;

import com.board.project.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentDTO {

    private List<Comment> commentList;

    private PageDTO pageDTO;

    private String uid;
}
