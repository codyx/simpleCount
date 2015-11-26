package simpleCount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * Listener Proxy class
 * Implements ActionListener
 * @author Aimeric Seguin
 * @version 1.0
 */
public class ListenerProxy {
	
	/**
	 * Instantiate a generic ActionListener
	 * @param dest: target object
	 * @param methodName: name of the method in the target object
	 * @return a new customized Proxy instance (i.e. a dynamic proxy) casted in ActionListener
	 */
	  public static ActionListener	actionListener(final Object dest, final String methodName) {
		  	final Method meth = getMethod(dest, methodName);
		    InvocationHandler handler = new InvocationHandler() {
		        @Override
		        public Object invoke(Object proxy, Method method, Object[] args)
		            throws Throwable {
		          ActionEvent event = (ActionEvent) args[0];
		          return meth.invoke(dest, event);
		        }
		      };
		    return (ActionListener) Proxy.newProxyInstance(dest.getClass().getClassLoader(),
		    		new Class[] { ActionListener.class }, handler);
	  }
	  
		/**
		 * Method getter
		 * @param dest: target object
		 * @param methodName: name of the method in the target object
		 * @return a Method corresponding to the method targeted in the object dest
		 */
	  protected static Method getMethod(Object dest, String methodName) {
		    try {
		      return dest.getClass().getMethod(methodName, ActionEvent.class);
		    }
		    catch (NoSuchMethodException err) {
			      throw new IllegalStateException(err);
			    }
		    catch (NullPointerException err) {
		    	throw new NullPointerException();
		    }
		    catch (SecurityException err) {
		      throw new IllegalStateException(err);
		    }
		  }

}
