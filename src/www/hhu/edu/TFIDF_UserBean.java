package www.hhu.edu;

public class TFIDF_UserBean {
	private int id;
	private String ip;
	private String nation;
	private double longitude;
	private double latitude;
	
	public TFIDF_UserBean(int id, String ip, String nation, double longitude, double latitude){
		this.id = id;
		this.ip = ip;
		this.nation = nation;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}
