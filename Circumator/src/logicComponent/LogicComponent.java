package logicComponent;
import java.util.HashMap;
import wireComponent.*;

//All Components will have a Node input with a meaningful name
//Input and output names MUST be different
public class LogicComponent {
	public HashMap<String, ENode> input; 
	public HashMap<String, SNode> output;
	//coordinates
	public int xCord;
	public int yCord;
	//gate delay 1 by default
	public int gateDelay = 1;
	public String label;


	
	public LogicComponent(){
		input = new HashMap<String, ENode>();
		output = new HashMap<String, SNode>();
	}
	
	/**
	 * Many components will have a fixed number of inputs/outputs
	 * */
	public LogicComponent(int in_capacity, int out_capacity){
		input = new HashMap<String, ENode>(in_capacity);
		output = new HashMap<String, SNode>(out_capacity);
	}
	
	/**@TODO 
	 * Implement to show signals on inputs and outputs
	 */
	public String toString(){
		String ans = "Inputs: ";
        ans += "/n";
        ans += "Outputs: ";
        ans += "/n";
		return ans;
	}
	
	public void setCordinates(int x, int y){
		xCord = x;
		yCord= y;
	}
	
	public void setLabel(String l){
		label = l;
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	/**
	 * Tries to plug the node given to an input specified.
	 * Returns False if the Logic component doesn't have an input named "in_name"
	 * True if successful
	 * */
	public boolean plugInput(ENode n, String in_name){
		if (input.containsKey(in_name)){
			input.put(in_name, n);
			return true;
		}
		else return false;
	}
	
	/**
	 * Tries to plug the node given to an output specified.
	 * Returns False if the Logic component doesn't have an input named "in_name"
	 * True if successful
	 * */
	public boolean plugOutput(SNode n, String out_name){
		if (output.containsKey(out_name)){
			output.put(out_name, n);
			return true;
		}
		else return false;
	}
}
				