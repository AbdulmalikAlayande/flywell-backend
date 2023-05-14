import java.util.Arrays;

public class Main {
		public int[] searchRange(int[] nums, int target) {
			int[] newList = new int[2];
			int newListCounter = 0;
			for(int i = 0; i < nums.length; i++) {
				if (target == nums[i]){
					newList[newListCounter] = i;
					newListCounter++;
				}
			}
			return newList;
		}
		
		
	public static void main(String[] args) {
		System.out.println("Hello world!");
		Main main = new Main();
		int[] nums = {4, 7, 8, 8, 9, 10};
		System.out.println(Arrays.toString(main.searchRange(nums, 2)));
	}
}