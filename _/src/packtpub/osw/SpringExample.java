package packtpub.osw;

import java.util.Collections;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import com.opensymphony.workflow.Workflow;

public class SpringExample {
	public static void main(String[] args) {
		   XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("osworkflow-spring-hibernate3.xml"));

	        Workflow workflow = (Workflow) beanFactory.getBean("workflow");
	        try {
				workflow.initialize("example", 100, Collections.EMPTY_MAP);
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
}
