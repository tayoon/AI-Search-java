import java.util.ArrayList;

public class Width {
	
	public static void main(String[] args) {
		int tmp0[] = {1, 2, 3};
		Node Wakayama =	new Node("和歌山",tmp0);
		int tmp1[] = {2}; 
		Node Kobe =		new Node("神戸",tmp1);
		int tmp2[] = {3, 4};
		Node Osaka =	new Node("大阪",tmp2);
		int tmp3[] = {4, 5, 8};
		Node Nara =		new Node("奈良",tmp3);
		int tmp4[] = {6, 8}; 
		Node Kyoto =	new Node("京都",tmp4);
		int tmp5[] = {8};
		Node Tsu =		new Node("津",tmp5);
		int tmp6[] = {7};
		Node Fukui =	new Node("福井",tmp6);
		int tmp7[] = {9}; 
		Node Kanazawa =	new Node("金沢",tmp7);
		int tmp8[] = {9};
		Node Nagoya =	new Node("名古屋",tmp8);
		int tmp9[] = {};
		Node Gifu =		new Node("岐阜",tmp9);
		
		Node ken[] = {Wakayama, Kobe, Osaka, Nara, Kyoto, Tsu, Fukui, Kanazawa, Nagoya, Gifu};
		
		ArrayList<Integer> openList = new ArrayList<>();
		ArrayList<Integer> closedList = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		
		Width_check(0, ken, openList, closedList, path);
		
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
	 
	public static boolean Width_check(int id, Node ken[], ArrayList<Integer> openList, ArrayList<Integer> closedList, ArrayList<Integer> path){
		if(id == 0) {
			openList.add(id);
		}		 
		
		int tmp = openList.get(0);
		
		if(tmp == 9) {
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
			
			return true;
		}
		
		openList.remove(0);
		
		closedList.add(tmp);
		 
		for(int i=0;i <ken[tmp].childlen.size();i++ ) {
			boolean lap_flag = false;
			for(int j=0;j < openList.size();j++ ) {
				if(ken[tmp].childlen.get(i) == openList.get(j)) {
					lap_flag = true;
				}		
			}
			
			for(int j=0;j < closedList.size();j++ ) {
				if(ken[tmp].childlen.get(i) == closedList.get(j)) {
					lap_flag = true;
				}		
			}
			
			if(lap_flag == false){
				openList.add(ken[tmp].childlen.get(i));
				path.add(tmp);
				path.add(ken[tmp].childlen.get(i));
			} 
				
		}
		if(openList.size() == 0) {
			return true;
		}else{
			System.out.print("オープンリスト：");
			for(int i = 0; i < openList.size(); i++) {
				System.out.print(ken[openList.get(i)].name+",");
			}
			System.out.println();
			
			System.out.print("クローズドリスト：");
			for(int i = 0; i < closedList.size(); i++) {
				System.out.print(ken[closedList.get(i)].name+",");
			}
			System.out.println();
			System.out.println("----------------------------------------------------");
			
			tmp = openList.get(0);			
			Width_check(tmp, ken, openList, closedList, path);
		}
		return false;
		
	}
	
}

class Node{
	String name;

	ArrayList<Integer> childlen = new ArrayList<>();
	
	Node(String name, int tmp[]){
		this.name = name;
		for(int i = 0; i < tmp.length; i++) {
			childlen.add(tmp[i]);
		}

	}
	
}

