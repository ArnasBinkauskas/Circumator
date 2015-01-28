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
	ArrayList<WNode> start;
	ArrayList<WNode> end;
	
	public Model(){};
	
	/**
	 * Read in Circuit from file
	 * Assumes Data is correct (used netlist model)
	 * */
	public void readFile(String filename) throws IOException{
		Scanner sc = new Scanner(new File(filename));
		CircuitName = sc.nextLine();
		//reading in nodes
		sc.nextLine(); //String Nodes
        // Read up until Wires
		parseForNodes(sc.nextLine());
		sc.nextLine();
		parseForWires(sc.nextLine());
		sc.nextLine();
		sc.useDelimiter("\nStart\n");
		parseForComponents(sc.next());
		sc.nextLine();
		sc.nextLine();
		parseForStart(sc.nextLine());
		sc.nextLine();
		parseForEnd(sc.nextLine());
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
			if (buffer.length >= 3){
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
		comp = new HashMap<String, LogicComponent>();
		String[] compLine = raw.split("\n");
		for (String line : compLine)
			readComponent(line);
	}
	
	public void parseForStart(String raw){
		start = new ArrayList<WNode>();
		String[] nodeS = raw.split(" ");
		for (String ID: nodeS)
			start.add(node.get(ID));
	}
	
	public void parseForEnd(String raw){
		end = new ArrayList<WNode>();
		String[] nodeS = raw.split(" ");
		for (String ID: nodeS)
			end.add(node.get(ID));
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
		else if (buffer[0].equals("buf1"))
			c = readBuf1(buffer);
		else if (buffer[0].equals("inv1"))
			c = readInv1(buffer);
		else if (buffer[0].equals("fork12"))
			c = readFork12(buffer);
		else if (buffer[0].equals("fork13"))
			c = readFork13(buffer);
		else c = null;
		
		if (c.equals(null))
			System.out.println("Unknown Component");
		else
			comp.putIfAbsent(buffer[0], c);
	}
	
	public And readAnd1(String[] netLine){
		String[] input = netLine[2].split(",");
		return  new And(netLine[1], 
						node.get(input[0]), 
						node.get(input[1]),
						node.get(netLine[3]), 
						computePoint(netLine[4]));
	}
	
	public Or readOr1(String[] netLine){
		String[] input = netLine[2].split(",");
		return  new Or(netLine[1], 
						node.get(input[0]), 
						node.get(input[1]),
						node.get(netLine[3]), 
						computePoint(netLine[4]));
	}
	
	public Buffer readBuf1(String[] netLine){
		return  new Buffer(netLine[1], 
						node.get(netLine[2]), 
						node.get(netLine[3]), 
						computePoint(netLine[4]));	
	}
	
	public Inverter readInv1(String[] netLine){
		return  new Inverter(netLine[1], 
						node.get(netLine[2]), 
						node.get(netLine[3]), 
						computePoint(netLine[4]));	
	}
	
	public Fork readFork12(String[] netLine){
		String[] output = netLine[3].split(",");
		return new Fork(netLine[1], 
						node.get(netLine[2]), 
						node.get(output[0]),
						node.get(output[1]),
						computePoint(netLine[4]));
	}
	
	public ForkThree readFork13(String[] netLine){
		String[] output = netLine[3].split(",");
		return new ForkThree(netLine[1], 
						node.get(netLine[2]), 
						node.get(output[0]),
						node.get(output[1]),
						node.get(output[2]),
						computePoint(netLine[4]));
	}
	
	public Point computePoint(String c){
		String[] coord = c.split(",");
		return new Point( Integer.parseInt(coord[0]), 
				   Integer.parseInt(coord[1]));
	}
		
}


