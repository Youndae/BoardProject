package com.board.project.domain.entity;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {

	private String imageName;
	private int imageNo;
	private String oldName;
	private int imageStep;
}
