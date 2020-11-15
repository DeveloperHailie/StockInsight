package model;

public class QnAList {
	
	private Boolean isQuestion;
	private String index;
	private String writer;
	private String title;
	private String content;
	private String date;
	QnAList(){
		isQuestion = true;
		index = "";
		writer = "";
		title = "";
		content = "";
		date = "";
	}
	QnAList(Boolean isQuestion,	String index, String writer, String title, String content, String date){
		this.isQuestion = isQuestion;
		if(isQuestion) {
			this.index = index;
		}else {
			this.index = "[re]"+index;
		}
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	void setQnAList(Boolean isQuestion,	String index, String writer, String title, String content, String date){
		this.isQuestion = isQuestion;
		if(isQuestion) {
			this.index = index;
		}else {
			this.index = "[re]"+index;
		}
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	Boolean getIsQuestion() {
		return isQuestion;
	}
	String getIndex() {
		return index;
	}
	String getWriter() {
		return writer;
	}
	String getTitle() {
		return title;
	}
	String getContent() {
		return content;
	}
	String getDate() {
		return date;
	}
	@Override
	public String toString() {
		return index + " "+writer+ " "+title+ " "+content+ " "+date;
	}	
}
