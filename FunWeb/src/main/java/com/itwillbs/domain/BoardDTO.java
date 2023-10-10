package com.itwillbs.domain;

import java.sql.Timestamp;

public class BoardDTO {
		// 전달할 데이터를 담는곳 -> 멤버변수 정의 
		// 전달할 데이터는 아주 중요한 데이터 -> 멤버변수 private 은닉 
		// 외부에서 접근할 수 있는 set, get 메서드 
		// BoardDTO 객체생성 (num, name, subject, content, readcount, date)
		private int num;
		private String name;
		private String subject;
		private String content;
		private int readcount;
		private Timestamp date;
		//첨부파일(파일이름) 
		private String file;
		//검색어 
//		private String search;
		
		@Override
		public String toString() {
			return "BoardDTO [num=" + num + ", name=" + name + ", subject=" + subject + ", content=" + content
					+ ", readcount=" + readcount + ", date=" + date + ", file=" + file + "]";
		}

//		public String getSearch() {
//			return search;
//		}
//
//		public void setSearch(String search) {
//			this.search = search;
//		}		
		
		public String getFile() {
			return file;
		}
		public void setFile(String file) {
			this.file = file;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getReadcount() {
			return readcount;
		}
		public void setReadcount(int readcount) {
			this.readcount = readcount;
		}
		public Timestamp getDate() {
			return date;
		}
		public void setDate(Timestamp date) {
			this.date = date;
		}



}
