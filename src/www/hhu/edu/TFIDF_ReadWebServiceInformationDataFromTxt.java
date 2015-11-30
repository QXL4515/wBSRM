package www.hhu.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TFIDF_ReadWebServiceInformationDataFromTxt implements TFIDF_ReadListDataFromTxt{
	private String path;
	private ArrayList<TFIDF_WebServiceBean> webServiceList;
	
	public TFIDF_ReadWebServiceInformationDataFromTxt(String path){
		this.path = path;
	}
	
	public ArrayList<TFIDF_WebServiceBean> readData(){
		char[] buffer = new char[1024];
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(new File(path));
			br = new BufferedReader(new InputStreamReader(fis));
			webServiceList = new ArrayList<TFIDF_WebServiceBean>();
			String temp;
			while ((temp=br.readLine()) != null) {
				String[] paras = temp.split("	");
				int id = Integer.parseInt(paras[0]);
				String website = paras[1];
				String provider = paras[2];
				String nation = paras[3];
				webServiceList.add(new TFIDF_WebServiceBean(id, website, provider, nation));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(webServiceList != null){
			return webServiceList;
		}else{
			System.out.println("没有网站数据");
			return null;
		}
	}
	
}
