package Common;

import java.util.Date;

public class Subject {
	int number;
	String type;
	String area;
	String name;
	String description;
	Date openDate;
	Date closeDate;
	
	Subject(){}

	public Subject(int number, String type, String area, String name, String description,
			Date openDate, Date closeDate) {
		super();
		this.number = number;
		this.type = type;
		this.area = area;
		this.name = name;
		this.description = description;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}

	public int getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
}
