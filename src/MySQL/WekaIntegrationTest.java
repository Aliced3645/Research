package MySQL;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Related Knowledge from: 
 * 	http://weka.wikispaces.com/Databases
 * 	http://weka.wikispaces.com/Use+Weka+in+your+Java+code
 * This class is for testing using Weka working with MySqL.
 * @author shu
 */
public class WekaIntegrationTest {
	
	/**
	 * Retrieving instances from MySQL database.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		InstanceQuery query = new InstanceQuery();
		query.setUsername("shu");
		query.setPassword("shu");
		query.setQuery("select * from table2");
		Instances data = query.retrieveInstances();
		System.out.println(data.get(1));
	}
	
	
}
