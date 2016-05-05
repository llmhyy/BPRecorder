package debugger;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class UI {
	
	public static Reason getUserReason(){
		
		final Reason reason = new Reason(null);
		
		Display.getDefault().syncExec(new Runnable() {
		    @Override
		    public void run() {
		        IWorkbenchWindow iw = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		        ReasonDialog dialog = new ReasonDialog(iw.getShell());
		        dialog.create();
		        if(dialog.open() == Window.OK){
		        	reason.isCodeUnderstanding = dialog.isCodeUnderstanding();
		        	reason.isProgramSemanticInspection = dialog.isProgramSemanticsInspection();
		        	reason.isVariableTracking = dialog.isVariableTracking();
		        	reason.isVariableInspection = dialog.isVariableInspection();
		        	reason.isIntuition = dialog.isIntuition();
		        	reason.intuitionString = dialog.getIntuitionString();
		        	reason.otherReason = dialog.getOtherReason();
		        	reason.comprehensionDetail = dialog.getComprehensionString();
		        	reason.variableDetail = dialog.getVariableString();
		        }
		        
		    }
		});
		
		return reason;
	}
}
