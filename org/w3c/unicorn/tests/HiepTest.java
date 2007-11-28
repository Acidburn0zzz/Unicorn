package org.w3c.unicorn.tests;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.unicorn.UnicornCall;
import org.w3c.unicorn.contract.EnumInputMethod;
import org.w3c.unicorn.output.OutputFactory;
import org.w3c.unicorn.output.OutputFormater;
import org.w3c.unicorn.output.OutputModule;

public class HiepTest {

	public static void main(String[] args) {
		Map<String, String[]> mapOfParameter = new LinkedHashMap<String, String[]>();
		
		// lang par defaut
		String[] tmp = {"en"};
		mapOfParameter.put("ucn_lang", tmp); 
		
		UnicornCall aUnicornCall = new UnicornCall();
		aUnicornCall.setTask("markup"); //task id
		aUnicornCall.setEnumInputMethod(EnumInputMethod.URI);
		aUnicornCall.setDocumentName("http://w3.org");
		aUnicornCall.setMapOfStringParameter(mapOfParameter);
		aUnicornCall.setInputParameterValue("http://w3.org");
		
		
		try {
			aUnicornCall.doTask();
			
			Map<String, Object> mapOfStringObject = new LinkedHashMap<String, Object>();
			mapOfStringObject.put("unicorncall", aUnicornCall);
			OutputFormater aOutputFormater = OutputFactory.getOutputFormater(
					"xhtml10", // le template --> text ou xhtml10, see unicorn.properties
					"en",   // la langue
					"text/plain"); // MIME Type
			OutputModule aOutputModule = OutputFactory.getOutputModule("simple");
			PrintWriter pw = new PrintWriter(System.out);
			aOutputModule.produceOutput(aOutputFormater, mapOfStringObject, null, pw);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}
