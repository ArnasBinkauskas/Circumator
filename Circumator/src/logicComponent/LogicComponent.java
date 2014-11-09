package logicComponent;
import java.util.Map;
import wireComponent.*;

//All Components will have a Node input with a meaningful name
//Input and output names MUST be different
public abstract class LogicComponent {
	Map<String, WNode> input; 
	Map<String, WNode> output;

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
	
	public Map<String, WNode> getInput(){
		return input;
	}
	
	public Map<String, WNode> getOutput(){
		return output;
	}
	
	
	/**
	 * Tries to plug the node given to an input specified.
	 * Returns False if the Logic component doesn't have an input named "in_name"
	 * True if successfull
	 * */
	public boolean plugInput(ENode n, String in_name){
		if (input.get(in_name) != null){
			input.put(in_name, n);
			return true;
		}
		else return false;
	}
	
	/**
	 * Tries to plug the node given to an output specified.
	 * Returns False if the Logic component doesn't have an input named "in_name"
	 * True if successfull
	 * */
	public boolean plugOutput(SNode n, String out_name){
		if (input.get(out_name) != null){
			input.put(out_name, n);
			return true;
		}
		else return false;
	}
}
				