package dijkstra;

import java.util.LinkedList;
import java.util.Stack;

public class Priority {
	int Int_max = 2147483647;
	int size;
	int[][] w;
	char[] vertices;
	int[] dest;
	Stack<Integer> S = new Stack<Integer>();
	heapPriorityQueue Q = new heapPriorityQueue();
	
	public Priority(int[][] w){
		this.w = w;
		this.size = w[0].length;
		
		vertices = new char[size];
		char a = 'A';
		for(int i = 0; i < size; i++){
			vertices[i] = a;
			a = (char)(a+1);
		}
		for(int i = 0; i < size; i ++){
		LinkedList<String> ll = new LinkedList<String>();
		ll.addFirst(String.valueOf(i));
		if(i == 0)	ll.addLast(String.valueOf(0));
		else	ll.addLast(String.valueOf(Int_max));
		Q.insert(Q, ll);
		}
		dest = new int[size];
		System.out.println("Dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.");
		
	}
	
	public void dijkstra(int s){
		if(s == size) return;
		LinkedList<String> getmax = Q.extract_max();
		dest[Integer.parseInt(getmax.getFirst())] = Integer.parseInt(getmax.getLast());
		S.push(Integer.parseInt(getmax.getFirst()));
		print(s);
		int d;
		
		for(int i = 0; i < Q.size; i++){
			LinkedList<String> geti = Q.index_get(i);
			int dv = Integer.parseInt(geti.getLast());
			int u = S.peek();
			int du = dest[u];
			int v = Integer.parseInt(geti.getFirst());
			int wuv = w[u][v];
			if(wuv == Int_max)	printat(i);
			else{
				String newdv = String.valueOf(du+wuv);
				if(du + wuv < dv){
					printchan(i, du+wuv);
					Q.increase_key(String.valueOf(i), newdv);
				}
				else	printat(i);
			}
		}
		dijkstra(s+1);
	}
	
	public void print(int i){
		System.out.println("\n===============================");
		int sindex = S.peek();
		int slen = dest[sindex];
		char indexalpha = vertices[sindex];
		System.out.println("S["+i+"] : d["+indexalpha+"] = "+slen);
		System.out.println("-------------------------------");
	}
	
	public void printat(int i){
		LinkedList<String> get = Q.index_get(i);
		char indexalpha = vertices[Integer.parseInt(get.peekFirst())];
		int leng = Integer.parseInt(get.peekLast());
		System.out.println("Q["+(i)+"] : d["+indexalpha+"] = "+leng);
	}
	
	public void printchan(int i, int newdv){
		LinkedList<String> get = Q.index_get(i);
		char indexalpha = vertices[Integer.parseInt(get.peekFirst())];
		int leng = Integer.parseInt(get.peekLast());
		System.out.println("Q["+(i)+"] : d["+indexalpha+"] = "+leng+" -> d["+indexalpha+"] = "+newdv);
	}
}