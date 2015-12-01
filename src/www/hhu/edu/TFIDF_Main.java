package www.hhu.edu;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

public class TFIDF_Main {
	// 目标β值
	private static double BETA;// = 0.4; // BETA为QoS属性标准。即设置的一个基线——满足QoS要求的比例数
								// 与这个基线 进行比较
	private static double QoS_VALUE;// = 8.0; // QoS属性值要求
	private static final int DISTANCE = 150;
	private static final double PERSET_VALUE = 0.0;
	// private static final int T = 1000;
	private static String USERINFORMATION_DATA_PATH = null; // "F:/TFIDF_Data/userlist.txt";
	private static String WSINFORMATION_DATA_PATH = null; // "F:/TFIDF_Data/wslist.txt";
	private static String TPINFORMATION_DATA_PATH = null; // "F:/TFIDF_Data/tpmatrix.txt";
	private static String RTINFORMATION_DATA_PATH = null; // "F:/TFIDF_Data/rtmatrix.txt";
	private static String OUT_PATH = null;
	private static ArrayList<TFIDF_UserBean> userList = new ArrayList<TFIDF_UserBean>();
	private static ArrayList<TFIDF_WebServiceBean> webServiceList = new ArrayList<TFIDF_WebServiceBean>();
	private static HashMap<HashMap<String, String>, Integer> ll2Num = new HashMap<HashMap<String, String>, Integer>();// 记录所有国家组合的总数
	private static HashMap<HashMap<String, String>, Integer> ll2C0 = new HashMap<HashMap<String, String>, Integer>();// 记录所有国家组合的C0个数
	private static HashMap<HashMap<String, String>, Integer> ll2C1 = new HashMap<HashMap<String, String>, Integer>();// 记录所有国家组合的C1个数
	private static HashMap<HashMap<String, String>, Double> ll2Wi_C0 = new HashMap<HashMap<String, String>, Double>();// 记录所有国家组合的Wi_C0
	private static HashMap<HashMap<String, String>, Double> ll2Wi_C1 = new HashMap<HashMap<String, String>, Double>();// 记录所有国家组合的Wi_C1
	private static double[][] rtData, tpData;
	private static int[][] tp, rt; // 记录吞吐量、响应时间对应的0-1值
	private static int nC0Xl, nC1Xl, nC0, nC1;// Xl 表示 Xk = 1;
												// nC0、nC1指的是通过先验概率的n——即计算先验概率的分子。
	private static double prePro_C0 = 1.0;
	private static double prePro_C1 = 1.0;

	private static Frame f = null;
	private static Button but = null;
	private static Button but1 = null;
	private static Button but2 = null;
	private static Button but3 = null;
	private static Button but4 = null;
	private static Button startButton = null;
	private static FileDialog openDia = null;
	private static JFileChooser openDir = null;
	private static TextField BetaTF = null;
	private static TextField QoSTF = null;
	private static TextArea outTA = null;
	private static MenuBar infoBar = null;
	private static Menu infoM = null;
	private static MenuItem infoIt = null;
	private static Dialog authorDia = null;
	private static Label authorLab = null;
	private static Label authorLab1 = null;
	private static Label authorLab2 = null;

	public TFIDF_Main() {
		init();
	}

	public static void init() {
		f = new Frame("wBSRM");

		f.setBounds(300, 100, 600, 360);
		f.setLayout(new FlowLayout());

		infoBar = new MenuBar();
		infoM = new Menu("Help");
		infoIt = new MenuItem("About...");
		but = new Button("Open_UserList");
		but1 = new Button("Open_WSList");
		but2 = new Button("Open_TPInfo");
		but3 = new Button("Open_RTInfo");
		but4 = new Button("Out_Path");
		authorLab1 = new Label();
		authorLab1.setAlignment(Label.RIGHT);
		authorLab1.setText("To Input Predefine BETA:");
		BetaTF = new TextField();
		authorLab2 = new Label();
		authorLab2.setAlignment(Label.RIGHT);
		authorLab2.setText("To Input Standard QoS_VALUE:");
		QoSTF = new TextField();
		QoSTF.setSize(30, 10);
		startButton = new Button("Start_Compute");
		outTA = new TextArea();

		f.setMenuBar(infoBar);
		openDia = new FileDialog(f, "USERINFORMATION_DATA To Choose",
				FileDialog.LOAD);

		f.add(but);
		f.add(but1);
		f.add(but2);
		f.add(but3);
		f.add(but4);
		f.add(startButton);
		f.add(authorLab1);
		f.add(BetaTF);
		f.add(authorLab2);
		f.add(QoSTF);
		f.add(outTA);

		infoBar.add(infoM);
		infoM.add(infoIt);

		myEvent();

		f.setVisible(true);

	}

	private static void myEvent() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		infoIt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				authorDia = new Dialog(f, "About:", false);
				authorLab = new Label();
				authorLab.setAlignment(Label.CENTER);
				authorLab
						.setText("@author ZH-He          @version 1.0.0");
				authorDia.add(authorLab);
				authorDia.setBounds(400, 200, 300, 100);
				authorDia.setVisible(true);
				authorDia.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						authorDia.setVisible(false);
					}
				});
			}
		});

		but.addActionListener(new UserLis());
		but1.addActionListener(new WSLis());
		but2.addActionListener(new TPLis());
		but3.addActionListener(new RTLis());
		but4.addActionListener(new OutKLis());
		BetaTF.addActionListener(new BetaTFLis());
		QoSTF.addActionListener(new QoSTFLis());
		startButton.addActionListener(new StartLis());
	}

	public static class UserLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			openDia.setVisible(true);

			String dirPath = openDia.getDirectory();
			String fileName = openDia.getFile();

			String wholePath = dirPath + fileName;

			TFIDF_Main.USERINFORMATION_DATA_PATH = wholePath;

			// System.out.println(TFIDF_Main.USERINFORMATION_DATA_PATH);
		}
	}

	public static class WSLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			openDia.setVisible(true);

			String dirPath = openDia.getDirectory();
			String fileName = openDia.getFile();

			String wholePath = dirPath + fileName;

			TFIDF_Main.WSINFORMATION_DATA_PATH = wholePath;

			// System.out.println(TFIDF_Main.WSINFORMATION_DATA_PATH);
		}
	}

	public static class TPLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			openDia.setVisible(true);

			String dirPath = openDia.getDirectory();
			String fileName = openDia.getFile();

			String wholePath = dirPath + fileName;

			TFIDF_Main.TPINFORMATION_DATA_PATH = wholePath;

			// System.out.println(TFIDF_Main.TPINFORMATION_DATA_PATH);
		}
	}

	public static class RTLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			openDia.setVisible(true);

			String dirPath = openDia.getDirectory();
			String fileName = openDia.getFile();

			String wholePath = dirPath + fileName;

			TFIDF_Main.RTINFORMATION_DATA_PATH = wholePath;

			// System.out.println(TFIDF_Main.RTINFORMATION_DATA_PATH);
		}
	}

	public static class OutKLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			openDir = new JFileChooser();

			openDir.setFileSelectionMode(openDir.DIRECTORIES_ONLY);

			openDir.showOpenDialog(but4);

			String dirPath = openDir.getSelectedFile().getPath();

			TFIDF_Main.OUT_PATH = dirPath;
//			System.out.println(TFIDF_Main.OUT_PATH);

			// System.out.println(TFIDF_Main.USERINFORMATION_DATA_PATH);
		}
	}

	public static class BetaTFLis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BetaTF.select(0, BetaTF.getSelectionEnd());

			// System.out.println(BetaTF.getSelectedText());
			TFIDF_Main.BETA = Double.parseDouble(BetaTF.getSelectedText());
		}
	}

	public static class QoSTFLis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			QoSTF.select(0, QoSTF.getSelectionEnd());
//			System.out.println(QoSTF.getSelectedText());
			TFIDF_Main.QoS_VALUE = Double.parseDouble(QoSTF.getSelectedText());
		}
	}

	public static class StartLis implements ActionListener {

		public void actionPerformed(ActionEvent ae) {

			nC0 = 0;
			nC1 = 0;
			long begin_n = 0;
			long end_n = 0;
			readData(USERINFORMATION_DATA_PATH, WSINFORMATION_DATA_PATH,
					RTINFORMATION_DATA_PATH, TPINFORMATION_DATA_PATH);// 读取文件数据
			traversalTPMatrix(userList, webServiceList, tpData);// 处理tp数据，如果需要处理rt数据则把二维数组换成rtdata
			// System.out.println("BETA:" + BETA);

			// Thread.sleep(6000);

			ll2Wi_C0 = new TFIDF_ComputeWi_C0().computeWi(userList,
					webServiceList, ll2Num, ll2C0, PERSET_VALUE);// 计算Wi_C0
			ll2Wi_C1 = new TFIDF_ComputeWi_C1().computeWi(userList,
					webServiceList, ll2Num, ll2C1, PERSET_VALUE);// 计算Wi_C1

			double plC0 = new TFIDF_ComputePlC0().computePlCX(nC0Xl, nC0);// 计算P
																			// c0
			double plC1 = new TFIDF_ComputePlC1().computePlCX(nC1Xl, nC1);// 计算P
																			// c1

//			outTA.append("plC0" + plC0 + "\r\n");
//			outTA.append("plC1" + plC1 + "\r\n");
			outTA.append("The output data is right in the path："+OUT_PATH + "\test_data.txt" + "\r\n");

			nC0 = 0;
			nC1 = 0;
			int n = 0; // 循环中目前为止 的样本数量
			int x = 0; // 循环中目前为止 小于给定QoS值的样本数量
			double K;
			String R0;
			String R1;
			begin_n = System.currentTimeMillis();

			for (int i = 0; i < DISTANCE; i++) {
				for (int m = i; m < tpData.length; m += DISTANCE) {
					for (int q = 0; q < DISTANCE; q++) {
						for (int p = q; p < tpData[0].length; p += DISTANCE) {
							n++;
							if (n == 2001) {
								end_n = System.currentTimeMillis();
								System.out.println("begin_n: " + begin_n);
								System.out.println("end_n: " + end_n);
								// System.out.println("2000个样本所用时间"+ (end_n -
								// begin_n));
								outTA.append("2000个样本所用时间" + (end_n - begin_n)
										+ "\r\n");
								return;
							}
							if (tpData[m][p] <= QoS_VALUE) {
								x++;
							}
							double aftPro_C0 = computeAftPro_C0(plC0, m, p,
									userList, webServiceList, ll2Wi_C0, tpData,
									x, n, tp);
							double aftPro_C1 = computeAftPro_C1(plC1, m, p,
									userList, webServiceList, ll2Wi_C1, tpData,
									x, n, tp);
							K = Math.pow(Math.abs(aftPro_C0), BETA)
									/ Math.pow(Math.abs(aftPro_C1), BETA);
							try {

								FileWriter fw = new FileWriter(OUT_PATH
										+ "/test_K.txt", true);
								BufferedWriter bw = new BufferedWriter(fw);

								String G = "K = " + K;
								bw.write(G);
								bw.newLine();
								bw.flush();
								fw.close();
								bw.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							R0 = "******* aftPro_C0: " + aftPro_C0 + " *******";
							R1 = "******* aftPro_C1: " + aftPro_C1 + " *******";
							try {
								FileWriter fw = new FileWriter(OUT_PATH
										+ "/test_data.txt", true);
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(R0);
								bw.newLine();
								bw.write(R1);
								bw.newLine();
								bw.flush();
								fw.close();
								bw.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (K > 1) {
								try {
									FileWriter fw = new FileWriter(OUT_PATH
											+ "/test_YorN.txt", true);
									BufferedWriter bw = new BufferedWriter(fw);

									String s = "1";
									bw.write(s);
									bw.newLine();
									bw.flush();
									fw.close();
									bw.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (K < 1) {
								try {
									FileWriter fw = new FileWriter(OUT_PATH
											+ "/test_YorN.txt", true);
									BufferedWriter bw = new BufferedWriter(fw);

									String s = "-1";
									bw.write(s);
									bw.newLine();
									bw.flush();
									fw.close();
									bw.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		new TFIDF_Main();

	}

	public static Long[] readData(String userPath, String webSevicePath,
			String RTPath, String TPPath) {

		Long[] time = new Long[4];
		// 读取userList数据
		userList = new TFIDF_ReadUserInformationDataFromTxt(userPath)
				.readData();
		System.out.println("Read user information success...");
		// 读取webService数据
		webServiceList = new TFIDF_ReadWebServiceInformationDataFromTxt(
				webSevicePath).readData();
		System.out.println("Read web service information success...");

		rtData = new double[userList.size()][webServiceList.size()];
		tpData = new double[userList.size()][webServiceList.size()];
		rt = new int[userList.size()][webServiceList.size()];
		tp = new int[userList.size()][webServiceList.size()];
		// 读取rt数据
		System.out.println("Begin to read RT matrix...");
		time[0] = System.currentTimeMillis();
		rtData = new TFIDF_ReadRTDataFromTxt(RTINFORMATION_DATA_PATH,
				userList.size(), webServiceList.size()).readData();
		time[1] = System.currentTimeMillis();
		System.out.println("End to read RT matrix, takes"
				+ new TFIDF_ComputeTime().computeTime(time[0], time[1])
				+ "ms...");

		// 读取tp数据
		System.out.println("Begin to read PT matrix...");
		time[2] = System.currentTimeMillis();
		tpData = new TFIDF_ReadTPDataFromTxt(TPINFORMATION_DATA_PATH,
				userList.size(), webServiceList.size()).readData();
		time[3] = System.currentTimeMillis();
		System.out.println("End to read PT matrix, takes"
				+ new TFIDF_ComputeTime().computeTime(time[2], time[3])
				+ "ms...");
		return time;
	}

	/**
	 * @param userList
	 * @param webServiceList
	 * @param matrix
	 */
	public static void traversalTPMatrix(ArrayList<TFIDF_UserBean> userList,
			ArrayList<TFIDF_WebServiceBean> webServiceList, double[][] matrix) {
		System.out.println("Begin to traversalMatrix...");
		// System.out.println("matrix.length------"+matrix.length);
		long begin = System.currentTimeMillis();
		int n = 0;
		int x = 0;
		double c = 0.0;
		nC0Xl = 0;
		nC1Xl = 0;
		nC0 = 0;
		nC1 = 0;
		for (int i = 0; i < DISTANCE; i++) {
			for (int m = i; m < matrix.length; m += DISTANCE) {
				for (int q = 0; q < DISTANCE; q++) {
					for (int p = q; p < matrix[0].length; p += DISTANCE) {

						// System.out.println("i: "+i+" m: "+m+" q: "+q+" p: "+p+"...");
						// System.out.println(" m: "+m+" p: "+p+"...");
						n++;
						TFIDF_UserBean userInformation = userList.get(m);
						TFIDF_WebServiceBean webServiceInformation = webServiceList
								.get(p);
						HashMap<String, String> ll = new HashMap<String, String>();
						ll.put(userInformation.getNation(),
								webServiceInformation.getNation());
						if (ll2Num.containsKey(ll)) {
							ll2Num.put(ll, ((Integer) ll2Num.get(ll)) + 1);
						} else {
							ll2Num.put(ll, 1);
						}
						if (matrix[m][p] <= QoS_VALUE) {
							x++;
							tp[m][p] = 1;
							nC0Xl++;
							nC1Xl++;
							c = x * 1.0 / n;
							if (c >= BETA) { /* 为了把功能分离开吧 */// 达不到QoS值标准的数目占目前总数的比值大于等于我们约定的临界值β，那么显然不落在c1中。
								nC1Xl--;
							} else {
								nC0Xl--;
							}
						}
						if (c >= BETA) { /* 为了把功能分离开吧 */
							if (ll2C0.containsKey(ll)) {
								ll2C0.put(ll, ((Integer) ll2C0.get(ll)) + 1);
							} else {
								ll2C0.put(ll, 1);
							}
							nC0++;
						} else {
							if (ll2C1.containsKey(ll)) {
								ll2C1.put(ll, ((Integer) ll2C1.get(ll)) + 1);
							} else {
								ll2C1.put(ll, 1);
							}
							nC1++;
						}
					}
				}
			}
		}
		System.out.println("BETA:" + (x * 1.0 / n));
		/******** yiyi hezai *************/

		long end = System.currentTimeMillis();
		System.out.println("End to traversalMatrix, takes"
				+ new TFIDF_ComputeTime().computeTime(begin, end) + "ms...");
	}

	public static double computePro_C0(double[][] tpData, int x, int n) {
		// 根据C0计算似然概率，为后面计算后验概率做准备
		double c = x * 1.0 / n;
		if (c >= BETA) {
			nC0++;
		}
		double pro_C0 = (nC0 * 1.0 + 1) / (n + 2);
		return pro_C0;
	}

	public static double computePro_C1(double[][] tpData, int x, int n) {
		// 根据C1计算似然概率，为后面计算后验概率做准备
		double c = x * 1.0 / n;
		if (c < BETA) {
			nC1++;
		}
		double pro_C1 = (nC1 * 1.0 + 1) / (n + 2);
		return pro_C1;
	}

	public static double computeAftPro_C0(double plC0, int a, int b,
			ArrayList<TFIDF_UserBean> userList,
			ArrayList<TFIDF_WebServiceBean> webServiceList,
			HashMap<HashMap<String, String>, Double> ll2Wi_C0,
			double[][] tpData, int x, int n, int[][] tp) {

		double pro_C0 = computePro_C0(tpData, x, n);

		prePro_C0 = prePro_C0
				+ new TFIDF_ComputePrePro_C0().computePrePro_CX(plC0, a, b,
						userList, webServiceList, ll2Wi_C0, tp);

		double pC0X = new TFIDF_ComputePCiX().computePCiX(pro_C0, prePro_C0);
		return pC0X;

	}

	public static double computeAftPro_C1(double plC1, int a, int b,
			ArrayList<TFIDF_UserBean> userList,
			ArrayList<TFIDF_WebServiceBean> webServiceList,
			HashMap<HashMap<String, String>, Double> ll2Wi_C1,
			double[][] tpData, int x, int n, int[][] tp) {

		double pro_C1 = computePro_C1(tpData, x, n);

		prePro_C1 = prePro_C1
				+ new TFIDF_ComputePrePro_C1().computePrePro_CX(plC1, a, b,
						userList, webServiceList, ll2Wi_C1, tp);

		double pC1X = new TFIDF_ComputePCiX().computePCiX(pro_C1, prePro_C1);
		return pC1X;
	}

}
