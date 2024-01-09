package com.board.project.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class ImageBoardDetailDTO {

    private int imageNo;

    private String imageTitle;

    private String imageContent;

    private String userId;

    private Date imageDate;

    private String imageName;

}
