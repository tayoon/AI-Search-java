import java.util.ArrayList;

public class Bunki {
	
	public static void main(String[] args) {
		int tmp0[] = {1,2,3};
		int cost0[] = {2,1,1};
		Node Wakayama =new Node("和歌山",tmp0,cost0);
		
		int tmp1[] = {2}; 
		int cost1[] = {1};
		Node Kobe =new Node("神戸",tmp1,cost1);
		
		int tmp2[] = {3,4};
		int cost2[] = {1,1};
		Node Osaka =new Node("大阪",tmp2,cost2);
		
		int tmp3[] = {4,5,8};
		int cost3[] = {1,2,1};
		Node Nara =new Node("奈良",tmp3,cost3);
		
		int tmp4[] = {6,8}; 
		int cost4[] = {1,1};
		Node Kyoto =new Node("京都",tmp4,cost4);
		
		int tmp5[] = {8};
		int cost5[] = {1};
		Node Tsu =new Node("津",tmp5,cost5);
		
		int tmp6[] = {7};
		int cost6[] = {1};
		Node Fukui =new Node("福井",tmp6,cost6);
		
		int tmp7[] = {9}; 
		int cost7[] = {1};
		Node Kanazawa =new Node("金沢",tmp7,cost7);
		
		int tmp8[] = {9};
		int cost8[] = {1};
		Node Nagoya =new Node("名古屋",tmp8,cost8);
		
		int tmp9[] = {};
		int cost9[] = {};
		Node Gifu =new Node("岐阜",tmp9,cost9);
		
		Node ken[] = {Wakayama,Kobe,Osaka,Nara,Kyoto,Tsu,Fukui,Kanazawa,Nagoya,Gifu};

		
		ArrayList<Integer> openList = new ArrayList<>();
		ArrayList<Integer> openListCost = new ArrayList<>();
		ArrayList<Integer> closedList = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();

		Branch_check(0,ken,openList,closedList,path,openListCost);
		System.out.print("オープンリスト：");
		for(int i = 0;i < openList.size();i++ ) {
			System.out.print(ken[openList.get(i)].name);
		}
		System.out.println();
		System.out.print("クローズドリスト：");
		for(int i = 0;i < closedList.size();i++ ) {
			System.out.print(ken[closedList.get(i)].name);
		}
		
		
	}
	 
	 public static boolean Branch_check(int id,Node ken[],ArrayList<Integer> openList,ArrayList<Integer> closedList,ArrayList<Integer> path,ArrayList<Integer> openListCost){
		 if(id == 0) {
			 openList.add(id);
			 openListCost.add(0);
		 }		 

		 int node_id = openList.get(0);
		 int cost = openListCost.get(0);
		 
		 if(node_id == 9) {
			 System.out.println("------------終了---------");
			 System.out.println("岐阜につきました");
			 
			 int a = 9;
			 System.out.print("経路："+ken[a].name);
			 while(a != 0) {
				for(int i = 1; i < path.size();i+=2) {
					 if(path.get(i) == a) {
						 a = path.get(i-1);
						 System.out.print("←"+ken[a].name);
						 break;
					 }
				 } 
			 }
			 System.out.println();
			 System.out.println("コスト："+cost);
			 
			 
			 return true;
		 }
		 openList.remove(0);
		 openListCost.remove(0);
		 closedList.add(node_id);
		 
		for(int i=0;i <ken[node_id].childlen.size();i++ ) {
			boolean lap_flag = false;
			int child_id = ken[node_id].childlen.get(i);
			int evaluation = ken[node_id].childcost.get(i)+cost;
			for(int j=0;j < openList.size();j++ ) {
				if(child_id == openList.get(j)) {
					lap_flag = true;
					if(openListCost.get(j) > evaluation ) {
						for(int k = 1; k < path.size();k+=2) {
							 if(path.get(k) == child_id) {
								 path.set(k-1, node_id);
								 break;
							 }
						 } 
						openListCost.set(j, evaluation);
						
						Sort(openList,openListCost);
					}
				}
			}
			for(int j=0;j < closedList.size();j++ ) {
				if(child_id == closedList.get(j)) {
					lap_flag = true;
				}		
			}
			
			if(lap_flag == false){
				
				openList.add(child_id);
				openListCost.add(evaluation);
				path.add(node_id);
				path.add(child_id);
				Sort(openList,openListCost);
			}
				
		}
		if(openList.size() == 0) {
			return true;
		}else {
			System.out.print("オープンリスト：");
			for(int i = 0;i < openList.size();i++ ) {
				System.out.print(ken[openList.get(i)].name+",");
			}
			System.out.println();
			
			System.out.print("オープンリストのコスト：");
			for(int i = 0;i < openListCost.size();i++ ) {
				System.out.print(openListCost.get(i)+",");
			}
			System.out.println();
			
			System.out.print("クローズドリスト：");
			for(int i = 0;i < closedList.size();i++ ) {
				System.out.print(ken[closedList.get(i)].name+",");
			}
			System.out.println();
			System.out.println("----------------------------------------------------");
			node_id = openList.get(0);	
			
			Branch_check(node_id, ken,openList,closedList,path,openListCost);
		}
		return false;
		
	}
	 
	 public static void Sort(ArrayList<Integer> openList,ArrayList<Integer> openListCost) {
		 for(int i = 0; i < openListCost.size(); i++) {
	            int index = i;
	            for(int j = i + 1; j < openListCost.size(); j++) {
	                if(openListCost.get(j) < openListCost.get(index)) {
	                	index = j;
	                } 
	            }
	            if(i != index) {
	                int tmp1 = openListCost.get(index);
	                openListCost.set(index, openListCost.get(i));
	                openListCost.set(i, tmp1);
	                int tmp2 = openList.get(index);
	                openList.set(index, openList.get(i));
	                openList.set(i, tmp2);
	            }
	        }
	 }
	 
}

class Node{
	String name;

	ArrayList<Integer> childlen = new ArrayList<>();
	ArrayList<Integer> childcost = new ArrayList<>();
	
	Node(String name,int root[],int kane[]){
		this.name = name;
		for(int i= 0;i < root.length;i++) {
			childlen.add(root[i]);
		}
		for(int i= 0;i < kane.length;i++) {
			childcost.add(kane[i]);
		}
	}
	
}

