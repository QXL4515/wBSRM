package www.hhu.edu;

public class TFIDF_ComputePlC1 implements TFIDF_ComputePlCX{
	public double computePlCX(int nC1Xl, int nC1){
		System.out.println("Begin to computePlC1...");
		return (nC1Xl * 1.0 + 1) / (nC1 + 2);//为了避免出现分母为零的情况，使用平滑处理
	}
}
