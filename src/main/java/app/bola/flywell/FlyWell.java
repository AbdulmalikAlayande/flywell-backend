package app.bola.flywell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@EnablePulsar
@EnableJpaAuditing
@SpringBootApplication
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

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FlyWell.class, args);
	}

}

/*
const reservationId = useMemo(
        () =>
            `${flightDetails.airline.substring(0, 2).toUpperCase()}${Math.floor(
                100000 + Math.random() * 900000
            )}`,
        [flightDetails.airline]
 );
*/
