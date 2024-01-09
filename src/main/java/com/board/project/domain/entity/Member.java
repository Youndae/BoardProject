package com.board.project.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Member {

	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;

	private List<Auth> authList;
}
