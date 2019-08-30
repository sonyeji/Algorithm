package prims;

import java.util.LinkedList;

public class minheap implements Cloneable{
	Edge[] edges = new Edge[10];
	int num_vertex;
	int num_edges;
	int size = 0;
	
	public minheap(int num_vertex){
		this.num_vertex = num_vertex;
	}
	
	public minheap(int num_vertex, int num_edges, int size, Edge[] edge){
		this.edges = edge;
		this.num_vertex = num_vertex;
		this.num_edges = num_edges;
		this.size = size;
	}
	
	public void insert(Edge edge){
		int w = edge.weight;
		if(size == edges.length)
			resize();
		int i = size++;
		while(i>0){
			int j = i;
			i = (i-1)/2;
			Edge temp = edges[i];
			int tw = temp.weight;
			if(tw <= w){
				edges[j] = edge;
				return;
			}
			edges[j] = edges[i];
		}
		edges[i] = edge;
		this.num_edges++;
	}
	
	public void resize(){//heap의 사이즈를 2배로 늘려주는 함수
		Edge[] aa = new Edge[2*edges.length];
		System.arraycopy(edges, 0, aa, 0, edges.length);
		edges = aa;
	}
	
	public void build_heap(int n){//원소의 처음부터 순회하며 heapify를 진행
		int i;
		for(i = (n/2)-1; i >= 0; i--){//리프노드가 아닌 노드에 대해
			heapify(i, n);//히프화 알고리즘 적용
		}//반복문을 돌며 다음 노드로 접근
	}
	
	public void heapify(int i, int n){//히프화 알고리즘
		Edge ed = edges[i];
		while(i < n/2){
			int j = 2*i +1;
			Edge right = edges[j+1];
			Edge left = edges[j];
			if(j+1<n && right.weight < left.weight)
				j++;
			Edge temp = edges[j];
			if(temp.weight >= ed.weight)
				break;
			this.edges[i] = this.edges[j];
			i = j;
		}
		this.edges[i] = ed;
	}
	
	public Edge max(){//0번째 원소 리턴
		return edges[0];
	}
	
	public Edge extract_max(){
		if(size == 0){//삭제할 원소가 남아있지 않을 때
			System.out.println("heap is empty");//오류구문 출력
			return null;
		}
		Edge max = edges[0];
		edges[0] = edges[--size];//0번째 원소에 마지막 원소를 저장시켜 bst형태 유지
		heapify(0, size);//0번째 원소를 heapify진행
		
		return max;
	}
	
	public Edge get(int num){
		return edges[num];
	}
	
	public Edge h_delete(int i){
		Edge delete = edges[i];
		
		edges[i] = edges[--size];//삭제한 원소의 인덱스에 마지막 원소를 넣음
		build_heap(size);//전체 원소를 대상으로 히프화 진행
		
		return delete;//삭제한 값 리턴
	}
	
	public minheap copy(){
		Edge[] aa = new Edge[edges.length];
		System.arraycopy(edges, 0, aa, 0, edges.length);
		minheap new_minheap = new minheap(this.num_vertex, this.num_edges, this.size, aa);
		return new_minheap;
	}
}