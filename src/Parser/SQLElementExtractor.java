package Parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import Offline.MetadataManager;
import Zql.ParseException;
import Zql.ZExpression;
import Zql.ZFromItem;
import Zql.ZQuery;
import Zql.ZSelectItem;
import Zql.ZqlParser;

/**
 * Given a SQL, extract all elements ( < table, column > pair ) using ZQL.
 * @author szhang
 *
 */
public class SQLElementExtractor {

	static ArrayList<QueryElement> getQueryElementsFromSQL(
			String sql) throws ParseException, IOException{
		
		ArrayList<QueryElement> elements = new ArrayList<QueryElement>();
		InputStream is = new ByteArrayInputStream(sql.getBytes());
		ZqlParser parser = new ZqlParser(is);
		ZQuery statement = (ZQuery) parser.readStatement();
		Vector<ZSelectItem> zColumns = statement.getSelect();
		Vector<ZFromItem> zTables = statement.getFrom();
		/**
		 * Query the metadata table and generate the elements.
		 * Methodology: Query all combinations.
		 */
		HashSet<String> columnsSet = new HashSet<String>();
		for(ZSelectItem zColumn : zColumns){
			if(zColumn.getAggregate() == null){
				columnsSet.add(zColumn.getColumn());
			} else {
				String aggregate = zColumn.getAggregate();
				String aggregatePlusColumn = zColumn.getColumn();
				String columnString = aggregatePlusColumn.substring(
						aggregate.length() + 1,
						aggregatePlusColumn.length() - 1);
				columnsSet.add(columnString);
			}
		}
		/**
		 * Look at metadata to determine combination.
		 */
		HashMap<String, ArrayList<String>> metadata = MetadataManager.loadMetadata();
		for(ZFromItem zTable : zTables){
			String table = zTable.getTable();
			ArrayList<String> columns = metadata.get(table);
			Iterator<String> columnIterator = columns.iterator();
			while(columnIterator.hasNext()){
				if(!columnsSet.contains(columnIterator.next())){
					//filter out
					columnIterator.remove();
				}
			}
			QueryElement element = 
					new QueryElement(table, new ArrayList<String>(columns));
			elements.add(element);
		}
		
		return elements;
		
	}
	
	/**
	 * Just for Testing
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, IOException{
		String sql =  "select avg(num1) from MyRelation where num1 > 5000;";
		ArrayList<QueryElement> 
			elements = SQLElementExtractor.getQueryElementsFromSQL(sql);
		for(QueryElement element : elements){
			System.out.println(element.getTableName() + " " + element.getColumns());
		}
	}
}
