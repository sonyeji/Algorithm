package Knapsack;

import java.util.LinkedList;

public class knapsack {
	int[][] item;
	int n;
	int W;
	int[][] result;
	
	public knapsack(int[][] item, int leng, int W){
		this.item = item;
		this.n = leng;
		this.W = W;
	}
	
	public int run_knapsack(){
		this.result = new int[n+1][W+1];
		
		for(int i = 0; i <= n ; i++){
			for(int w = 0; w <= W; w++){
				if(i == 0 || w == 0)
					result[i][w] = 0;
				else if(item[i-1][2] <= w)
					result[i][w] = max(item[i-1][1] + result[i-1][w-item[i-1][2]], result[i-1][w]);
				else
					result[i][w] = result[i-1][w];
			}
		}
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < W+1; j++)
				System.out.printf("%5d", result[i][j]);
			System.out.println();
		}
		return result[n][W];
	}
	
	public int max(int a, int b) {return (a>b)? a : b;}
	
	public int[] itemList(){
		LinkedList<Integer> i_list = new LinkedList<Integer>();
		int i = n, w = W;
		while(i>0 & w >0){
			if(this.result[i][w] == this.result[i-1][w])
				i--;
			else{
				i_list.add(i);
				i--;
				w -= item[i][2];
			}
		}
		int leng = i_list.size();
		int[] i_result = new int[leng];
		for(int j = 0; j < leng; j++)
			i_result[j] = i_list.pop();
		return i_result;
	}
}