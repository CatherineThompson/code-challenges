package DP;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CoinChange {

	public static void main(String[] args) {
		int n = 4;
		int[] coins = {1, 2, 3};
		
		CoinSet cs1 = new CoinSet(n, coins);
		System.out.println("Final Count: " + cs1.countCombos(0, -1));
		
		CoinSet cs2 = new CoinSet(10, new int[]{2, 5, 3, 6});
		System.out.println("Final Count: " + cs2.countCombos(0, -1));
					
	}
	
	private static class CoinSet {
		private int n;
		private ArrayList<Integer> coins;
		
		public CoinSet(int n, int[] coins) {
			this.n = n;
			int[] newCoinsArray = coins.clone();
			Arrays.sort(newCoinsArray);
			this.coins = new ArrayList<Integer>(newCoinsArray.length);
			for (int i = 0; i < newCoinsArray.length; i++) {
				  this.coins.add(Integer.valueOf(newCoinsArray[i]));
			}
		}
		
		public int countCombos(int currentValue, int currentCoin) {
			int sum = 0;
			
			if(currentValue == n) {
				return 1;
			} else if(currentValue > n) {
				return 0;
			}
			
			if(currentValue == 0) {
				for(int coinValue : coins) {
					if (currentValue + coinValue <= n) {
						sum += countCombos(currentValue + coinValue, coinValue);
					}
				}
			} else {
				for(int i = coins.indexOf(currentCoin); i < coins.size(); i++) {
					if (currentValue + coins.get(i) <= n) {
						sum += countCombos(currentValue + coins.get(i), coins.get(i));
					}
				}
				
			}
			
			return sum;
		}
		
	}

}
