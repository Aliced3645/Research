package Offline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.core.Instances;

public class ARFFReader {

	public static Instances readARFF(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				path));
		Instances data = new Instances(reader);
		reader.close();
		data.setClassIndex(data.numAttributes() - 1);
		return data;
	}
	
	public static void main(String[] args) throws IOException{
		String path = "MyRelation";
		System.out.println(ARFFReader.readARFF(path));
	}
}
