package Permutations;

public class Permutations {

	public static void main(String[] args) {
		Permutation p = new Permutation();
		p.permutation("cake");
	}
	
	private static class Permutation {
		public void permutation(String str) {
			permutation(str, "");
		}
		
		private void permutation(String str, String prefix) {
			System.out.println("str: " + str);
			System.out.println("pre: " + prefix);

			if (str.length() == 0) {
				//System.out.println("end: " + prefix);
			} else {
				for (int i = 0; i < str.length(); i++) {
					String rem = str.substring(0, i) + str.substring(i + 1);
					permutation(rem, prefix + str.charAt(i));
				}
			}
		} 
	}

}
