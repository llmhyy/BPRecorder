package debugger;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class UI {
	
	public class Reason{
		String message;

		public Reason(String message) {
			super();
			this.message = message;
		}
		
		
	}
	
	public static String getUserReason(){
		
		final Reason reason = new UI().new Reason(null);
		
		Display.getDefault().syncExec(new Runnable() {
		    @Override
		    public void run() {
		        IWorkbenchWindow iw = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		        ReasonDialog dialog = new ReasonDialog(iw.getShell());
		        dialog.create();
		        if(dialog.open() == Window.OK){
		        	String message = dialog.getReason();
		        	reason.message = message;
		        }
		        
		    }
		});
		
		return reason.message;
	}
}
