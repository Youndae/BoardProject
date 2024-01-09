package com.board.project.domain.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Deprecated
public class PageMaker {


	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 5; 
	private Criteria2 cri;
	
	
	public void setCri(Criteria2 cri) {
		this.cri = cri;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		System.out.println("totalCount : "+totalCount);
		calcData();
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public Criteria2 getCri() {
		return cri;
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));

		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		System.out.println("prev : "+prev+" next : "+next);
	}
	
	public String makeQuery(int page) {
		System.out.println("makeQuery page : "+page);
		System.out.println("makeQuery perpage : "+cri.getPerPageNum());
		UriComponents uriComponents = 
			UriComponentsBuilder.newInstance()
								.queryParam("page", page)
								.queryParam("perPageNum", cri.getPerPageNum())
								.build();
		
		return uriComponents.toUriString();
	}	
	
	public String makeSearch(int page) {
		System.out.println("makeSearch page : "+page);
		System.out.println("makeSearch perpage : "+cri.getPerPageNum());
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
				.build();
		return uriComponents.toUriString();
	}
	
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			return "";
		}
	}
}