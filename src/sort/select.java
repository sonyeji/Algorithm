package sort;

public class select {
	public static int[] selectionsort(int[] input){
		int[] input_data = input;
		int min, min_index;
		for(int i = 0; i < input_data.length - 1; i++){
			min = input_data[i];
			min_index = i;
			for(int j = i + 1; j < input_data.length; j++){
				if(min > input_data[j]){
					min = input_data[j];
					min_index = j;
				}
			}
			int t = min;
			input_data[min_index] = input_data[i];
			input_data[i] = t;
		}
		return input_data;
	}
}
