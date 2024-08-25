package app.bola.flywell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;


@SpringBootApplication
@EnablePulsar
public class FlyWell {

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
		SpringApplication.run(FlyWell.class, args);
	}
	
}