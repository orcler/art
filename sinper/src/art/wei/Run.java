package art.wei;

import art.wei.xls.ParseExcel;

public class Run {

	public static void main(String[] args) {
		try {
			new ParseExcel().parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
