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
	public Boolean getIsQuestion() {
		return isQuestion;
	}
	public String getIndex() {
		return index;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getDate() {
		return date;
	}
	public String getIndexVerAnswer() {
		String[] array = this.index.split("]");
		return array[1];
	}
	@Override
	public String toString() {
		return index + " "+writer+ " "+title+ " "+content+ " "+date;
	}	
}
