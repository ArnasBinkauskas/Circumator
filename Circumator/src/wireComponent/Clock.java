package wireComponent;

import main.Clocked;
import java.util.ArrayList;

public class Clock extends WNode{
	
	ArrayList<Clocked> clockedComponents;
	
	public Clock(){
		super("clock");
		this.setReady(true);
		clockedComponents = new ArrayList<Clocked>();
	}
	
	public void tick(){
		for(Clocked component: clockedComponents)
			component.tick();
	}
	
	public void plugComponent(Clocked c){
		clockedComponents.add(c);
	}

}
