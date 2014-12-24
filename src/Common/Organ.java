package Common;

public class Organ
{
	private int organId;
	private String organName;
	private String regionCode;
	private String fax;
	private String president;
	
	public Organ(int organId, String organName, String regionCode, String fax, String president)
	{
		super();
		this.organId = organId;
		this.organName = organName;
		this.regionCode = regionCode;
		this.fax = fax;
		this.president = president;
	}
	public Organ(){}
	public int getOrganId()
	{
		return organId;
	}
	public void setOrganId(int organId)
	{
		this.organId = organId;
	}
	public String getOrganName()
	{
		return organName;
	}
	public void setOrganName(String organName)
	{
		this.organName = organName;
	}
	public String getRegionCode()
	{
		return regionCode;
	}
	public void setRegionCode(String regionCode)
	{
		this.regionCode = regionCode;
	}
	public String getFax()
	{
		return fax;
	}
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	public String getPresident()
	{
		return president;
	}
	public void setPresident(String president)
	{
		this.president = president;
	}
}
