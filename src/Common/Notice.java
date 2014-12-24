package Common;

import java.util.Date;

public class Notice {
	private Date date;
	private String type;
	private String body;
	private String email;
	private String file;
	
	public Notice(Date date, String type, String body, String email, String file)
	{
		this.date = date; this.type = type; this.body = body;
		this.email = email; this.file = file;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

}
