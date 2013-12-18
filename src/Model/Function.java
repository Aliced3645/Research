package Model;

public interface Function {
	
	//interface for storing the function pointer 
	//Now we just assume typical cells can deduce to the value regardless
	//of the impact of other rows.
	int deduceValue(int[] inputValues);
	
	
}
