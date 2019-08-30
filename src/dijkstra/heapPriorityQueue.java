package dijkstra;

import java.util.LinkedList;

public class heapPriorityQueue {
	LinkedList[] heap = new LinkedList[10];//linkedlist�� �迭, �ʱ� ũ��� 10
	int size = 0;//heap �ȿ� ����ִ� ������ ����

	public void insert(heapPriorityQueue S, LinkedList<String> x){
		int key = Integer.parseInt(x.peekFirst());//������ ������ key��
		if(size == heap.length)//heap�� ��á�� �� ����� �÷���
			resize();
		int i = size++;//����� �÷���
		while(i>0){
			int j = i;
			i = (i-1)/2;//�θ����� �ε���
			LinkedList<String> temp = heap[i];
			int tkey = Integer.parseInt(temp.peekFirst());
			if(tkey <= key){//������ ������ key���� �θ������ key�� �� �������
				heap[j] = x;//j�� ������
				return;
			}
			heap[j] = heap[i];//�θ����� ���� �ڽĳ���� �ڸ��� ����(�����ϴ� ���� ���� �ø�)
		}
		heap[i] = x;//������ �ڸ��� ã�� �� ����
	}
	
	public LinkedList<String> max(){//0��° ���� ����
		return heap[0];
	}
	
	public LinkedList<String> extract_max(){
		if(size == 0){//������ ���Ұ� �������� ���� ��
			System.out.println("heap is empty");//�������� ���
			return null;
		}
		LinkedList<String> max = heap[0];
		heap[0] = heap[--size];//0��° ���ҿ� ������ ���Ҹ� ������� bst���� ����
		heapify(0, size);//0��° ���Ҹ� heapify����
		
		return max;
	}
	
	public void increase_key(String x, String k){
		int i = Integer.parseInt(x);
		heap[i].removeLast();//key�� ����
		heap[i].addLast(k);//key���� ����
		build_heap(size);//heapify�� ��ü ���Ҹ� ������� ����
	}
	
	public LinkedList<String> h_delete(String x){
		int i = Integer.parseInt(x);
		LinkedList<String> delete = heap[i];
		
		heap[i] = heap[--size];//������ ������ �ε����� ������ ���Ҹ� ����
		build_heap(size);//��ü ���Ҹ� ������� ����ȭ ����
		
		return delete;//������ �� ����
	}
	
	public void build_heap(int n){//������ ó������ ��ȸ�ϸ� heapify�� ����
		int i;
		for(i = (n/2)-1; i >= 0; i--){//������尡 �ƴ� ��忡 ����
			heapify(i, n);//����ȭ �˰��� ����
		}//�ݺ����� ���� ���� ���� ����
	}
	
	public void resize(){//heap�� ����� 2��� �÷��ִ� �Լ�
		LinkedList[] aa = new LinkedList[2*heap.length];
		System.arraycopy(heap, 0, aa, 0, heap.length);
		heap = aa;
	}
	
	public void heapify(int i, int n){//����ȭ �˰���
		LinkedList<String> hi = heap[i];
		while(i < n/2){//������尡 �ƴ� ��忡 ���Ͽ� ����
			int j = 2*i +1;//�ڽĳ���� �ε���
			LinkedList<String> temp1 = heap[j+1];
			int t1key = Integer.parseInt(temp1.peekLast());
			LinkedList<String> temp2 = heap[j];
			int t2key = Integer.parseInt(temp2.peekLast());
			if(j+1 < n && t1key < t2key)//size���� ������ ������ �ڽ��� �� �������
				j++;
			LinkedList<String> temp = heap[j];//���� �� ���� �ڽ��� ��Ī
			int tkey = Integer.parseInt(temp.peekLast());
			if(tkey >= Integer.parseInt(hi.peekLast()))//�ڽ��� ����ȭ�� �����ϴ� ���Һ��� Ŭ���
				break;//����ȭ �˰��� ����
			this.heap[i] = this.heap[j];//�ڽĿ��Ҹ� �θ���� �ڸ��� �̵�
			i = j;//�ڽĿ��Ҹ� ������� �ٽ� ����ȭ ����
		}
		this.heap[i] = hi;//�ش��ϴ� �ڸ��� ���� ���� ����
	}
	
	public void printheap(){//heap�� ���¸� ���
		System.out.println("*** ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�. ***");
		System.out.println("[index] key, value");
		System.out.println("-------------------");
		for(int i = 0; i < size; i++){
			LinkedList<String> temp = heap[i];
			System.out.println("[" + i + "] " + temp.peekFirst() + ", " + temp.peekLast());
		}
		System.out.println("----------------------------");
	}
	
	public int index_len(int i){
		LinkedList<String> temp = heap[i];
		int leng = Integer.parseInt(temp.peekLast());
		return leng;
	}
	
	public LinkedList index_get(int i){
		LinkedList<String> temp = heap[i];
		return temp;
	}
}