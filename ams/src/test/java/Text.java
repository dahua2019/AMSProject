public class Text {
	public static void main(String[] args) {
		//输出三角形的 * 图形
		for (int i = 1; i <= 5; i++) {
			//输出每行空格
			for (int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= 2*i-1; j++) {
				//输出每行*
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
