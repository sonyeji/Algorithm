package dijkstra;

public class dijkstramain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] w = {{2147483647, 10, 3, 2147483647, 2147483647},
				{2147483647, 2147483647, 1, 2, 2147483647},
				{2147483647, 4, 2147483647, 8, 2},
				{2147483647, 2147483647, 2147483647, 2147483647, 7},
				{2147483647, 2147483647, 2147483647, 9, 2147483647}};
	
		Priority p = new Priority(w);
		p.dijkstra(0);
	}
}
