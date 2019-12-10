package com.yuhan.helloanimal.vo;

import java.util.Date;

import lombok.ToString;

@ToString
public class CommunityVO {
		
	private long community_no; //커뮤니티 번호
	private String community_title; //제목
	private String community_content; //내용
	private String community_password; //비밀번호
	private String community_writer; //작성자
	private long community_hits; //조회수
	private Date community_regdate; //등록일
	private Date community_updatedate; //수정일
	
	public CommunityVO() {
		
	}
	
	public CommunityVO(String title, String content, String password) {
		this.community_title = title;
		this.community_content = content;
		this.community_password = password;
	}
	
	public long getCommunity_no() {
		return community_no;
	}
	public void setCommunity_no(long community_no) {
		this.community_no = community_no;
	}
	public String getCommunity_title() {
		return community_title;
	}
	public void setCommunity_title(String community_title) {
		this.community_title = community_title;
	}
	public String getCommunity_content() {
		return community_content;
	}
	public void setCommunity_content(String community_content) {
		this.community_content = community_content;
	}
	public String getCommunity_password() {
		return community_password;
	}
	public void setCommunity_password(String community_password) {
		this.community_password = community_password;
	}
	public long getCommunity_hits() {
		return community_hits;
	}
	public void setCommunity_hits(long community_hits) {
		this.community_hits = community_hits;
	}
	public Date getCommunity_regdate() {
		return community_regdate;
	}
	public void setCommunity_regdate(Date community_regdate) {
		this.community_regdate = community_regdate;
	}
	public Date getCommunity_updatedate() {
		return community_updatedate;
	}
	public void setCommunity_updatedate(Date community_updatedate) {
		this.community_updatedate = community_updatedate;
	}

	public String getCommunity_writer() {
		return community_writer;
	}

	public void setCommunity_writer(String community_writer) {
		this.community_writer = community_writer;
	}
	
	
}
