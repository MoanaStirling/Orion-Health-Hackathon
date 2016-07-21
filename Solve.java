package solve;

import java.util.*;


public class Solve {
	public static void main(String [ ] args){
		for(int i=2; i<=9; i++){
			System.out.println("Combinations for " + Integer.toString(i) + ":");
			printList(solve(i));
		}
	}
	
	private static List<String> solve(int n) {
		List<String> list = new ArrayList<String>();
		String[] operatorSymbols = {"+", "-", null};
		int[][] operatorArray = generateOperatorArray(n-1);
		
		for(int i = 0; i < operatorArray.length; i++){
			int sum = 0;
			int number = 1;
			for(int j = 0; j < operatorArray[i].length; j++){	
				if(operatorArray[i][j] == 0){
					sum += number;
					number = j+2;
				} else if(operatorArray[i][j] == 1){
					sum += number;
					number = -(j+2);
				} else if(operatorArray[i][j] == 2){
					if(number > 0){
						number = number * 10 + j+2;
					} else{
						number = number * 10 - (j+2);
					}
				}
			}
			sum += number;
						
			if (sum == 100){
				String sequence = "1";
				for(int k=0; k<operatorArray[i].length; k++){
					if(operatorSymbols[operatorArray[i][k]] != null){
						sequence += operatorSymbols[operatorArray[i][k]];
					}
						sequence += Integer.toString(k+2);
				}
				list.add(sequence);
			}
		}
		return list;
	}
	
	private static int[][] generateOperatorArray(int n) {
		int[][] operatorArray = new int[(int) Math.pow(3, n)][n];
		if(n == 1){
			operatorArray[0][0] = 0;
			operatorArray[1][0] = 1;
			operatorArray[2][0] = 2;
		}
		else{
			int[][] lesserArray = generateOperatorArray(n-1);
			int j = 0;
			for(int i = 0; i < Math.pow(3, n-1); i++){
				operatorArray[i][0] = 0;
				for(int k = 1; k < n; k++){
					operatorArray[i][k] = lesserArray[j][k-1];
				}
				j++;
			}
			j=0;
			for(int i = (int)Math.pow(3, n-1); i < 2 * Math.pow(3, n-1); i++){
				operatorArray[i][0] = 1;
				for(int k = 1; k < n; k++){
					operatorArray[i][k] = lesserArray[j][k-1];
				}
				j++;
			}
			j=0;
			for(int i = 2*(int)Math.pow(3, n-1); i < Math.pow(3, n); i++){
				operatorArray[i][0] = 2;
				for(int k = 1; k < n; k++){
					operatorArray[i][k] = lesserArray[j][k-1];
				}
				j++;
			}
		}
		
		return operatorArray;
	}

	private static void printList(List<String> list){
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
}


