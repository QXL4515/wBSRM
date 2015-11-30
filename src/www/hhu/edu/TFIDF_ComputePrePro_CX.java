package www.hhu.edu;

import java.util.ArrayList;
import java.util.HashMap;

public interface TFIDF_ComputePrePro_CX {
	public double computePrePro_CX(double plC1, int x, int y, 
			ArrayList<TFIDF_UserBean> userList, ArrayList<TFIDF_WebServiceBean> webServiceList,
			HashMap<HashMap<String, String>, Double> ll2Wi_Ci, int[][] tp);
}
