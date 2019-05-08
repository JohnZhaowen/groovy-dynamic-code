package com.a;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.tools.GroovyClass;

import com.alipay.mcenter.core.engine.aengine.compile.model.ScriptObject;
import com.alipay.mcenter.core.engine.aengine.compile.service.GroovyScriptService;
import com.alipay.mcenter.core.engine.aengine.compile.service.impl.GroovyScriptServiceImpl;
import com.alipay.mcenter.core.engine.aengine.eval.service.impl.TextScriptEngineImpl;
import com.alipay.mcenter.core.engine.aengine.model.Script;
import com.alipay.mcenter.core.engine.aengine.service.impl.ScriptObjectCacheService;
import com.alipay.mcenter.core.engine.aengine.temp.groovyservice.CommonScript;
import com.alipay.mcenter.core.engine.aengine.temp.impl.TemplateServiceImpl;

public class MyTest {
	
	private static final String script = "package com.a;" + 
			"public class User implements Enti{" + 
			"	private String name = \"user groovy\";" + 
			"	public String getName() {" + 
			"		return name;" + 
			"	}\r\n" + 
			"	public void setName(String name) {" + 
			"		this.name = name;" + 
			"	}\r\n" + 
			"	@Override" + 
			"	public String toString() {" + 
			"		return \"User [name=\" + name + \"]\";" + 
			"	}" + 
			"}";
	
	public static void main(String[] args) throws Exception {
		//testTemplateServiceImpl();
		//testScriptObjectCacheService();
		//testGroovyScriptServiceImpl();
		//testTextScriptEngineImpl();
		testTemplateServiceImpl();
	}
	
	public static void testTemplateServiceImpl() {
		TemplateServiceImpl ts = new TemplateServiceImpl();
		Map ctx = new HashMap();
		ctx.put("domainName","Articles");
		ctx.put("packageName","com.john.create");
		
		String template = ts.getTemplate(ctx, "DtoTemplate");
		System.out.println(template);
		
	}
	
	public static void testTextScriptEngineImpl() {
		
		Script s = new Script();
		s.setScriptText("sss");
		Map input = new HashMap();
		input.put("a", "a1");
		
		
		
		TextScriptEngineImpl engine = new TextScriptEngineImpl();
		String compileEvalVM = engine.compileEvalVM(s, input);
		
		System.out.println(compileEvalVM);
	}
	
	public static void testGroovyScriptServiceImpl() throws Exception {
		
		GroovyScriptService gss = new GroovyScriptServiceImpl();
		List<GroovyClass> compile = gss.compile("a",script);		
		ScriptObject<Enti> loadClasses = gss.loadClasses(compile);
		
		System.out.println(loadClasses.getCompileObject().getName());
	}

	
	public static void testScriptObjectCacheService() {		
		ScriptObjectCacheService soc = ScriptObjectCacheService.getInstance();
		soc.cacheScriptObject("test1", new ScriptObject("lala", "sss"));
		
		ScriptObject<CommonScript> scriptObject = soc.getScriptObject("test1");
		System.out.println(scriptObject.getClassName());
		System.out.println(scriptObject.getSource());
		System.out.println(scriptObject.getClassBytes());
		
		System.out.println("=================");
		soc.remove("test1");
		scriptObject = soc.getScriptObject("test1");
		System.out.println(scriptObject == null);
		
	}
	
	

}





















