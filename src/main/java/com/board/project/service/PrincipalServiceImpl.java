package com.board.project.service;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.mapper.ImageBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService{

    private final HierarchicalBoardMapper hierarchicalBoardMapper;

    private final ImageBoardMapper imageBoardMapper;

    private final CommentMapper commentMapper;

    @Override
    public boolean checkWriter(int no, String type, Principal principal) {

        if(type.equals("board")) {
            if(principal.getName().equals(hierarchicalBoardMapper.checkWriter(no)))
                return true;

            return false;
        }else if(type.equals("image")){
            if(principal.getName().equals(imageBoardMapper.checkWriter(no)))
                return true;

            return false;
        }else if(type.equals("comment")){
            if(principal.getName().equals(commentMapper.checkCommentWriter(no)))
                return true;

            return false;
        }

        return false;
    }
}
