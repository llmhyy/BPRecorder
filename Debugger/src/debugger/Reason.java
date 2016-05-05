package debugger;

public class Reason {
	boolean isCodeUnderstanding;
	boolean isProgramSemanticInspection;
	boolean isVariableTracking;
	boolean isVariableInspection;
	boolean isIntuition;
	public String comprehensionDetail;
	public String variableDetail;
	String intuitionString;
	String otherReason;
	
	String message;

	public Reason(String message) {
		super();
		this.message = message;
	}
}
