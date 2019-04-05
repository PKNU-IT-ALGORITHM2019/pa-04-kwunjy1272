import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.io.*;


public class pa04 {
	static data[] lowData = new data[15789];
	static int i = 0;
	public static void main(String[] args) {
		action();
	}
	private static void action() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("$ ");
			String line = sc.nextLine();
			String[] command = line.split("\\s");
			if (command[0].equals("read")) {
				read(command[1]);
			} else if (command[0].equals("sort")) {
				if (command[1].equals("-t")) {
					timeSort();
				}
				else if(command[1].equals("-ip")){
					ipSort();
				}
			}
			else if (command[0].equals("print"))
				print();
			else if (command[0].equals("exit"))
				break;
		}
	}
	private static void read(String fileName) {
		try {
			File csv = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] d = line.split(",");
				d[1] = d[1].substring(1, d[1].length());
				//month�� ���ϰ� ���ϱ� ���� ���ڷ� ����
				d[1] = d[1].replace("Jan", "01");
				d[1] = d[1].replace("Feb", "02");
				d[1] = d[1].replace("Mar", "03");
				d[1] = d[1].replace("Apr", "04");
				d[1] = d[1].replace("May", "05");
				d[1] = d[1].replace("Jun", "06");
				d[1] = d[1].replace("Jul", "07");
				d[1] = d[1].replace("Aug", "08");
				d[1] = d[1].replace("Sep", "09");
				d[1] = d[1].replace("Oct", "10");
				d[1] = d[1].replace("Nov", "11");
				d[1] = d[1].replace("Dec", "12");
				
				//�ð��񱳸� �ϱ����� ��,��,��,��,��,�� ������ ������ ����
				String []dfTime = d[1].split("/|:");
				//��,��,��
				String strYear = dfTime[2]+dfTime[1]+dfTime[0];
				//��,��,��
				String strHour = dfTime[3]+dfTime[4]+dfTime[5];		
				int year = Integer.parseInt(strYear);
				int hour = Integer.parseInt(strHour);
				lowData[i] = new data(d[0], d[1], d[2], d[3],year,hour);
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void timeSort() {
		Arrays.sort(lowData, data.timeComparator);
	}
	private static void ipSort() {
		Arrays.sort(lowData, data.ipComparator);
	}
	private static void print() {
		int j = 0;
		while (j <i) {
			System.out.println("IP: " + lowData[j].ip);
			System.out.println("Time: " + lowData[j].time);
			System.out.println("URL: " + lowData[j].url);
			System.out.println("Status: " + lowData[j].status+'\n');
			j++;
		}
	}
}
