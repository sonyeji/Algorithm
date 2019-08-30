package karatsuba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class karatsuba_main {
	public static void main(String[] args) throws IOException {
		long result = 0;
		
		System.out.println("input x, y : ex)123,345");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] sp_input = s.split(",");
		long x = Long.parseLong(sp_input[0]);
		long y = Long.parseLong(sp_input[1]);
		if(x < y) 
			result = karatsuba(sp_input[0], sp_input[1]);
		else
			result = karatsuba(sp_input[1], sp_input[2]);
		System.out.println("result = " + result);
	}
	
	public static long karatsuba(String inputx, String inputy){
		int threshold = 3;
		long result = 0;
		
		if(inputx.length() <= threshold && inputy.length() <= threshold){
				long x = Long.parseLong(inputx);
				long y = Long.parseLong(inputy);
				result = x * y;
		}
		else if(inputx.length() <= threshold && inputy.length() > threshold){
			long x = Long.parseLong(inputx);
			int m = inputy.length()/2;
			int ym;
			if(inputy.length() % 2 == 1)
				ym = inputy.length()/2 + 1;
			else
				ym = inputy.length()/2;
			String string_y1 = inputy.substring(0, ym);
			String string_y0 = inputy.substring(ym);
			long y1 = Long.parseLong(string_y1);
			long y0 = Long.parseLong(string_y0);
			long z1 = karatsuba(inputx, string_y1);
			long z0 = karatsuba(inputx, string_y0);
			
			for(int i = 0; i < m; i++)
				z1 *= 10;
			
			result = z1 + z0;
		}
		else{
			int m = inputy.length()/2;
			int ym;
			if(inputy.length() % 2 == 1)
				ym = inputy.length()/2 + 1;
			else
				ym = inputy.length()/2;
			if(ym > inputx.length()){
				long x = Long.parseLong(inputx);
				long y = Long.parseLong(inputy);
				result = x * y;
				return result;
			}
			String string_x0 = inputx.substring(m);
			String string_x1 = inputx.substring(0, m);
			String string_y0 = inputy.substring(ym);
			String string_y1 = inputy.substring(0, ym);
			
			long x1 = Long.parseLong(string_x1);
			long x0 = Long.parseLong(string_x0);
			long y1 = Long.parseLong(string_y1);
			long y0 = Long.parseLong(string_y0);
			
			long z0 = karatsuba(string_x0, string_y0);
			long z2 = karatsuba(string_x1, string_y1);
			long z1_x = x1+x0;
			long z1_y = y1+y0;
			long z1 = karatsuba(String.valueOf(z1_x), String.valueOf(z1_y))-z2-z0;
			
			for(int i = 0; i < m; i++)
				z1 *= 10;
			for(int i = 0; i < 2*m; i++)
				z2 *= 10;
			result = z2+z1+z0;
		}
		
		return result;
	}
}