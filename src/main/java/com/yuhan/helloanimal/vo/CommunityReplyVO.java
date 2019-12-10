package com.yuhan.helloanimal.vo;

import java.util.Date;

import lombok.ToString;

@ToString
public class CommunityReplyVO {

	private long reply_no;
	private long community_no;
	private String reply_content;
	private Date reply_regdate;
	private String reply_writer;
	private String reply_password;
	
	public CommunityReplyVO() {
		
	}
	
	public CommunityReplyVO(long community_no, String content, String writer, String password) {
		this.community_no = community_no;
		this.reply_content = content;
		this.reply_writer = writer;
		this.reply_password = password;
	}
	
	
	public long getReply_no() {
		return reply_no;
	}
	public void setReply_no(long reply_no) {
		this.reply_no = reply_no;
	}
	public long getCommunity_no() {
		return community_no;
	}
	public void setCommunity_no(long community_no) {
		this.community_no = community_no;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_regdate() {
		return reply_regdate;
	}
	public void setReply_regdate(Date reply_regdate) {
		this.reply_regdate = reply_regdate;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public String getReply_password() {
		return reply_password;
	}
	public void setReply_password(String reply_password) {
		this.reply_password = reply_password;
	}
	
	
	
}
