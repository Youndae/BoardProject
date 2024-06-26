package com.board.project.domain.dto;

@Deprecated
public class Criteria2 {
	
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	
	public Criteria2() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		System.out.println("getPage : "+page);
		return page;
	}
	public void setPage(int page) {
		System.out.println("setPage : "+page);
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		System.out.println("setPerPageNum : "+perPageNum);
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			System.out.println("setPerPageNum If : "+this.perPageNum);
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getpageStart() {
		return (this.page -1) * perPageNum;
	}
	
	public int getRowStart() {
		System.out.println("page : "+page+" perPageNum : "+perPageNum);
		rowStart = ((page - 1) * perPageNum + 1);
		System.out.println("rowStart : "+rowStart);
		return rowStart;
	}
	
	public int getRowEnd() {
		System.out.println("RowEnd Start : "+rowStart+" perPageNum : "+perPageNum);
		rowEnd = rowStart + perPageNum - 1;
		System.out.println("rowEnd : "+rowEnd);
		return rowEnd;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}
	
	
}
