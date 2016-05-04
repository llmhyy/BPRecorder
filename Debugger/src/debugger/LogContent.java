package debugger;

public class LogContent {

	public static final String ADD = "add";
	public static final String REMOVE = "remove";
	public static final String PRINT = "print";

	private String className;
	private int lineNumber;
	private String breakPointOperation;
	private Reason reason;

	public LogContent(String className, int lineNumber, String breakPointOperation, Reason reason) {
		super();
		this.className = className;
		this.lineNumber = lineNumber;
		this.setBreakPointOperation(breakPointOperation);
		this.reason = reason;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public String getBreakPointOperation() {
		return breakPointOperation;
	}

	public void setBreakPointOperation(String breakPointOperation) {
		this.breakPointOperation = breakPointOperation;
	}

}
