package www.hhu.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class TFIDF_ComputePrePro_C1 implements TFIDF_ComputePrePro_CX{
	public double computePrePro_CX(double plC1, int x, int y, 
			ArrayList<TFIDF_UserBean> userList, ArrayList<TFIDF_WebServiceBean> webServiceList,
			HashMap<HashMap<String, String>, Double> ll2Wi_C1, int[][] tp){
		//计算先验概率
		int temp = tp[x][y];
		String W;
	//	System.out.println( tp[x][y]);
		double pXiC1 = Math.pow(plC1, temp) * Math.pow((1 - plC1), (1 - temp));
		HashMap<String, String> ll = new HashMap<String, String>();
		ll.put(userList.get(x).getNation(), webServiceList.get(y).getNation());
		String LL = ll.put(userList.get(x).getNation(), webServiceList.get(y).getNation());
		
		double wi_c1 = ll2Wi_C1.get(ll) ;
		
	/*	 String I = ll.put(userList.get(x).getNation(), webServiceList.get(y).getNation()) + wi_c1;
		 try {
	  		   //String teststr=test();
	  		   FileWriter fw = new FileWriter("d:/test/test_w1.txt",true);
	  		   BufferedWriter bw = new BufferedWriter(fw);
	  		   bw.write(I +'\n');
	  		   bw.newLine();
	  		   bw.flush();
	  		   fw.close();
	  		   bw.close();
	  	 }catch (Exception e) {
	  		   // TODO Auto-generated catch block
	  		   e.printStackTrace();
	  		  }
		*/
	//double	wi_c1 = 1;
	//	System.out.println("1111111111wi_c1" + wi_c1);
	//	wi_c1 = wi_c1 * 100;
	//	double wi_pXiC1 = Math.pow(pXiC1, wi_c1);
		
		W = "*" + wi_c1;
	/* try {
 		   //String teststr=test();
 		   FileWriter fw = new FileWriter("d:/test.txt",true);
 		   BufferedWriter bw = new BufferedWriter(fw);
 	//	    bw.write(LL);
 		   bw.write(W +'\n');
 		   bw.newLine();
 		   bw.flush(); 
 		   fw.close();
 		   bw.close();
 	 }catch (Exception e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 		  }*/
		double wi_pXiC1 =wi_c1 * ( Math.log(1 - pXiC1) + wi_c1 *  Math.log(pXiC1/(1 - pXiC1)));
		return wi_pXiC1;
	}
}
