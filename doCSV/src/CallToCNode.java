import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ��ȡcall������Node��
 * Call�������A�ţ�B�ţ�����ȱ�ǩ
 * Node�������A�ţ���ȣ����ȣ������������������
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
			File csv = new File(url); // CSV�ļ�

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// ��ȡֱ�����һ��
			String line = "";
			while ((line = br.readLine()) != null) {
				String temp[] = line.split(",");
				al.add(temp);

			}
			br.close();
			
			String[] first = (String[]) al.get(0); // ��һ������
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
				if (current[0].equals(history[0])) { // a number ��ȵ������
					if (current[1].equals(history[1])) { // b number ��ȵ������
						if (current[2].equals("3")&&history[2].equals("3")) // ���������Ӧ������
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
					if (!current[1].equals(history[1])) { // b number �����
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
				if (!current[0].equals(history[0])) { // A number �����
					/*if(history[2].equals("3"))
						degree3++;
					if(history[2].equals("5"))
						degree5++;*/
					String[] node = { history[0], String.valueOf(degree5), // ������һ����¼
							String.valueOf(degree3),
							String.valueOf(degree5sum),
							String.valueOf(degree3sum) };
					nodeList.add(node);
					degree3 = 0;  // ����
					degree3sum = 0;
					degree5 = 0;
					degree5sum = 0;
					if (current[2].equals("3")) {  // �������ݽ�����ȳ��ȼ��
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
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			// ����BufferedReader����ر�ʱ���쳣
			e.printStackTrace();
		}

		try {
			File csv = new File("E:\\call1.csv"); // CSV�ļ�
			// ׷��ģʽ
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			// ����һ������
			for(int j=0;j<nodeList.size();j++){
				String[] newLine=(String[])nodeList.get(j);
				System.out.println(newLine[0]);
				bw.newLine();
				bw.write(newLine[0] + "," + newLine[1] + "," + newLine[2]+","+newLine[3]+","+newLine[4]);
			}
			System.out.println("over");
			bw.close();
		} catch (FileNotFoundException e) {
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			// ����BufferedWriter����ر�ʱ���쳣
			e.printStackTrace();
		}

	}
}
