package packtpub.osw;

import java.util.Iterator;
import java.util.Map;

import org.drools.WorkingMemory;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * Rule executing function provider.
 * 
 */
public class RuleExecutorFunctionProvider implements FunctionProvider {

	public void execute(Map transientVars, Map args, PropertySet arg2)
			throws WorkflowException {
		WorkingMemory wm = DroolsHelper.setupRules((String)args.get("ruleName"));
		for (Iterator iter = transientVars.values().iterator(); iter.hasNext();) {
			wm.assertObject(iter.next());
		}
		wm.fireAllRules();
	}
}
