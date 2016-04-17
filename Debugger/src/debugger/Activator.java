package debugger;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.internal.debug.core.breakpoints.JavaLineBreakpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "Debugger"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	public static List<LogContent> logList = new ArrayList<>();
	public static String logFileName = "log";
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		
		
//		JavaBreakpointActionDelegate delegate = new JavaBreakpointActionDelegate();
		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(new IBreakpointListener() {
			
			@Override
			public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
				String operation = LogContent.REMOVE;
				recordToExcel(breakpoint, operation);
				
			}
			
			@Override
			public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void breakpointAdded(IBreakpoint breakpoint) {
				String operation = LogContent.ADD;
				recordToExcel(breakpoint, operation);
				
			}
			
			private void recordToExcel(IBreakpoint breakpoint, String operation) {
				
				boolean is = false;
				if(is){
					return;
				}
				
				if(breakpoint instanceof JavaLineBreakpoint){
					JavaLineBreakpoint javaBP = (JavaLineBreakpoint)breakpoint;
					try {
						int lineNumber = javaBP.getLineNumber();
						String typeName = javaBP.getTypeName();
						
						String reason = UI.getUserReason();
						
						if(reason != null){
							LogContent content = new LogContent(typeName, lineNumber, operation, reason);
							logList.add(content);
							
							ExcelWriter writer = new ExcelWriter();
							writer.start();
							writer.export(logList, "log");
						}
						
						
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
