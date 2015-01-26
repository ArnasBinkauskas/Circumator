import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import logicComponent.*;
import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

public class Model {
	String CircuitName;
	HashMap<String, WNode> node;
	HashMap<String, Wire> wire;
	HashMap<String, LogicComponent> comp;
	
	public Model(){};
	
	/**
	 * Read in Circuit from file
	 * Assumes Data is correct (used netlist model)
	 * */
	public void readFile(String filename) throws IOException{
		Scanner sc = new Scanner(new File(filename));
		CircuitName = sc.next();
		//reading in nodes
		sc.next(); //String Nodes
		sc.useDelimiter("Wires"); // Read up until Wires
		parseForNodes(sc.next());
		sc.useDelimiter("Components");
		parseForWires(sc.next());
		
		sc.close();
	}
	
	public void parseForNodes(String raw){
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
	
	public void parseForWires(String raw){
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
	
	public void parseForComponents(String raw){
		String[] compLine = raw.split("\n");
		
	}
	
	public void readComponent(String line){
		String[] buffer = line.split(" ");
		LogicComponent c;
		for (int i = 2; i < 5; i++)
			buffer[i] = buffer[i].substring(1, buffer[i].length() - 1);
		
		if (buffer[0].equals("and1"))
			c =  readAnd1(buffer);
		else if (buffer[0].equals("or1"))
			c = readOr1(buffer);
	}
	
	public And readAnd1(String[] netLine){
		String[] input = netLine[2].split(",");
		String[] coord = netLine[4].split(",");
		Point coordsPoint = new Point( Integer.parseInt(coord[0]), 
		          					   Integer.parseInt(coord[1]));
		return  new And(netLine[1], 
						node.get(input[0]), 
						node.get(input[1]),
						node.get(netLine[3]), 
						coordsPoint);
	}
	
	public Or readOr1(String[] netLine){
		String[] input = netLine[2].split(",");
		String[] coord = netLine[4].split(",");
		Point coordsPoint = new Point( Integer.parseInt(coord[0]), 
		          					   Integer.parseInt(coord[1]));
		return  new Or(netLine[1], 
						node.get(input[0]), 
						node.get(input[1]),
						node.get(netLine[3]), 
						coordsPoint);
	}
		
}


