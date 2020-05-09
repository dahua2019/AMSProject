
public class ServiceAImpl  {

		/** 人数*/
		static int n=12;
		/** 报数*/
		static int m=3;

		public static void main(String[] args) {
			/*
			 * 新建数组,下标+1表示几号人,固定不变
			 * 数组对应的值初始赋值为1
			 */
			int[] arr=new int[n];
			for (int i = 0; i < arr.length; i++) {
				arr[i]=1;
			}
			/*
			 * 假设人标号为1,2,3,4,5,6,7,8,9,10,11,12,报到3时出列
			 * 无论多少人出列,数数次数都会一直递增,只是报数1-3循环.
			 * 逻辑:遍历数组一次:当数数次数为3的倍数时,输出下标+1即为对应人编号,同时将对应的值改成o
			 * 遍历数组二次:只有当值不为0时,数数次数才算有效
			 * 无限循环:直到数数次数到达12*3时即n*m时,退出循环
			 */
			int count=0;//该变量记数录数次数,理论最多数n*m次
			while(true) {
				for (int i = 0; i < arr.length; i++) {
					if(arr[i]!=0) {
						count++;
						if(count%m==0) {
							System.out.print(i+1+" ");
							arr[i]=0;
						}
						if(count==n*m) {//实际当最后第二个出圈,谁最后一个出圈的也就出来,暂不考虑
							return;
						}
					}
				}
			}
		}

}
