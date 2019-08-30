package inversionCount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class inversion_main {
	public static void main(String[] args) {
		FileReader fr;
		int[] input = null;
		try{
			fr = new FileReader("C:/Users/ths33/workspace/algo/src/hw07_inversion/data07.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			String[] sp_input = inputString.split(",");
			input = new int[sp_input.length];
			for(int i = 0; i < sp_input.length; i++){
				input[i] = Integer.parseInt(sp_input[i]);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		int inversion = count(input, 0, input.length);
		System.out.println("count inversion = " + inversion);
	}
	
	public static int count(int[] input, int l, int r){
		int inversion = 0;
		int l_inv = 0;
		int r_inv = 0;
		
		if(r-l < 2) return 0;
		int m = (l+r)/2;
		l_inv = count(input, l, m);
		r_inv = count(input, m, r);
		inversion = merge(input, l, m, r);
		
		inversion = inversion + l_inv + r_inv;
		
		return inversion;
	}
	
	public static int merge(int[] input, int l, int m, int r){
		int inversion = 0;
		
		if(input[m-1] <= input[m]) return inversion;
		int i = l, j = m, k = 0;
		int[] aa = new int[r-l];
		while(i < m && j < r)
			if(input[i]<input[j]) aa[k++] = input[i++];
			else{
				System.out.println("inversion ("+input[i]+","+input[j]+")");
				aa[k++] = input[j++];
				inversion++;
			}
		if(i<m)
			System.arraycopy(input, i, input, l+k, m-i);
		System.arraycopy(aa, 0, input, l, k);
		
		return inversion;
	}
}