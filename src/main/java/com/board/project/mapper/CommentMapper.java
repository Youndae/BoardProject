package com.board.project.mapper;

import java.util.List;

import com.board.project.domain.dto.CommentDeleteDTO;
import com.board.project.domain.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import com.board.project.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
	//댓글 리스트
	List<Comment> commentList(@Param("vo") Comment comment, @Param("cri") Criteria cri) throws Exception;

	//paging에 사용할 총 댓글 수 조회
	int cListCount(Comment comment) throws Exception;

	//댓글 insert
	void commentInsert(Comment comment);

	//댓글 delete
	void commentDelete(int commentNo);

	//대댓글 insert
	void commentReply(Comment comment);

	//댓글 작성자 조회
	String checkCommentWriter(int commentNo);

	//삭제 댓글의 upperNo, groupNo 조회
	public CommentDeleteDTO checkDeleteComment(int commentNo);

	//계층형 구조의 삭제 처리를 위해 같은 그룹의 모든 댓글 리스트
	public List<CommentDeleteDTO> deleteGroupComment(int commentGroupNo);

	//계층형 구조의 삭제 처리 중 삭제해야하는 리스트의 delete
	public void commentDeleteList(List<Integer> deleteList);
	
}
