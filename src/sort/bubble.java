package sort;

public class bubble {

	public static int[] bubblesort(int[] input){
		int[] input_data = input;
		int i, j, k;
		
		for(i = 0; i < input_data.length-1; i++){
			for(j = i; j < input_data.length-1; j++){
				k = j + 1;
				if(input_data[j] > input_data[k]){
					int t = input_data[k];
					input_data[k] = input_data[j];
					input_data[j] = t;
				}
			}
		}
		return input_data;
	}
}
