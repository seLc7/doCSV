import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ��¼ʱ����ڵĽڵ����� ������ÿСʱ�ڵķ��Ͷ��Ŵ���
 * 
 * @author Cheng
 * 
 */
public class Time2TRate {
	public static void main(String[] args) {

		BufferedReader br = null;
		String url = "E://a.csv";
		String dateHisSubStr = ""; // ��ʷ����
		String dateCurSubStr = ""; // ��ǰ����
		String timeSubStr = ""; // ʱ���Ӵ�
		String hourHisSubStr = ""; // ��ʷСʱ��
		String hourCurSubStr = ""; // ��ǰСʱ
		int i = 1; // ������
		List list = new ArrayList();
		try {
			File file = new File(url);
			br = new BufferedReader(new FileReader(file));

			String line = "";
			line = br.readLine(); // ����һ������
			String firstDateSS = line.substring(0, 9);
			String firstTimeSS = line.substring(10);
			String[] firstArray = firstTimeSS.split(":");
			String firstHHSS = firstArray[0];

			dateHisSubStr = firstDateSS;
			hourHisSubStr = firstHHSS;
			while ((line = br.readLine()) != null) {
				dateCurSubStr = line.substring(0, 9);
				timeSubStr = line.substring(10);
				String[] timeArray = timeSubStr.split(":");
				hourCurSubStr = timeArray[0];
				// System.out.println(hourCurSubStr);

				if (hourCurSubStr.equals(hourHisSubStr)) { // Сʱ�����
					i++; // ������1
					hourHisSubStr = hourCurSubStr; // ��¼����
					dateHisSubStr = dateCurSubStr;
				} else {
					// Сʱ�����ȣ���¼����
					String[] node = {
							dateHisSubStr + " " + hourHisSubStr + ":00",
							String.valueOf(i) };
					list.add(node);
					// System.out.println(node[0]+" "+node[1]);
					hourHisSubStr = hourCurSubStr; // ��¼����
					dateHisSubStr = dateCurSubStr;
					i = 1; // ����������Ϊ1
				}// �����һ�������ݣ������ڴ˺����Current����
				br.close();
			}
		} catch (FileNotFoundException e) {
			// ����File��������ʱ���쳣
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
