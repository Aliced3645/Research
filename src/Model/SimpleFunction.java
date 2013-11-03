package Model;

public class SimpleFunction implements Function {
	@Override
	public int deduceValue(int[] inputValues) {
		// TODO Auto-generated method stub
		return inputValues[0] * -1;
	}
}
