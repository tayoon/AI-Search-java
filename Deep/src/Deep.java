import java.util.ArrayList;

public class Deep {
	
	public static void main(String[] args) {
		int tmp0[] = {1,2,3};
		Node Wakayama =new Node("�a�̎R",tmp0);
		int tmp1[] = {2}; 
		Node Kobe =new Node("�_��",tmp1);
		int tmp2[] = {3,4};
		Node Osaka =new Node("���",tmp2);
		int tmp3[] = {4,5,8};
		Node Nara =new Node("�ޗ�",tmp3);
		int tmp4[] = {6,8}; 
		Node Kyoto =new Node("���s",tmp4);
		int tmp5[] = {8};
		Node Tsu =new Node("��",tmp5);
		int tmp6[] = {7};
		Node Fukui =new Node("����",tmp6);
		int tmp7[] = {9}; 
		Node Kanazawa =new Node("����",tmp7);
		int tmp8[] = {9};
		Node Nagoya =new Node("���É�",tmp8);
		int tmp9[] = {};
		Node Gifu =new Node("��",tmp9);

		Node ken[] = {Wakayama,Kobe,Osaka,Nara,Kyoto,Tsu,Fukui,Kanazawa,Nagoya,Gifu};
		
		ArrayList<Integer> openList = new ArrayList<>();
		ArrayList<Integer> closedList = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		Deep_check(0,ken,openList,closedList,path);
		System.out.print("�I�[�v�����X�g�F");
		for(int i = 0;i < openList.size();i++ ) {
			System.out.print(ken[openList.get(i)].name);
		}
		System.out.println();
		System.out.print("�N���[�Y�h���X�g�F");
		for(int i = 0;i < closedList.size();i++ ) {
			System.out.print(ken[closedList.get(i)].name);
		}
		
	}
	 
	 public static boolean Deep_check(int id,Node ken[],ArrayList<Integer> openList,ArrayList<Integer> closedList,ArrayList<Integer> path){
		 if(id == 0) {
			 openList.add(id);
			 //System.out.println("�I�[�v�����X�g��"+ken[id].name+"��ǉ����܂���");
		 }		 
		 
		 int node_id = openList.get(0);
		 
		 if(node_id == 9) {
			 System.out.println("�򕌂ɂ��܂���");
			 
			 int a = 9;
			 System.out.print("�o�H�F"+ken[a].name);
			 while(a != 0) {
				for(int i = 1; i < path.size();i+=2) {
					 if(path.get(i) == a) {
						 a = path.get(i-1);
						 System.out.print("��"+ken[a].name);
					 }
					 if(a == 0) {
						 break;
					 }
				 } 
			 }
			 System.out.println();
			 
			 
			 return true;
		 }
		 //System.out.println("�I�[�v�����X�g��"+ken[openList.get(0)].name+"���폜���܂���");
		 openList.remove(0);
		 //System.out.println("�N���[�Y�h���X�g��"+ken[node_id].name+"��ǉ����܂���");
		 closedList.add(node_id);
		 
		for(int i=ken[node_id].childlen.size()-1 ;i >= 0;i-- ) {
			boolean lap_flag = false;
			for(int j=0;j < openList.size();j++ ) {
				if(ken[node_id].childlen.get(i) == openList.get(j)) {
					lap_flag = true;
				}		
			}
			for(int j=0;j < closedList.size();j++ ) {
				if(ken[node_id].childlen.get(i) == closedList.get(j)) {
					lap_flag = true;
				}		
			}
			
			if(lap_flag == false){
				//System.out.println(ken[node_id].childlen.get(i));
				openList.add(0,ken[node_id].childlen.get(i));
				path.add(node_id);
				path.add(ken[node_id].childlen.get(i));
				//System.out.println("�I�[�v�����X�g��"+ken[ken[node_id].childlen.get(i)].name+"��ǉ����܂���");
			} 
				
		}
		if(openList.size() == 0) {
			return true;
		}else {
			System.out.print("�I�[�v�����X�g�F");
			for(int i = 0;i < openList.size();i++ ) {
				System.out.print(ken[openList.get(i)].name+",");
			}
			System.out.println();
			
			System.out.print("�N���[�Y�h���X�g�F");
			for(int i = 0;i < closedList.size();i++ ) {
				System.out.print(ken[closedList.get(i)].name+",");
			}
			System.out.println();
			System.out.println("----------------------------------------------------");
			node_id = openList.get(0);			
			Deep_check(node_id, ken,openList,closedList,path);
		}
		return false;
		
	}
	 
}

class Node{
	String name;

	ArrayList<Integer> childlen = new ArrayList<>();
	
	Node(String name,int tmp[]){
		this.name = name;
		for(int i= 0;i < tmp.length;i++) {
			childlen.add(tmp[i]);
		}

	}
	
}

