package jp.co.worksap.global;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * @author bheem_000
 *
 */
public class Orienteering {

	static int minDistance = 0; static int[] source ;
	static int distance = 0;
	static LinkedList<Node> nodeList = new LinkedList<Node>();
	static LinkedList<int[]> myList = new LinkedList<int[]>();
	static ArrayList<Node> nodeArr = new ArrayList<Node>();
	private static void nextMove(int h, int w, int[] source_temp,
			int[] temp_dest, char[][] map) {
		bfs(h, w, source_temp, temp_dest, map);
}
private static boolean bfs(int h, int w, int[] source_temp,
			int[] temp_dest, char[][] map) {
		LinkedList<int[]> queue = new LinkedList<int[]>();
		List<int[]> visited = new ArrayList<int[]>();
		List<List<int[]>> lists = new ArrayList<List<int[]>>();
		lists.add(new ArrayList<int[]>());
		queue.push(source_temp);
		lists.get(0).add(source_temp);
		while (!queue.isEmpty()) {
     	int[] node = queue.pollLast();
        visited.add(node);
			if (node[0] == temp_dest[0] && node[1] == temp_dest[1]) {
				List<int[]> l = getList(lists, node);
                int l_size = l.size() - 1;
				Node temp = new Node(l.get(l_size), l_size);
				nodeArr.add(temp);
				return true;
			} else {
				for (int[] suc : successors(node, queue, map)) {
		              if(!charAt(suc[0], suc[1], map).equals("#")&& !contains(visited, suc)) {
		            	queue.push(suc);
						List<int[]> list = getList(lists, node);
						List<int[]> l = new ArrayList<int[]>();

						for (int[] i : list) {
							l.add(i);
						}
						l.add(suc);
						lists.add(l);
					}
				}
			}
		}
		return false;
	}
static class Node {
	int[] k;
	int dist;

	public Node(int[] k) {
		this.k = k;
		dist = 0;
	}

	public Node(int[] k, int dist) {
		this.k = k;
		this.dist = dist;
	}
}
	static int[] countDist(ArrayList<Node> nodeArr) {
		if(nodeArr==null) return null;
	else{int[] temp = new int[2];
		int w = nodeArr.get(0).dist;
		for (Node i : nodeArr) {
			if ((i.dist <=w) && (i.dist != 0)) {
				w = i.dist;
				temp = i.k;
			}
		}
		minDistance = minDistance + w;
		return temp;
	}
		}
	static String charAt(int x, int y, char[][] map) {
		char k = map[x][y];
		String p = Character.toString(k);
		return p;
	}
    static List<int[]> successors(int[] node, List<int[]> queue, char[][]map) {
		List<int[]> result = new ArrayList<int[]>();
		int x = node[0]; 
		int y = node[1];
		int[] suc1 = new int[] { x - 1, y };
		int[] suc2 = new int[] { x + 1, y };
		int[] suc3 = new int[] { x, y - 1 };
		int[] suc4 = new int[] { x, y + 1 };
		if (!contains(queue, suc1)) {
			if(((x-1)==-1)||y==-1);
			else result.add(suc1);
		  }
		if (!contains(queue, suc2)) {
			if(((x+1)==-1)||y==-1);
			else result.add(suc2);
			}
		if (!contains(queue, suc3)) {
			if(((x)==-1)||(y-1)==-1);
			else result.add(suc3);
			}
		if (!contains(queue, suc4)) {
			if((x==-1)||(y+1)==-1);
			else 
			result.add(suc4);
		}
		return result;
	}
    
     static List<int[]> getList(List<List<int[]>> lists, int[] node) {
		for (List<int[]> l : lists) {
			if (contains(l, node))
				return l;
		}
		return null;
	}

	static void print(List<int[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0] + " " + list.get(i)[1]);

		}
		System.out.println(list.size() - 1);

	}

	static boolean contains(List<int[]> stack, int[] cell) {
		for (int[] i : stack) {
			if (i[0] == cell[0] && i[1] == cell[1])
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		int name=0;
		int[] goal = new int[2];
		goal[0]=-1; goal[1]=-1;
		int[] src = new int[2];
		src[0]=-1; src[1]=-1;
		char[][] map = new char[W][H];
		for (int i = 0; i < H; i++) {
			String str = in.next();
			char[] chr = str.toCharArray();
			for (int j = 0; j < W; j++) {
				map[j][i] = chr[j];
				if (chr[j] == '@') {
					int[] tempArr =new int[2];
					tempArr[0]= j; tempArr[1]= i ;
					myList.add(tempArr);
					name++;
				}
				if (chr[j] == 'S') {
					src[0] = j;
					src[1] = i;
					myList.add(src);
				}
				if (chr[j] == 'G') {

					goal[0] = j;
					goal[1] = i;
				}
			}
		}
		in.close();
	    source = src;
		int[] temp = new int[2];
		int x= src[0]; int y=src[1];
		if(((W<1)||(W>100)||(H<1)||(H>100)||(goal[0]==-1)||(goal[1]==-1)
				||(name>18)||(x==-1)||(y==-1)||(map[x-1][y]=='#')&&(map[x+1][y]=='#')
				&&(map[x][y-1]=='#')&&(map[x][y+1]=='#'))){
		System.out.println(-1);}
		else{while (myList.size()!=1) {
			myList.remove(source); 
			for (int[] i : myList) {
				int[] temp_dest = i;
				nextMove(H,W,source,temp_dest, map);
			}
			 temp=countDist(nodeArr); 
			for(int[] j:myList){
				if(temp[0]==j[0]&&temp[1]==j[1]) source=j;
			}
		 nodeArr.clear();
			}
		nextMove(H, W, source, goal, map);
		countDist(nodeArr);
		System.out.println(minDistance);
		}	
	}

}
