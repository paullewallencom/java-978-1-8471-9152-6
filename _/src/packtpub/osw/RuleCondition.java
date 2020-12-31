/**
 * 
 */
package packtpub.osw;

import java.util.Iterator;
import java.util.Map;

import org.drools.WorkingMemory;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.WorkflowException;

/**
 * Drools Condition
 */
public class RuleCondition implements Condition{

	public boolean passesCondition(Map transientVars, Map args, PropertySet arg2) throws WorkflowException {
		WorkingMemory wm = DroolsHelper.setupRules((String)args.get("ruleName"));
		RuleConditionalResult result = new RuleConditionalResult();
		for (Iterator iter = transientVars.values().iterator(); iter.hasNext();) {
			wm.assertObject(iter.next());
		}
		wm.assertObject(result);
		wm.fireAllRules();
		System.out.println("Condition:" + result.getResult());
		return result.getResult();
	}
}