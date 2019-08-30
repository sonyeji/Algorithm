package dijkstra;

import java.util.LinkedList;

public class heapPriorityQueue {
	LinkedList[] heap = new LinkedList[10];//linkedlist의 배열, 초기 크기는 10
	int size = 0;//heap 안에 들어있는 원소의 개수

	public void insert(heapPriorityQueue S, LinkedList<String> x){
		int key = Integer.parseInt(x.peekFirst());//삽입할 원소의 key값
		if(size == heap.length)//heap이 꽉찼을 때 사이즈를 늘려줌
			resize();
		int i = size++;//사이즈를 늘려줌
		while(i>0){
			int j = i;
			i = (i-1)/2;//부모노드의 인덱스
			LinkedList<String> temp = heap[i];
			int tkey = Integer.parseInt(temp.peekFirst());
			if(tkey <= key){//삽입할 원소의 key보다 부모원소의 key가 더 작은경우
				heap[j] = x;//j에 삽입함
				return;
			}
			heap[j] = heap[i];//부모노드의 값을 자식노드의 자리에 저장(삽입하는 값을 위로 올림)
		}
		heap[i] = x;//삽입할 자리를 찾은 후 삽입
	}
	
	public LinkedList<String> max(){//0번째 원소 리턴
		return heap[0];
	}
	
	public LinkedList<String> extract_max(){
		if(size == 0){//삭제할 원소가 남아있지 않을 때
			System.out.println("heap is empty");//오류구문 출력
			return null;
		}
		LinkedList<String> max = heap[0];
		heap[0] = heap[--size];//0번째 원소에 마지막 원소를 저장시켜 bst형태 유지
		heapify(0, size);//0번째 원소를 heapify진행
		
		return max;
	}
	
	public void increase_key(String x, String k){
		int i = Integer.parseInt(x);
		heap[i].removeLast();//key값 삭제
		heap[i].addLast(k);//key값을 삽입
		build_heap(size);//heapify를 전체 원소를 대상으로 진행
	}
	
	public LinkedList<String> h_delete(String x){
		int i = Integer.parseInt(x);
		LinkedList<String> delete = heap[i];
		
		heap[i] = heap[--size];//삭제한 원소의 인덱스에 마지막 원소를 넣음
		build_heap(size);//전체 원소를 대상으로 히프화 진행
		
		return delete;//삭제한 값 리턴
	}
	
	public void build_heap(int n){//원소의 처음부터 순회하며 heapify를 진행
		int i;
		for(i = (n/2)-1; i >= 0; i--){//리프노드가 아닌 노드에 대해
			heapify(i, n);//히프화 알고리즘 적용
		}//반복문을 돌며 다음 노드로 접근
	}
	
	public void resize(){//heap의 사이즈를 2배로 늘려주는 함수
		LinkedList[] aa = new LinkedList[2*heap.length];
		System.arraycopy(heap, 0, aa, 0, heap.length);
		heap = aa;
	}
	
	public void heapify(int i, int n){//히프화 알고리즘
		LinkedList<String> hi = heap[i];
		while(i < n/2){//리프노드가 아닌 노드에 한하여 진행
			int j = 2*i +1;//자식노드의 인덱스
			LinkedList<String> temp1 = heap[j+1];
			int t1key = Integer.parseInt(temp1.peekLast());
			LinkedList<String> temp2 = heap[j];
			int t2key = Integer.parseInt(temp2.peekLast());
			if(j+1 < n && t1key < t2key)//size범위 내에서 오른쪽 자식이 더 작은경우
				j++;
			LinkedList<String> temp = heap[j];//둘중 더 작은 자식을 지칭
			int tkey = Integer.parseInt(temp.peekLast());
			if(tkey >= Integer.parseInt(hi.peekLast()))//자식이 히프화를 진행하는 원소보다 클경우
				break;//히프화 알고리즘 종료
			this.heap[i] = this.heap[j];//자식원소를 부모원소 자리로 이동
			i = j;//자식원소를 대상으로 다시 히프화 진행
		}
		this.heap[i] = hi;//해당하는 자리에 원래 원소 삽입
	}
	
	public void printheap(){//heap의 상태를 출력
		System.out.println("*** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ***");
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