package com.board.project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDataVO extends ImageBoardVO{

	private String ImageName;
	private int ImageNo;
	private String OldName;
	private int ImageStep;
}
