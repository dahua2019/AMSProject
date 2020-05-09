public class Text2 {
	public static void main(String[] args) {
		//冒泡排序
		int[] nums = {1,5,3,2,9,8,10};
		//双循环
		for (int i = 0; i < nums.length-1; i ++) {
			for (int j = 0; j < nums.length-1-i; j++) {
				if (nums[j] < nums[j + 1]) {
					//将 nums[j] 赋值给 item
					int item = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = item;
				}
			}
		}

		//从大到小排序
		for (int i = 0; i < nums.length; i++) {

			System.out.print(nums[i] + " ");
		}
	}
}
