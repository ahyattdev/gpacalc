package gpacalc;

public enum Grade {
	
	A, B, C, D, F;
	
	public int getWeight() {
		switch (this) {
		case A:
			return 4;
		case B:
			return 3;
		case C:
			return 2;
		case D:
			return 1;
		case F:
			return 0;
		default:
			// So it is clear that something went wrong
			return Integer.MAX_VALUE;
		}
	}
}
