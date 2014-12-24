package Common;

public class Expert
{
	private int expertNumber;
	private String id;//
	private String password;
	private String name;//
	private String socialNumber;
	private String tel;
	private String officeAddress;
	private String homeAddress;
	private int organId;
	private String position;//
	private String accountInfo;
	private String email;//
	private String field;//
	
	public Expert(String id, String password, int expertNumber, String name, String socialNumber, String tel, String officeAddress,
			String homeAddress, int organId, String position, String accountInfo,
			String email, String field)
	{
		super();
		this.id = id; this.password = password; this.expertNumber = expertNumber;
		this.name = name; this.socialNumber = socialNumber; this.tel = tel;
		this.officeAddress = officeAddress; this.homeAddress = homeAddress;
		this.organId = organId; this.position = position; this.accountInfo = accountInfo;
		this.email = email; this.field = field;
	}
	
	public int getOrganId()
	{
		return organId;
	}

	public void setOrganId(int organId)
	{
		this.organId = organId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Expert(){}

	public int getExpertNumber() {
		return expertNumber;
	}

	public void setExpertNumber(int expertNumber) {
		this.expertNumber = expertNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialNumber() {
		return socialNumber;
	}

	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}


	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
