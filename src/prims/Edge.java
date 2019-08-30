package prims;

public class Edge {
	char v; //시작 정점 인덱스
	char w; //끝 정점 인덱스
	int weight; //가중치
	Edge(char v, char w, int weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
}
