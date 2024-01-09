package com.board.project.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {

    private int pageNum;
    private int boardAmount;
    private int imageAmount;

    private String keyword;
    private String searchType;

    public Criteria(){
        this(1, 20, 15);
    }

    public Criteria(int pageNum, int boardAmount, int imageAmount){
        this.pageNum = pageNum;
        this.boardAmount = boardAmount;
        this.imageAmount = imageAmount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
