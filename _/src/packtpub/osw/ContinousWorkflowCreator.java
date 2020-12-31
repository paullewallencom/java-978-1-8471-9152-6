/**
 * 
 */
package packtpub.osw;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * @author Naya
 * @version 1.0
 */
public class ContinousWorkflowCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		System.out.println("available workflows:" + Arrays.toString(workflow.getWorkflowNames()));
		try {
			while(true){
				final String wfName = "holiday";
				long wfid = workflow
						.initialize(wfName, 100, new HashMap());
				System.out.println(wfid);
				workflow.doAction(wfid, 1, Collections.EMPTY_MAP);
				workflow.doAction(wfid, 2, Collections.EMPTY_MAP);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}