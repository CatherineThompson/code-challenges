package DP;

import java.util.*;
import java.math.*;

public class CoinChangeOptimized {

	public static void main(String[] args) {
		CoinSet cs1 = new CoinSet(4, new int[]{1, 2, 3});
		cs1.countCombos();
		
		CoinSet cs2 = new CoinSet(10, new int[]{2, 5, 3, 6});
		cs2.countCombos();
		
		CoinSet cs3 = new CoinSet(250, new int[]{41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11});
		cs3.countCombos();
		
	}
	
	private static class CoinSet {
		private int n;
		private int[] coins;
		private long[] combosArray;
		
		public CoinSet(int n, int[] coins) {
			this.n = n;
			this.coins = coins.clone();
			combosArray = new long[n + 1];
			// base case
			combosArray[0] = 1;
		}
		
		public void countCombos() {
			for(int coin : coins) {
				for(int amount = 0; amount < combosArray.length; amount++) {
					if(amount >= coin) {
						combosArray[amount] += combosArray[amount - coin];
					}
				}
			}
			System.out.println(combosArray[n]);
			
		}
		
	}

}
