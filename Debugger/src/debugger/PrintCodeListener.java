package debugger;

import java.util.Map;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

public class PrintCodeListener implements IElementChangedListener {
	
	private boolean isFirst = false;
	private CompilationUnit cu = null;
	
	private TypingListener typeListener = new TypingListener();
	
	class TypingListener implements IDocumentListener{
		 @Override
	        public void documentChanged(DocumentEvent event) 
	        {
	        	
	        	if(event.getText().contains("print")){
	        		if(isFirst){
	        			int offset = event.getOffset();
	        			
	        			int lineNumber = cu.getLineNumber(offset);
	        			String typeName = getFullNameOfCompilationUnit(cu);
	        			
	        			Reason reason = UI.getUserReason();
						
						if(reason != null){
							LogContent content = new LogContent(typeName, lineNumber, LogContent.PRINT, reason);
							Activator.logList.add(content);
							
							ExcelWriter writer = new ExcelWriter();
							writer.start();
							writer.export(Activator.logList, "log");
						}
	        			
	        			
	        			isFirst = false;
	        		}
	        	}
	        	
	            //System.out.println("Change happened: " + event.toString());
	        }

	        @Override
	        public void documentAboutToBeChanged(DocumentEvent event) {
	            //System.out.println("I predict that the following change will occur: "+event.toString());


	        }
	}
	
	@Override
	public void elementChanged(ElementChangedEvent event) {
		isFirst = true;
		IJavaElement obj = event.getDelta().getElement();
		if(obj instanceof ICompilationUnit){
			ICompilationUnit icu = (ICompilationUnit)obj;
			cu = convertICompilationUnitToASTNode(icu);
			
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					IEditorInput input = editor.getEditorInput();  
					IDocument document=(((ITextEditor)editor).getDocumentProvider()).getDocument(input);
					document.addDocumentListener(typeListener);
					
				}
			});
		}
		
//		IJavaElementDelta delta = event.getDelta();
//		if (delta != null) {
//			Object obj = event.getSource();
//			System.out.println("delta received: ");
//			
//			if(delta.getKind() == IJavaElementDelta.ADDED || delta.getKind() == IJavaElementDelta.CHANGED){		
//				if(delta.getAffectedChildren().length > 0){
//					IJavaElementDelta[] element = delta.getAffectedChildren();
//					System.out.println(element);
//					
//				}
//			}
//			
//		}
	}
	
	public String getFullNameOfCompilationUnit(CompilationUnit cu){
		String packageName = cu.getPackage().getName().toString();
		AbstractTypeDeclaration typeDeclaration = (AbstractTypeDeclaration) cu.types().get(0);
		String typeName = typeDeclaration.getName().getIdentifier();
		
		return packageName + "." + typeName; 
	}
	
	@SuppressWarnings("rawtypes")
	public CompilationUnit convertICompilationUnitToASTNode(ICompilationUnit iunit){
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_7, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setSource(iunit);
		
		CompilationUnit cu = null;
		try{
			cu = (CompilationUnit) parser.createAST(null);		
			return cu;
		}
		catch(java.lang.IllegalStateException e){
			return null;
		}
	}

}
