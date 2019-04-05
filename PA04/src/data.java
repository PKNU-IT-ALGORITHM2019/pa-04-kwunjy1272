import java.util.Comparator;

public class data {
	public String ip;
	public String time;
	public String url;
	public String status;
	public int Year;
	public int Hour;

	public data(String ip, String time, String url, String status, int Year, int Hour) {
		this.ip = ip;
		this.time = time;
		this.url = url;
		this.status = status;
		this.Year = Year;
		this.Hour = Hour;
	}
	public static Comparator<data> timeComparator = new Comparator<data>() {
		public int compare(data d1, data d2) {
			// 년월일 비교
			if (d1.Year == d2.Year) {
				return d1.Hour - d2.Hour;
			} else {
				return d1.Year - d2.Year;
			}
		}
	};
	public static Comparator<data> ipComparator = new Comparator<data>() {
		public int compare(data d1, data d2) {
			return d1.ip.compareTo(d2.ip);
		}
	};
}