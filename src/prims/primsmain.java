package prims;

public class primsmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num_vertex = 9;
		int num_edges = 14;
		Edge[] edges = new Edge[14];
		
		edges[0] = new Edge('a', 'b', 4);
		edges[1] = new Edge('a', 'h', 8);
		edges[2] = new Edge('b', 'c', 8);
		edges[3] = new Edge('b', 'h', 11);
		edges[4] = new Edge('c', 'd', 7);
		edges[5] = new Edge('c', 'f', 4);
		edges[6] = new Edge('c', 'i', 2);
		edges[7] = new Edge('d', 'e', 9);
		edges[8] = new Edge('d', 'f', 14);
		edges[9] = new Edge('e', 'f', 10);
		edges[10] = new Edge('f', 'g', 2);
		edges[11] = new Edge('g', 'h', 1);
		edges[12] = new Edge('g', 'i', 6);
		edges[13] = new Edge('h', 'i', 7);
		
		minheap mh = new minheap(num_vertex);
		for(int i = 0; i < num_edges; i++){
			mh.insert(edges[i]);
		}
		MST mst = new MST(mh);
		mst.prim();
	}

}