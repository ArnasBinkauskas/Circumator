import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

public class Model {
	String CircuitName;
	HashMap<String, WNode> node;
	
	public Model(){};
	
	public void readFile(String filename) throws IOException{
		Scanner sc = new Scanner(new File(filename));
		CircuitName = sc.next();
		
		
		sc.close();
	}
}
