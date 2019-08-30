package Ford_Fulkerson_Algorithm;

public class fordmain {
	
	// Driver program to test above functions 
    public static void main (String[] args) throws java.lang.Exception 
    { 
        // Let us create a graph shown in the above example 
        int graph[][] =new int[][] { {0, 12, 0, 3, 0, 0}, 
                                     {0, 0, 10, 0, 0, 0}, 
                                     {0, 0, 0, 0, 3, 15}, 
                                     {0, 11, 0, 0, 5, 0}, 
                                     {0, 0, 0, 0, 0, 17}, 
                                     {0, 0, 0, 0, 0, 0} 
                                   }; 
        MaxFlow m = new MaxFlow(); 
  
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, 0, 5)); 
  
    } 
}