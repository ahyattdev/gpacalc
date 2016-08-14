package gpacalc;

public enum ClassType {
	
	AP, REGULAR;
	
	public int getOffset() {
		switch (this) {
		case AP:
			return 1;
		case REGULAR:
			return 0;
		default:
			return 0;
		}
	}
	
}
