package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
@CucumberOptions(plugin = {"cucumberHooks.customReportListener"},
monochrome=true,
glue={"stepDefinitions","cucumberHooks"},
features = {"src/test/resources/features"} //FolderName
)
public class cucumberRunner extends AbstractTestNGCucumberTests{
	
	@DataProvider(parallel = true)
	 @Override
	 public Object[][] scenarios() {
	 return super.scenarios();
	 }

}
