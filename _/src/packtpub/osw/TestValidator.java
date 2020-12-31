package packtpub.osw;

import java.util.Map;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.Validator;
import com.opensymphony.workflow.WorkflowException;

public class TestValidator implements Validator {

	public void validate(Map transientVars, Map args, PropertySet ps)
			throws InvalidInputException, WorkflowException {
//		if(transientVars.get("user.na"))
	}

}
