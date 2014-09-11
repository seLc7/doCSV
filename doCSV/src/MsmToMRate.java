import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MsmToMRate {
	static String url = "E:\\Call_sortA.csv";

	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		ArrayList nodeList = new ArrayList();
		int degree3 = 0, degree5 = 0;
		
		try { // read
			File csv = new File(url); // CSV文件

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				String temp[] = line.split(",");
				al.add(temp);

			}

			for (int i = 1; i < al.size(); i++) {

				String[] history = (String[]) al.get(i - 1);

				System.out.println("history" + history[1]);
				String[] current = (String[]) al.get(i);
				System.out.println("current" + current[1]);
				if (current[1].equals(history[1])) { // b number equal
					if (current[2].equals("8"))
						degree3 = degree3 + 1;
					if (current[2].equals("7"))
						degree5 = degree5 + 1;

				}
				if (!current[1].equals(history[1])) { // b number is not
														// equal
					if (history[2].equals("8"))
						degree3++;
					if (history[2].equals("7"))
						degree5++;
					String[] a = { history[0], history[1],
							String.valueOf(degree5), String.valueOf(degree3) };
					nodeList.add(a);
					degree3 = 0;
					degree5 = 0;

				}

			}

			/*
			 * System.out.println(nodeList.size()); String[] b = (String[])
			 * nodeList.get(0);
			 * 
			 * System.out.println(b[0] + " " + b[1] + " " + b[2] + " " + b[3] );
			 */
			br.close();

		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace();
		}

		try { // write
			File csv = new File("E:\\writers2.csv"); // CSV文件
			// 追记模式
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			// 新增一行数据
			for (int j = 0; j < nodeList.size(); j++) {
				String[] newLine = (String[]) nodeList.get(j);
				// System.out.println(newLine[0]);
				bw.newLine();
				bw.write(newLine[0] + "," + newLine[1] + "," + newLine[2] + ","
						+ newLine[3]);
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
