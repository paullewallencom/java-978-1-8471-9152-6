/**
 * 
 */
package packtpub.osw;

import java.io.InputStreamReader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;

/**
 * Helper for Drools integration.
 */
public class DroolsHelper {

	public static void main(String[] args) {
		try {
			LoanRequest r = new LoanRequest(1000, "Diego", true, 1000, false,
					false, false);
			setupRules("/loan.drl");
			workingMemory.assertObject(r);
			workingMemory.fireAllRules();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static WorkingMemory workingMemory;

	public static WorkingMemory setupRules(String ruleFile)
			 {
		try {
			PackageBuilder builder = new PackageBuilder();
			builder.addPackageFromDrl(new InputStreamReader(DroolsHelper.class
					.getResourceAsStream(ruleFile)));
			org.drools.rule.Package pkg = builder.getPackage();
			RuleBase ruleBase = RuleBaseFactory.newRuleBase();
			ruleBase.addPackage(pkg);
			workingMemory = ruleBase.newWorkingMemory();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return workingMemory;
	}

}
