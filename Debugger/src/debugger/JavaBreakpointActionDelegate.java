package debugger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.jdt.core.dom.Message;
import org.eclipse.jdt.debug.core.IJavaBreakpoint;
import org.eclipse.jdt.debug.core.IJavaBreakpointListener;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.debug.core.IJavaThread;
import org.eclipse.jdt.debug.core.IJavaType;

public class JavaBreakpointActionDelegate implements IJavaBreakpointListener {

	public JavaBreakpointActionDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addingBreakpoint(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		System.out.println("add");
	}

	@Override
	public int installingBreakpoint(IJavaDebugTarget target, IJavaBreakpoint breakpoint, IJavaType type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void breakpointInstalled(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public int breakpointHit(IJavaThread thread, IJavaBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void breakpointRemoved(IJavaDebugTarget target, IJavaBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointHasRuntimeException(IJavaLineBreakpoint breakpoint, DebugException exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointHasCompilationErrors(IJavaLineBreakpoint breakpoint, Message[] errors) {
		// TODO Auto-generated method stub
		
	}

	

}
