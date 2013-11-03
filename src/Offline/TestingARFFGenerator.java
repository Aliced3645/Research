package Offline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


/**
 * Using WEKA API to generate a simple ARFF file.
 * @author szhang
 *
 */
public class TestingARFFGenerator {
	
	static final int ROWS = 1000;
	static final String FILE_NAME = "MyRelation";
	
	public static void main(String[] args) throws Exception {
		// 1. set up attributes
	     FastVector atts = new FastVector();
	     atts.addElement(new Attribute("num1"));
	     atts.addElement(new Attribute("num2"));
	     Instances data = new Instances("MyRelation", atts, 0);
	     
	     //fill with data
	     for(int i = 0; i < ROWS; i ++){
	    	 double[] vals = new double[data.numAttributes()];
	    	 for(int j = 0; j < data.numAttributes(); j ++){
	    		 if(j == 0) vals[j] = i;
	    		 else vals[j] = vals[j-1] * -1;
	    	 }
	    	 data.add(new DenseInstance(1.0, vals));
	     }
	     
	     //persistence
	     BufferedWriter writer = 
	    		 new BufferedWriter(new FileWriter(FILE_NAME));
	     writer.write(data.toString());
	     writer.flush();
	     writer.close();
	}
}
