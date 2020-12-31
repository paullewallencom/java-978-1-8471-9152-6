package packtpub.osw;
import java.util.Date;
import java.util.Map;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.WorkflowException;

/**
 * Allows execution if today is not Sunday.
 */
public class TimeCondition implements Condition {

	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		Date date = new Date();
		int day = Integer.valueOf((String)args.get("dayNumber")).intValue();
		return date.getDay() != 7;
	}
}
