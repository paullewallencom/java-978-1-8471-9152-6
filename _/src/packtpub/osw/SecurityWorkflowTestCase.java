/**
 * 
 */
package packtpub.osw;

import java.util.HashMap;

import com.opensymphony.user.Group;
import com.opensymphony.user.User;
import com.opensymphony.user.UserManager;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * @author Naya
 * @version 1.0
 */
public class SecurityWorkflowTestCase extends WorkflowTestCase {

	/* (non-Javadoc)
	 * @see packtpub.osw.WorkflowTestCase#setUp()
	 */
	public void setUp() {
		 UserManager um = UserManager.getInstance();
		    User test = null;
			Group foos = null;
			Group bars = null;
			Group bazs = null;
			try {
				test = um.createUser("test");
				test.setPassword("test");

				foos = um.createGroup("foos");
				bars = um.createGroup("bars");
				bazs = um.createGroup("bazs");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		    test.addToGroup(foos);
		    test.addToGroup(bars);
		    test.addToGroup(bazs);


		final String wfName = "example";
		workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		try {
			workflowId = workflow.initialize(wfName, 100, new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
