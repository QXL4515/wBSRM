package www.hhu.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class TFIDF_ReadTPDataFromTxt implements TFIDF_ReadDataFromTxt{
	private String path;
	private double[][] TPData;
	
	public TFIDF_ReadTPDataFromTxt(String path, int x, int y){
		this.path = path;
		this.TPData = new double[x][y];
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
				if((TPData[0].length != paras.length)){
					System.out.println("Error, 数据不匹配");
					return null;
				}else{
					double[] line = new double[paras.length];
					for(int i=0; i<paras.length; i++){
						line[i] = Double.parseDouble(paras[i]);
					}
					TPData[lineNum++] = line;
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
		if(TPData != null){
			return TPData;
		}else{
			System.out.println("没有TP数据");
			return null;
		}
	}
}
