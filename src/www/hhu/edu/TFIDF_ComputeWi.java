package www.hhu.edu;

import java.util.ArrayList;
import java.util.HashMap;

public interface TFIDF_ComputeWi {
	public HashMap<HashMap<String, String>, Double> computeWi(ArrayList<TFIDF_UserBean> userList, ArrayList<TFIDF_WebServiceBean> webServiceList, 
			HashMap<HashMap<String, String>, Integer> ll2Num, HashMap<HashMap<String, String>, Integer> ll2C0, double preset_value);
}
