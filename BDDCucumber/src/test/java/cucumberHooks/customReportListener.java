package cucumberHooks;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunStarted;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.HookTestStep;

public class customReportListener implements EventListener{
	private ExtentSparkReporter spark;
	private ExtentReports extent;
	Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
	ExtentTest scenario;
	ExtentTest step;
	public customReportListener() {
	};

	public void setEventPublisher(EventPublisher publisher) {
		// TODO Auto-generated method stub
		publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
		publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
		publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
		publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
		publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
	};

	private void runStarted(TestRunStarted event) {
		spark = new ExtentSparkReporter("./ExtentReportResults.html");
		extent = new ExtentReports();
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
	};

	private void runFinished(TestRunFinished event) {
		extent.flush();
	};

	private void featureRead(TestSourceRead event) {
		String featureSource = event.getUri().toString();
		String featureName = featureSource.split(".*/")[1];
		if (feature.get(featureSource) == null) {
			feature.putIfAbsent(featureSource, extent.createTest(featureName));
		}
	}

	private void ScenarioStarted(TestCaseStarted event) {
		String featureName = event.getTestCase().getUri().toString();
		scenario = feature.get(featureName).createNode(event.getTestCase().getName());
	};

	private void stepStarted(TestStepStarted event) {
		String stepName = " ";
		String keyword = "Triggered the hook :";
		// We checks whether the event is from a hook or step
		if (event.getTestStep() instanceof PickleStepTestStep) {
			// TestStepStarted event implements PickleStepTestStep interface
			// WHich have additional methods to interact with the event object
			// So we have to cast TestCase object to get those methods
			PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
			stepName = steps.getStep().getText();
			keyword = steps.getStep().getKeyword();
		} else {
			// Same with HoojTestStep
			HookTestStep hoo = (HookTestStep) event.getTestStep();
			stepName = hoo.getHookType().name();
		}
		step = scenario.createNode(Given.class, keyword + " " + stepName);
	};

	private void stepFinished(TestStepFinished event) {
		if (event.getResult().getStatus().toString() == "PASSED") {
			step.log(Status.PASS, "This step was  passed");
		} else if (event.getResult().getStatus().toString() == "SKIPPED") {
			step.log(Status.SKIP, "This step was skipped ");
		} else {
			step.log(Status.FAIL, "This failed");
		}
	};
	
	
}
