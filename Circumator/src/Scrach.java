import java.util.ArrayList;
import java.util.HashMap;

import wireComponent.Point;
import wireComponent.WNode;
import wireComponent.Wire;


public class Scrach {
	static HashMap<String, WNode> node;
	static HashMap<String, Wire> wire;
	
	public static void main(String args[]){
		parseForNodes("n1 (20,20); n2 (20,30); n3 (20,60); n4 (60,20); n5 (60,30); n6 (60,60); n7 (80,25); n8 (80,60); n9 (120,25); n10 (120,35); n11 (140,30); n12 (150,30); n13 (170,30); n14 (180,30); n15 (200,30); n16 (230,30)");
		parseForWires("w1 (n1,n4); w2 (n2,n5); w3 (n3,n6); w4 (n7,n9); w5 (n8,n10) (100,60):(60,35); w6 (n11,n12); w7 (n13,n14); w8 (n15,n16)");
		System.out.println(wire.get("w3").toString());
		System.out.println(node.get("n16").toString());
	}
	
	
	public static void parseForNodes(String raw){
		node = new HashMap<String, WNode>();
		String[] allNodes = raw.split("; ");
		String[] buffer;
		for (int i = 0; i < allNodes.length; i++ ){
			buffer = allNodes[i].split(" ");
			//get rid of brakets and commas
			String[] cords = buffer[1].substring(1, buffer[1].length() - 1).split(",");
			WNode n = new WNode(buffer[0]);
			n.setCordinates(new Point( Integer.parseInt(cords[0]), 
									   Integer.parseInt(cords[1])));
			node.putIfAbsent(buffer[0], n);
		}

	}
	
	public static void parseForWires(String raw){
		wire = new HashMap<String, Wire>();
		String[] allWires = raw.split("; ");
		String[] buffer;
		for (int i = 0; i < allWires.length; i++ ){
			buffer = allWires[i].split(" ");
			String[] nodes = buffer[1].substring(1, buffer[1].length() - 1).split(",");
			Wire w = new Wire(buffer[0], node.get(nodes[0]), node.get(nodes[1]));
			if (buffer.length > 2){
				ArrayList<Point> moreCoords = new ArrayList<Point>();
				String[] c = buffer[2].split(":");
				for (int j = 0; j < c.length; j ++){
					String[] coord = c[j].substring(1, c[j].length() - 1).split(",");
					moreCoords.add(new Point( Integer.parseInt(coord[0]), 
									          Integer.parseInt(coord[1])));
				}
				w.setCoordinates(moreCoords);
			}
			wire.putIfAbsent(buffer[0], w);
		}
	}
	
	
}
