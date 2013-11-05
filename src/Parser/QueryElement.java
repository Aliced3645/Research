package Parser;

import java.util.ArrayList;

/**
 * For a specific sql query, 
 * @author szhang
 *
 */
public class QueryElement {
	
	String tableName;
	ArrayList<String> columns;
	
	public QueryElement(String tableName, ArrayList<String> columns){
		this.tableName = tableName;
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}
	
}
