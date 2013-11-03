package Offline;

import java.util.ArrayList;
import java.util.HashMap;
import Model.ModelDescriptor;
import Model.SimpleFunction;

/**
 * Model manager is managing models/sample in off line.
 * @author szhang
 *
 */
public class ModelManager {
	
	/**
	 * In the very early prototype, we manually assign an already existing
	 * model to it.
	 */
	static HashMap<String, HashMap<String, ModelDescriptor>> modelMap;
	static {
		modelMap = new 
			HashMap<String, HashMap<String, ModelDescriptor>>();
	
		//insert an already known model (MyRelation - MyRelation_sample)
		String fileName = "MyRelation";
		String sampleFileName= "MyRelation_sample";
		String missingAttribute = "num2";
		
		ModelDescriptor modelDescriptor = new ModelDescriptor();
		modelDescriptor.missingAttribute = missingAttribute;
		modelDescriptor.function = new SimpleFunction();
		modelDescriptor.sampledFileName = sampleFileName;
		ArrayList<String> sourceAttributes = 
				new ArrayList<String>();
		sourceAttributes.add("num1");
		modelDescriptor.sourceAttributes = sourceAttributes;
		
		HashMap<String, ModelDescriptor> subMap = 
				new HashMap<String, ModelDescriptor>();
		subMap.put(missingAttribute, modelDescriptor);
		
		modelMap.put(fileName, subMap);
	}
	
	public HashMap<String, HashMap<String, ModelDescriptor>> getModelMap(){
		return modelMap;
	}
}
