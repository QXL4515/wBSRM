package www.hhu.edu;

public class TFIDF_WebServiceBean {
	private int id;
	private String website;
	private String nation;
	private String provider;
	
	public TFIDF_WebServiceBean(int id, String website, String provider, String nation){
		this.id = id;
		this.website = website;
		this.nation = nation;
		this.provider = provider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
}
