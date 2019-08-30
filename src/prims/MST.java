package prims;

public class MST {
	minheap Q;
	Edge[] S = null;
	int mincost = 0;
	int s_len = 0;
	char[] V = null;
	int v_len = 0;
	
	public MST(minheap mh){
		this.Q = mh;
		S = new Edge[Q.size];
		V = new char[Q.num_vertex];
	}
	
	public void prim(){
		V[v_len++] = 'a'; //시작 정점 설정
		System.out.println(" w( ,a) = 0");
		while(s_len < Q.num_vertex - 1){
			minheap tempQ = Q.copy();
			for(int i = 0; i < Q.size; i++){
				Edge temp = tempQ.extract_max();
				char isin = isin(temp);
				if(isin == 'f')
					continue;
				else{
					if(isin == 'v'){
						V[v_len++] = temp.w;
						S[s_len++] = temp;
						mincost += temp.weight;
						for(int j = 0; j < Q.size; j++)
							if(Q.get(j) == temp){
								Q.h_delete(j);
								break;
							}
						print(temp);
						break;
					}
					else{
						V[v_len++] = temp.v;
						S[s_len++] = temp;
						mincost += temp.weight;
						for(int j = 0; j < Q.size; j++)
							if(Q.get(j) == temp){
								Q.h_delete(j);
								break;
							}
						print(temp);
						break;
					}
				}
			}
		}
		System.out.println("\n w(MST) = " + mincost);
	}
	
	public char isin(Edge e){
		char in_vertex = 'f';
		for(int i = 0; i < v_len; i++){
			if(in_vertex == 'f' && V[i] == e.v)
				in_vertex = 'v';
			else if(in_vertex == 'f' && V[i] == e.w)
				in_vertex = 'w';
			else if(in_vertex != 'f' && (V[i] == e.v || V[i] == e.w))
				in_vertex = 'f';
		}
		return in_vertex;
	}
	
	private void print(Edge e){
		System.out.println(" w("+e.v+","+e.w+") = "+e.weight);
	}
	
}