import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 记录时间段内的节点数。 本例是每小时内的发送短信次数
 * 
 * @author Cheng
 * 
 */
public class Time2TRate {
	public static void main(String[] args) {

		BufferedReader br = null;
		String url = "E://a.csv";
		String dateHisSubStr = ""; // 历史日期
		String dateCurSubStr = ""; // 当前日期
		String timeSubStr = ""; // 时间子串
		String hourHisSubStr = ""; // 历史小时数
		String hourCurSubStr = ""; // 当前小时
		int i = 1; // 计数器
		List list = new ArrayList();
		try {
			File file = new File(url);
			br = new BufferedReader(new FileReader(file));

			String line = "";
			line = br.readLine(); // 读第一条数据
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

				if (hourCurSubStr.equals(hourHisSubStr)) { // 小时数相等
					i++; // 记数加1
					hourHisSubStr = hourCurSubStr; // 记录下移
					dateHisSubStr = dateCurSubStr;
				} else {
					// 小时数不等，记录数据
					String[] node = {
							dateHisSubStr + " " + hourHisSubStr + ":00",
							String.valueOf(i) };
					list.add(node);
					// System.out.println(node[0]+" "+node[1]);
					hourHisSubStr = hourCurSubStr; // 记录下移
					dateHisSubStr = dateCurSubStr;
					i = 1; // 计数器重置为1
				}// 少最后一条的数据，可以在此后添加Current数据
				br.close();
			}
		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
