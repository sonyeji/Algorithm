package Sequence_Alignment;

import java.util.Scanner;

public class sequencemain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String inputx, inputy;
		System.out.print("input x : ");
		inputx = scan.nextLine();
		System.out.print("input y : ");
		inputy = scan.nextLine();
		int lx, ly;
		char[] x, y;
		if(inputx.length() >= inputy.length()){
			lx = inputx.length();
			ly = inputy.length();
			x = inputx.toCharArray();
			y = inputy.toCharArray();
		}else{
			lx = inputy.length();
			ly = inputx.length();
			x = inputy.toCharArray();
			y = inputx.toCharArray();
		}
		
		int[][] a = new int[ly+1][lx+1];
		int result = sequenceAlignment(lx, ly, x, y, a);
		System.out.println("점수 : " + result);
		String strx = "";
		String stry = "";
		select(ly, lx, x, y, a, strx, stry);
	}
	
	public static int sequenceAlignment(int lx, int ly, char[] x, char[] y, int[][] a){
		int q = -2;
		for(int i = 0; i <= ly; i++){
			a[i][0] = i*q;
			print1(a, lx, ly);
			System.out.println();
		}
		for(int j = 0; j <= lx; j++){
			a[0][j] = j*q;
			print2(a, lx, ly);
			System.out.println();
		}
		for(int i = 1; i <= ly; i++){
			for(int j = 1; j <= lx; j++){
				a[i][j] = max(a[i][j-1]+q, a[i-1][j-1]+p(i-1, j-1, x, y), a[i-1][j]+q);
				print(i, j, a, lx, ly);
				System.out.println();
			}
		}
		return a[ly][lx];
	}
	
	public static void print1(int[][] a, int lx, int ly){
		for(int i = 0; i <= ly; i++){
			for(int j = 0; j <= lx; j++){
				if(i == 0 && j == 0){
					System.out.printf("%4d", a[i][j]);
					continue;
				}
				if(j == 0 && a[i][j] != 0){
					System.out.printf("%4d", a[i][j]);
				}
				else
					System.out.print("   -");
			}
			System.out.println();
		}
	}
	
	public static void print2(int[][] a, int lx, int ly){
		for(int i = 0; i <= ly; i++){
			for(int j = 0; j <= lx; j++){
				if(i == 0 && j == 0){
					System.out.printf("%4d", a[i][j]);
					continue;
				}
				if(i != 0 && j != 0)
					System.out.print("   -");
				else{
					if(a[i][j] == 0){
						System.out.print("   -");
						continue;
					}
					System.out.printf("%4d", a[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public static void print(int k, int l, int[][] a, int lx, int ly){
		for(int i = 0; i <= ly; i++){
			for(int j = 0; j <= lx; j++){
				if((i>k || (i==k && j>l)) && a[i][j] == 0)
					System.out.print("   -");
				else
					System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int max(int x, int y, int z){
		int max = x;
		if (max < y)
			max = y;
		if (max < z)
			max = z;
		return max;
	}
	
	public static int p(int i, int j, char[] x, char[] y){
		if(x[j] == y[i])
			return 1;
		else
			return -1;
	}
	
	public static void select(int i, int j, char[] x, char[] y, int[][] a, String strx, String stry){
		if(i == 0){
			System.out.println("조합 : ");
			for(int k = 0; k < x.length; k++){
				System.out.print(strx.charAt(k)+" ");
			}
			System.out.println();
			for(int k = 0; k < stry.length(); k++){
				System.out.print(stry.charAt(k)+" ");
			}
			System.out.println();
			return;
		}
		
		int q = -2;
		int left = a[i][j-1]+q;
		int cross = a[i-1][j-1]+p(i-1, j-1, x, y);
		int up = a[i-1][j]+q;
		
		int route = max(left, cross, up);

		if(route == left){
			if(left == up && left == cross){
				String strx2 = x[j-1] + strx;
				String stry2 = y[i-1] + stry;
				select(i-1, j-1, x, y, a, strx2, stry2);
				String strx3 = "-" + strx;
				String stry3 = y[i-1] + stry;
				select(i-1, j, x, y, a, strx3, stry3);
			}
			else if(left == cross){
				String strx2 = x[j-1] + strx;
				String stry2 = y[i-1] + stry;
				select(i-1, j-1, x, y, a, strx2, stry2);
			}
			else if(left == up){
				String strx2 = "-" + strx;
				String stry2 = y[i-1] + stry;
				select(i-1, j, x, y, a, strx2, stry2);
			}
			strx = x[j-1] + strx;
			stry = "-" + stry;
			select(i, j-1, x, y, a, strx, stry);
		}
		else if(route == cross){
			if(cross == up){
				String strx2 = "-" + strx;
				String stry2 = y[i-1] + stry;
				select(i-1, j, x, y, a, strx2, stry2);
			}
			strx = x[j-1] + strx;
			stry = y[i-1] + stry;
			select(i-1, j-1, x, y, a, strx, stry);
		}
		else if(route == up){
			strx = "-" + strx;
			stry = y[i-1] + stry;
			select(i-1, j, x, y, a, strx, stry);
		}
	}

}
