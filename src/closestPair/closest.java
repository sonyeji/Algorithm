package closestPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class closest {
	public static void main(String[] args) {
		FileReader fr;
		double result;
		LinkedList loc = new LinkedList();
		LinkedList location = null;
		LinkedList[] input_loc = null;
		double[] re = new double[3];
		try{
			fr = new FileReader("C:/Users/ths33/workspace/algo/src/hw05/data05.txt"); //해당 위치에서 data를 읽어옴
			BufferedReader br = new BufferedReader(fr);
			while(true){
				String inputString = br.readLine();
				if (inputString == null)
					break;
				String[] sp_input = inputString.split(","); //,단위로 끊어서 string 배열에 각각 값을 저장
				location = new LinkedList();
				for(int i = 0; i < sp_input.length; i++){
					location.push(Double.parseDouble(sp_input[i]));
				}
				loc.push(location);
			}
			input_loc = new LinkedList[loc.size()];
			for(int i = 0; i < input_loc.length; i++){
				input_loc[i] = (LinkedList)loc.pop();
				//input_loc[i].getFirst : y축, getLast : x축
				//System.out.println(input_loc[i].getLast());
				//System.out.println(input_loc[i].getFirst());
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		xquicksort(input_loc, 0, input_loc.length);
		re = daq(input_loc, 0, input_loc.length - 1);
		LinkedList point1 = input_loc[(int)re[0]];
		LinkedList point2 = input_loc[(int)re[1]];
		if(re[0] > re[1])
			System.out.println("closest pair ("+point1.getLast() +", " + point1.getFirst() + "), (" + point2.getLast() +", " + point2.getFirst() + ") length = " + re[2]);
		else
			System.out.println("closest pair ("+point2.getLast() +", " + point2.getFirst() + "), (" + point1.getLast() +", " + point1.getFirst() + ") length = " + re[2]);
	}
	
	public static double[] daq(LinkedList[] locs,int l, int r){
		double min = 0;
		double[] remin = {0, 0, 0};//l, r, min
		
		if((r - l)+1 <= 3){
			min = mincalc(locs, l, r);
			remin[0] = l;
			remin[1] = r;
			remin[2] = min;
		}
		else{
			int mid = (l + r)/2;
			double[] lremin = daq(locs, l, mid);
			double[] rremin = daq(locs, mid+1, r);
			double lmin = lremin[2];
			double rmin = rremin[2];
			
			double[] rewindow = new double[3];
			if (lmin <= rmin) rewindow = lremin;
			else rewindow = rremin;
			double window = rewindow[2];
			double xmid = ((double)locs[mid].getLast()+(double)locs[mid+1].getLast())/2;
			double[] rewmin = cal_window(locs, xmid, window, (double)locs[l].getLast(), (double)locs[r].getLast());
			double wmin = rewmin[2];
			if(wmin == 0)
				remin = rewindow;
			else{
				if(wmin > window)
					remin = rewindow;
				else
					remin = rewmin;
			}
		}
		return remin;
	}
	
	public static double[] cal_window(LinkedList[] locs, double xmid, double window, double lmax, double rmax){
		double lwin = xmid - window;
		if (lwin < lmax) lwin = lmax;
		double rwin = xmid + window;
		if (rwin > rmax) rwin = rmax;
		double[] remin = {0,0,0};
		LinkedList[] winlocs = null;
		LinkedList index = new LinkedList();
		for(int i = 0; i < locs.length; i++){
			if((double)locs[i].getLast() >= lwin && (double)locs[i].getLast() <= rwin){
				index.push(i);
			}
		}
		if(index.size() >= 2){
			winlocs = new LinkedList[index.size()];
			int size = index.size();
			int[] iindex = new int[size];
			for(int i = 0; i < size; i++){
				iindex[i] = (int)index.removeLast();
				winlocs[i] = locs[iindex[i]];
			}
			yquicksort(winlocs, 0, winlocs.length);
			int[] windex = new int[size];
			for(int i = 0; i < size; i++){
				for(int j = iindex[0]; j <= iindex[size-1]; j++){
					if(winlocs[i] == locs[j]){
						windex[i] = j;
					}
				}
			}
			remin = winmin(winlocs, 0, winlocs.length-1, window);
			remin[0] = windex[(int)remin[0]];
			remin[1] = windex[(int)remin[1]];
		}
		return remin;
	}
	
	public static double[] winmin(LinkedList[] locs, int l, int r, double window){
		double min;
		double[] remin = {0,0,0};
		if(locs.length == 2){
			min = mincalc(locs, 0, 1);
			remin[0] = 0;
			remin[1] = 1;
			remin[2] = min;
			return remin;
		}
		else{
			double length;
			min = calc(locs[0], locs[1]);
			remin[0] = 0;
			remin[1] = 1;
			remin[2] = min;
			for(int i = 0; i < locs.length; i++){
				for(int j = i+1; j < 12; j++){
					if(j >= locs.length) break;
					length = calc(locs[i], locs[j]);
					if(min > length) {
						min = length;
						remin[0] = i;
						remin[1] = j;
						remin[2] = min;
					}
				}
			}
			return remin;
		}
	}
	
	public static void xquicksort(LinkedList[] locs, int l, int r){
		if(r-l < 2) return;
		int j = xpartition(locs, l, r);
		xquicksort(locs, l, j);
		xquicksort(locs, j+1, r);
	}
	
	public static int xpartition(LinkedList[] locs, int l, int r){
		LinkedList pivot = locs[l];
		int i = l, j = r;
		while(i < j){
			while(j > i && (double)locs[--j].getLast() >= (double)pivot.getLast());
			if(j > i) locs[i] = locs[j];
			while(i < j && (double)locs[++i].getLast() <= (double)pivot.getLast());
			if(i < j) locs[j] = locs[i];
		}
		locs[j] = pivot;
		return j;
	}
	
	public static void yquicksort(LinkedList[] locs, int l, int r){
		if(r-l < 2) return;
		int j = ypartition(locs, l, r);
		yquicksort(locs, l, j);
		yquicksort(locs, j+1, r);
	}
	
	public static int ypartition(LinkedList[] locs, int l, int r){
		LinkedList pivot = locs[l];
		int i = l, j = r;
		while(i < j){
			while(j > i && (double)locs[--j].getFirst() >= (double)pivot.getFirst());
			if(j > i) locs[i] = locs[j];
			while(i < j && (double)locs[++i].getFirst() <= (double)pivot.getFirst());
			if(i < j) locs[j] = locs[i];
		}
		locs[j] = pivot;
		return j;
	}
	
	public static double mincalc(LinkedList[] locs, int l, int r){
		double d1, d2, d3;
		if(r - l + 1 == 3){
			d1 = calc(locs[l], locs[l+1]);
			d2 = calc(locs[l], locs[r]);
			d3 = calc(locs[l+1], locs[r]);
			double min = d1;
			if(d2 < min)
				min = d2;
			if(d3 < min)
				min = d3;
			return min;
		}
		else{
			d1 = calc(locs[l], locs[r]);
			return d1;
		}
	}
	
	public static double calc(LinkedList p1, LinkedList p2){
		double x1 = (double) p1.getLast();
		double y1 = (double) p1.getFirst();
		double x2 = (double) p2.getLast();
		double y2 = (double) p2.getFirst();
		return Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
	}
}