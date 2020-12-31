package packtpub.osw.cep;

import java.util.Map;

import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.util.BeanUtils;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * Function provider that sends an event to the esper engine.
 */
public class PushEventFunctionProvider implements FunctionProvider {

	public void execute(Map transientVars, Map args, PropertySet arg2)
			throws WorkflowException {
		// the event POJO class name to be instantiated by reflection
		String eventClassName = ((String)args.get("event.class.name")).trim();
		Object event;
		try {
			event = Class.forName(eventClassName).newInstance();
		} catch (Exception e) {
			throw new WorkflowException(e);
		} 
		//invokes the setters of the event with the argument map data.
		// if a setter name is equals to a map key, it will be called
		// with the key's value as a parameter.
		// f.e.: if the arguemnt Map contains a key "name" with value "john"
		// and the event bean has a method called setName, it will be invoked
		// by reflection in the following way: setName("john").
		// See the javadocs for more detail.
		BeanUtils.setValues(event, args, null);
		// gets the default provider and the default runtime for this JVM.
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
		EPRuntime runtime = epService.getEPRuntime();
		System.out.println("Sending event.");
		System.out.println(event);
		runtime.sendEvent(event);
	}

}
