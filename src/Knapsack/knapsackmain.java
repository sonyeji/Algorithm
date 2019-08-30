package Knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class knapsackmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fr;
		int[] input = null;
		LinkedList<int[]> il = new LinkedList<int[]>();
		int[][] item;
		try{
			fr = new FileReader("C:/Users/ths33/workspace/algo/src/hw11_knapsack/data11.txt");
			BufferedReader br = new BufferedReader(fr);
			while(true){
				String inputString = br.readLine();
				if (inputString == null)
					break;
				String[] sp_input = inputString.split(",");
				input = new int[sp_input.length];
				for(int i = 0; i < sp_input.length; i++){
					input[i] = Integer.parseInt(sp_input[i]);
				}
				il.add(input);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		item = new int[il.size()][3];
		int leng = il.size();
		for(int i = 0; i < leng; i++){
			int[] temp = il.pop();
			item[i][0] = temp[0];
			item[i][1] = temp[1];
			item[i][2] = temp[2];
		}
		
		Scanner scan = new Scanner(System.in);
		String s;
		System.out.print("input W : ");
		s = scan.nextLine();
		int W = Integer.parseInt(s);
		
		knapsack ks = new knapsack(item, leng, W);
		int result = ks.run_knapsack();
		System.out.println("max : "+result);
		int[] res = ks.itemList();
		System.out.print("item : ");
		for(int i = 0; i < res.length; i++)
			System.out.print(res[i]+" ");
	}

}