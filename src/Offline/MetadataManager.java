package Offline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Maintains metadata information for tables Basic information: (table_name -
 * table_columns)
 * 
 * @author szhang
 * 
 */
public class MetadataManager {

	static final String REGISTER_TABLE_NAME = "Metadata";

	static public void registerTable(String table, 
			String columnsString) throws IOException {
		Instances data = ARFFReader.readARFF(REGISTER_TABLE_NAME);
		double[] vals = new double[data.numAttributes()];
		vals[0] = data.attribute(0).addStringValue(table);
		vals[1] = data.attribute(1).addStringValue(columnsString);
		data.add(new DenseInstance(1.0, vals));
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				REGISTER_TABLE_NAME));
		writer.write(data.toString());
		writer.flush();
		writer.close();
	}
	
	static public HashMap<String, ArrayList<String>> loadMetadata() throws IOException {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		Instances metadata = ARFFReader.readARFF(REGISTER_TABLE_NAME);
		for (int i = 0; i < metadata.size(); i++) {
			Instance row = metadata.firstInstance();
			String table = row.stringValue(0);
			String columnsString = row.stringValue(1);
			String[] columns = columnsString.split(",");
			ArrayList<String> columnList = new ArrayList<String>();
			for(String column : columns){
				columnList.add(column);
			}
			map.put(table, columnList);
		}
		return map;
	}

	public static void main(String[] args) throws IOException {
		// generate an ARFF file for storing the metadata like a register.
		FastVector atts = new FastVector();
		atts.addElement(new Attribute("table", (FastVector) null));
		atts.addElement(new Attribute("columns", (FastVector) null));
		Instances data = new Instances("Metadata", atts, 0);

		double[] vals = new double[data.numAttributes()];
		vals[0] = data.attribute(0).addStringValue("MyRelation");
		vals[1] = data.attribute(1).addStringValue("num1,num2");
		data.add(new DenseInstance(1.0, vals));

		BufferedWriter writer = new BufferedWriter(new FileWriter(
				REGISTER_TABLE_NAME));
		writer.write(data.toString());
		writer.flush();
		writer.close();

	}
}
