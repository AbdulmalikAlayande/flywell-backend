package com.example.airlinereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AirlineReservation {
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
		SpringApplication.run(AirlineReservation.class, args);
	}
	
}