import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadCSV {
	static String url = "E:\\Call_sortA.csv";

	public static void main(String[] args) {
		int i = 0,j=0;
		String array[][] ;
		try {
			File csv = new File(url); // CSV�ļ�

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// ��ȡֱ�����һ��
			String line = "";
			while ((line = br.readLine()) != null) {
				// ��һ�����ݷָ�ɶ���ֶ�
				StringTokenizer st = new StringTokenizer(line, ",");

				while (st.hasMoreTokens()) {
					// ÿһ�еĶ���ֶ���TAB������ʾ
					System.out.print(st.nextToken() + "/t");
					array[i][j]=st.nextToken();
					j=j+1;
				}
				System.out.println(i++);
				i=i+1;
				System.out.print(array);
			}
			br.close();

		} catch (FileNotFoundException e) {
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			// ����BufferedReader����ر�ʱ���쳣
			e.printStackTrace();
		}
	}
}