package www.hhu.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TFIDF_ComputeWi_C0 implements TFIDF_ComputeWi{
	public HashMap<HashMap<String, String>, Double> computeWi(ArrayList<TFIDF_UserBean> userList, ArrayList<TFIDF_WebServiceBean> webServiceList, 
			HashMap<HashMap<String, String>, Integer> ll2Num/*所有国家组合的总数*/, HashMap<HashMap<String, String>, Integer> ll2C0/*所有国家组合所有属于c0的*/, double preset_value){
		System.out.println("Begin to computeWi_C0...");
		long begin = System.currentTimeMillis();
		
		HashMap<HashMap<String, String>, Double> ll2W0Temp = new HashMap<HashMap<String, String>, Double>();
		int n = userList.size() * webServiceList.size();//记录所有数据个数
		
		Iterator<Entry<HashMap<String, String>, Integer>> c0Iterator = ll2C0.entrySet().iterator();
		int nCi = 0;//记录所有 c0/c1 的个数
		while(c0Iterator.hasNext()){
			Entry<HashMap<String, String>, Integer> entry = c0Iterator.next();
			nCi += entry.getValue();
		}
		
		Iterator<Entry<HashMap<String, String>, Integer>> numIterator = ll2Num.entrySet().iterator();
		while(numIterator.hasNext()){
			Entry<HashMap<String, String>, Integer> entry = numIterator.next();
			if(ll2C0.containsKey(entry.getKey())){
				int nCiRi = ll2C0.get(entry.getKey());
		//		System.out.println("nC0Ri" + nCiRi);
				int nRi = entry.getValue();
				double wi = nCiRi * 1.0 / nCi * Math.log(n / nRi);
		//		System.out.println("wi_C0" + wi);
				ll2W0Temp.put(entry.getKey(), wi);
			}else{ 
				ll2W0Temp.put(entry.getKey(), preset_value);
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("End to computeWi_C0, takes" + new TFIDF_ComputeTime().computeTime(begin, end) + "1000 ms...");
	//	System.out.println(ll2W0Temp);
		return ll2W0Temp;
	}
}
