package www.hhu.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class TFIDF_ReadRTDataFromTxt implements TFIDF_ReadDataFromTxt{
	private String path;
	private double[][] rtData;
	
	public TFIDF_ReadRTDataFromTxt(String path, int x, int y){
		this.path = path;
		this.rtData = new double[x][y];
	}
	
	public double[][] readData(){
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(new File(path));
			br = new BufferedReader(new InputStreamReader(fis));
			String temp;
			int lineNum = 0;
			while((temp=br.readLine()) != null){
				String[] paras = temp.split("	");
				if((rtData[0].length != paras.length)){
					System.out.println("Error, 数据不匹配");
					return null;
				}else{
					double[] line = new double[paras.length];
					for(int i=0; i<paras.length; i++){
						line[i] = Double.parseDouble(paras[i]);
					}
					rtData[lineNum++] = line;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		if(rtData != null){
			return rtData;
		}else{
			System.out.println("没有RT数据");
			return null;
		}
	}
}
