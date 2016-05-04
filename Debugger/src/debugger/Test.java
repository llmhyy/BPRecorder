package debugger;

import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.InvocationException;
import com.sun.jdi.ObjectReference;

public class Test {
	public void test(){
		ObjectReference f = null;
		try {
			f.invokeMethod(null, null, null, 0);
		} catch (InvalidTypeException | ClassNotLoadedException | IncompatibleThreadStateException
				| InvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Runtime.getRuntime().exec(cmdLine, new String[]{}, workingDirectory);
	}
}
