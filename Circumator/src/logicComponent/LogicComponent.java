package logicComponent;
import java.util.Map;
import wireComponent.WNode;

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
}
				