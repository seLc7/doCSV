import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 读取call表生成Node表
 * Call表包含：A号，B号，出入度标签
 * Node表包含：A号，入度，出度，入度总数，出度总数
 * @author Cheng
 *
 */
public class CallToCNode {
	static String url = "E:\\Call_sortA.csv";

	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		ArrayList nodeList = new ArrayList();
		int degree3 = 0, degree5 = 0, degree3sum = 0, degree5sum = 0;
		try { // read
			File csv = new File(url); // CSV文件

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				String temp[] = line.split(",");
				al.add(temp);

			}
			br.close();
			
			String[] first = (String[]) al.get(0); // 第一行数据
			if (first[2].equals("3")){
				degree3sum++;
				degree3++;
			}
				
			if (first[2].equals("5")){
				degree5sum++;
				degree5++;
			}
				
			for (int i = 1; i < al.size(); i++) {

				String[] history = (String[]) al.get(i - 1);
				String[] current = (String[]) al.get(i);
				if (current[0].equals(history[0])) { // a number 相等的情况下
					if (current[1].equals(history[1])) { // b number 相等的情况下
						if (current[2].equals("3")&&history[2].equals("3")) // 四种情况对应处理方法
							degree3sum++;
						if(current[2].equals("3")&&history[2].equals("5")){
							degree3++;
							degree3sum++;
						}
						if (current[2].equals("5")&&history[2].equals("5"))
							degree5sum++;
						if(current[2].equals("5")&&history[2].equals("3")){
							degree5++;
							degree5sum++;
						}
					}
					if (!current[1].equals(history[1])) { // b number 不相等
						if (current[2].equals("3")) {
							degree3++;
							degree3sum++;
						}
						if (current[2].equals("5")) {
							degree5++;
							degree5sum++;
						}
					}
				}
				if (!current[0].equals(history[0])) { // A number 不相等
					/*if(history[2].equals("3"))
						degree3++;
					if(history[2].equals("5"))
						degree5++;*/
					String[] node = { history[0], String.valueOf(degree5), // 储存上一条记录
							String.valueOf(degree3),
							String.valueOf(degree5sum),
							String.valueOf(degree3sum) };
					nodeList.add(node);
					degree3 = 0;  // 重置
					degree3sum = 0;
					degree5 = 0;
					degree5sum = 0;
					if (current[2].equals("3")) {  // 对新数据进行入度出度检测
						degree3++;
						degree3sum++;
					}
					if (current[2].equals("5")) {
						degree5++;
						degree5sum++;
					}
					/*System.out.println("number:" + node[0]);
					System.out.println("indegree:" + node[1]);
					System.out.println("outdegree:" + node[2]);
					System.out.println("inSum:" + node[3]);
					System.out.println("outSum:" + node[4]);*/
				}
			}

			System.out.println(nodeList.size());
		

		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace();
		}

		try {
			File csv = new File("E:\\call1.csv"); // CSV文件
			// 追记模式
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			// 新增一行数据
			for(int j=0;j<nodeList.size();j++){
				String[] newLine=(String[])nodeList.get(j);
				System.out.println(newLine[0]);
				bw.newLine();
				bw.write(newLine[0] + "," + newLine[1] + "," + newLine[2]+","+newLine[3]+","+newLine[4]);
			}
			System.out.println("over");
			bw.close();
		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedWriter对象关闭时的异常
			e.printStackTrace();
		}

	}
}
