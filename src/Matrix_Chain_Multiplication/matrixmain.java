package Matrix_Chain_Multiplication;

import java.util.Scanner;

public class matrixmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix_num;
		int[][] matrix_size;
		int[] p;
		int n;
		int[][] m;
		int[][] s;
		
		Scanner scan = new Scanner(System.in);
		String input;
		System.out.print("input matrix num : ");
		input = scan.nextLine();
		matrix_num = Integer.parseInt(input);
		matrix_size = new int[matrix_num][2];
		p = new int[matrix_num+1];
		System.out.println("input matrix size\nex)A(1) = 30,15");
		for(int i = 0; i < matrix_num; i++){
			System.out.print("A("+(i+1)+") = ");
			input = scan.nextLine();
			String[] s_input = input.split(",");
			matrix_size[i][0] = Integer.parseInt(s_input[0]);
			matrix_size[i][1] = Integer.parseInt(s_input[1]);
		};
		for(int i = 0; i < matrix_num; i++){
			p[i] = matrix_size[i][0];
		}
		p[matrix_num] = matrix_size[matrix_num-1][1];
		n = p.length-1;
		m = new int[n][n];
		s = new int[n][n];
		matrix_chain_order(p, n, m, s);
		System.out.println("--------------------------------------------");
		for(int i = 0; i < n; i++){
			for(int j =0; j< n; j++)
				System.out.printf("%8d",m[i][j]);
			System.out.println();
		}
		System.out.println("--------------------------------------------");
		for(int i = 0; i < n; i++){
			for(int j =0; j< n; j++)
				System.out.printf("%8d",s[i][j]);
			System.out.println();
		}
		System.out.println("--------------------------------------------");
		System.out.println("Optimal solution : " + m[0][n-1]);
		print_optimal_parens(s, 0, n-1);
	}
	
	public static void matrix_chain_order(int[] p, int n, int[][] m, int[][] s){
		for(int i = 0; i < n; i++){
			m[i][i] = 0;
			s[i][i] = -1;
		}
		for(int i = 1; i < n; i++){
			for(int j = 0; j < i; j++){
				m[i][j] = -1;
				s[i][j] = -1;
			}
		}
		
		for(int l = 2; l <= n; l++){
			for(int i = 1; i <= n-l+1; i++){
				int j = i+l-1;
				m[i-1][j-1] = 2147483647;
				for(int k = i; k < j; k++){
					int q = m[i-1][k-1] + m[k][j-1]+(p[i-1]*p[k]*p[j]);
					if(q < m[i-1][j-1]){
						m[i-1][j-1] = q;
						s[i-1][j-1] = k-1;
					}
				}
			}
		}
	}
	
	public static void print_optimal_parens(int[][] s, int i, int j){
		if(i == j){
			System.out.print("A"+(i+1));
		}
		else{
			System.out.print("(");
			print_optimal_parens(s, i, s[i][j]);
			print_optimal_parens(s,s[i][j]+1, j);
			System.out.print(")");
		}
	}

}