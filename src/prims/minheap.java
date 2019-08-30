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
	
	public void resize(){//heap�� ����� 2��� �÷��ִ� �Լ�
		Edge[] aa = new Edge[2*edges.length];
		System.arraycopy(edges, 0, aa, 0, edges.length);
		edges = aa;
	}
	
	public void build_heap(int n){//������ ó������ ��ȸ�ϸ� heapify�� ����
		int i;
		for(i = (n/2)-1; i >= 0; i--){//������尡 �ƴ� ��忡 ����
			heapify(i, n);//����ȭ �˰��� ����
		}//�ݺ����� ���� ���� ���� ����
	}
	
	public void heapify(int i, int n){//����ȭ �˰���
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
	
	public Edge max(){//0��° ���� ����
		return edges[0];
	}
	
	public Edge extract_max(){
		if(size == 0){//������ ���Ұ� �������� ���� ��
			System.out.println("heap is empty");//�������� ���
			return null;
		}
		Edge max = edges[0];
		edges[0] = edges[--size];//0��° ���ҿ� ������ ���Ҹ� ������� bst���� ����
		heapify(0, size);//0��° ���Ҹ� heapify����
		
		return max;
	}
	
	public Edge get(int num){
		return edges[num];
	}
	
	public Edge h_delete(int i){
		Edge delete = edges[i];
		
		edges[i] = edges[--size];//������ ������ �ε����� ������ ���Ҹ� ����
		build_heap(size);//��ü ���Ҹ� ������� ����ȭ ����
		
		return delete;//������ �� ����
	}
	
	public minheap copy(){
		Edge[] aa = new Edge[edges.length];
		System.arraycopy(edges, 0, aa, 0, edges.length);
		minheap new_minheap = new minheap(this.num_vertex, this.num_edges, this.size, aa);
		return new_minheap;
	}
}