package debugger;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ReasonDialog extends TitleAreaDialog {

	private String codeUnderstandingText = "I am not sure what this segment of code is about. "
			+ "I am just trying to understand its behaviors";
	private String programSemanticsInspectionText = "I know what this part of the program should do, "
			+ "I am checking if it is correctly implemented. (Input: in your mind, what the program should do? "
			+ "Please brief it in natural language)";
	private String variableTrackingText = "I think some variables are critical, "
			+ "but I don¡¯t know how exactly their values are updated. I am trying to track this information. "
			+ "(Input: what variable and why do you think they are critical? E.g. based on your intuition/experience, "
			+ "based on the specification, etc.)";
	private String variableInspectionText = "I know the expected value of these variables. "
			+ "I am just checking if they have the correct values before/after execution. "
			+ "(Input: What variable and what triggers you to check them? How did you figure out their expected values?) ";
	private String intuitionText = "Just based on your intuition, "
			+ "or based on some particular breakpoint you have seen an abnormal value, etc.";
	
	private Text reasonText;
	private String otherReasonString = "";
	
	private Text inText;
	private String intuitionString = "";
	
	private boolean isCodeUnderstanding = false;
	private boolean isProgramSemanticsInspection = false;
	private boolean isVariableTracking = false;
	private boolean isVariableInspection = false;
	private boolean isIntuition = false;
	

	public ReasonDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Break Point Reason");
		setMessage("Please fill in your reason", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);

		createGroups(container);
		
		return area;
	}

	private void createGroups(Composite container) {
		
		createProgramComprehensionGroup(container);
		createVariableGroup(container);
		createIntuitionGroup(container);
		createReasonGroup(container);
		
	}

	private void createProgramComprehensionGroup(Composite container) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		
		Group group = new Group(container, SWT.NONE);
		group.setLayoutData(gridData);
		
		group.setText("Program Comprehension");
		
		GridLayout layout = new GridLayout(2, true);
		group.setLayout(layout);
		
		Button codeUnderstandingButton = new Button(group, SWT.RADIO);
		codeUnderstandingButton.setText("Code Understanding");
		codeUnderstandingButton.setToolTipText(codeUnderstandingText);
		codeUnderstandingButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCodeUnderstanding = true;
				isProgramSemanticsInspection = false;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		
		Button programSemanticsInspectionButton = new Button(group, SWT.RADIO);
		programSemanticsInspectionButton.setText("Program Semantic Inspection");
		programSemanticsInspectionButton.setToolTipText(programSemanticsInspectionText);
		programSemanticsInspectionButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCodeUnderstanding = false;
				isProgramSemanticsInspection = true;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
	}
	
	private void createVariableGroup(Composite container) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		
		Group group = new Group(container, SWT.NONE);
		group.setLayoutData(gridData);
		
		group.setText("Variable Related");
		
		GridLayout layout = new GridLayout(2, true);
		group.setLayout(layout);
		
		Button variableTrackingButton = new Button(group, SWT.RADIO);
		variableTrackingButton.setText("Variable Tracking");
		variableTrackingButton.setToolTipText(variableTrackingText);
		variableTrackingButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isVariableTracking = true;
				isVariableInspection = false;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		
		Button variableInspectionButton = new Button(group, SWT.RADIO);
		variableInspectionButton.setText("Variable Inspection");
		variableInspectionButton.setToolTipText(variableInspectionText);
		variableInspectionButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isVariableTracking = false;
				isVariableInspection = true;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
	}
	
	private void createIntuitionGroup(Composite container) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		
		Group group = new Group(container, SWT.NONE);
		group.setLayoutData(gridData);
		
		group.setText("Intuition, please fill in what intuition in blank (optional)");
		
		GridLayout layout = new GridLayout(1, true);
		group.setLayout(layout);
		
		Button intuitionButton = new Button(group, SWT.CHECK);
		intuitionButton.setText("Intuition");
		intuitionButton.setToolTipText(intuitionText);
		intuitionButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isIntuition = !isIntuition;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.grabExcessVerticalSpace = true;
		textData.horizontalAlignment = GridData.FILL;
		textData.verticalAlignment = GridData.FILL;
		textData.heightHint = 50;
		
		inText = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		inText.setLayoutData(textData);
	}
	
	private void createReasonGroup(Composite container) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.heightHint = 100;
		
		Group group = new Group(container, SWT.NONE);
		group.setLayoutData(gridData);
		
		group.setText("Or specify other reason");
		
		GridLayout layout = new GridLayout(1, true);
		group.setLayout(layout);
		
		this.reasonText = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		this.reasonText.setLayoutData(gridData);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		this.setReason(this.reasonText.getText());
		this.setIntuitionString(this.inText.getText());
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public String getOtherReason() {
		return otherReasonString;
	}

	public void setReason(String reason) {
		this.otherReasonString = reason;
	}

	public String getIntuitionString() {
		return intuitionString;
	}

	public void setIntuitionString(String intuitionString) {
		this.intuitionString = intuitionString;
	}

	public boolean isCodeUnderstanding() {
		return isCodeUnderstanding;
	}

	public void setCodeUnderstanding(boolean isCodeUnderstanding) {
		this.isCodeUnderstanding = isCodeUnderstanding;
	}

	public boolean isProgramSemanticsInspection() {
		return isProgramSemanticsInspection;
	}

	public void setProgramSemanticsInspection(boolean isProgramSemanticsInspection) {
		this.isProgramSemanticsInspection = isProgramSemanticsInspection;
	}

	public boolean isVariableTracking() {
		return isVariableTracking;
	}

	public void setVariableTracking(boolean isVariableTracking) {
		this.isVariableTracking = isVariableTracking;
	}

	public boolean isVariableInspection() {
		return isVariableInspection;
	}

	public void setVariableInspection(boolean isVariableInspection) {
		this.isVariableInspection = isVariableInspection;
	}

	public boolean isIntuition() {
		return isIntuition;
	}

	public void setIntuition(boolean isIntuition) {
		this.isIntuition = isIntuition;
	}

	
	
}
