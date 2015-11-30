package www.hhu.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TFIDF_ComputeWi_C1 implements TFIDF_ComputeWi{
	public HashMap<HashMap<String, String>, Double> computeWi(ArrayList<TFIDF_UserBean> userList, ArrayList<TFIDF_WebServiceBean> webServiceList, 
			HashMap<HashMap<String, String>, Integer> ll2Num, HashMap<HashMap<String, String>, Integer> ll2C1, double preset_value1){
		System.out.println("Begin to computeWi_C1...");
		long begin = System.currentTimeMillis();
		HashMap<HashMap<String, String>, Double> ll2W1Temp = new HashMap<HashMap<String, String>, Double>();
		int n = userList.size() * webServiceList.size();//记录所有数据个数
		Iterator<Entry<HashMap<String, String>, Integer>> c1Iterator = ll2C1.entrySet().iterator();
		int nCi = 0;//记录所有 c0/c1 的个数
		while(c1Iterator.hasNext()){
			Entry<HashMap<String, String>, Integer> entry = c1Iterator.next();
			nCi += entry.getValue();
		}
		
		Iterator<Entry<HashMap<String, String>, Integer>> numIterator = ll2Num.entrySet().iterator();
		while(numIterator.hasNext()){
			Entry<HashMap<String, String>, Integer> entry = numIterator.next();
			if(ll2C1.containsKey(entry.getKey())){
				int nCiRi = ll2C1.get(entry.getKey());
	//			System.out.println("nC1Ri" + nCiRi);
				int nRi = entry.getValue();
				double wi = nCiRi * 1.0 / nCi * Math.log(n / nRi);
		//		System.out.println("wi_C1" + wi);
				ll2W1Temp.put(entry.getKey(), wi);
			}else{
				ll2W1Temp.put(entry.getKey(), preset_value1);
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("End to computeWi_C1, takes" + new TFIDF_ComputeTime().computeTime(begin, end) + "ms...");
	//	System.out.println(ll2W1Temp);
		//System.out.println("wiTemp" + ll2WiTemp.size());
		return ll2W1Temp;
	}
}
