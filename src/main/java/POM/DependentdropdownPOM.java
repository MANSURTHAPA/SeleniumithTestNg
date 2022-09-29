package POM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DependentdropdownPOM {

	public WebDriver driver;

	// findby is used for pagefactory annotations to not use driver.findElement
	@FindBy(id = "subject")
	WebElement dropdownSub;
	@FindBy(id = "topic")
	WebElement dropdownTopic;
	@FindBy(id = "chapter")
	WebElement dropdownChapter;

	// function for selecting dropdown
	public Select selectSub() {
		Select selectDropDownSub = new Select(dropdownSub);
		return selectDropDownSub;
	}

	// function for getting the list values in dropdown
	public List<String> actualsubList() {
		List<String> actualSub = new ArrayList<String>();
		actualSub = selectSub().getOptions().stream().map(ele -> ele.getText()).collect(Collectors.toList());
		actualSub.remove(0);
		System.out.print("Actual Subjects :" + actualSub);
		return actualSub;

	}

	// reading json file for expected output
	public Map<String, Object> expectedResult() throws StreamReadException, DatabindException, IOException {
		File expectedJsonFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\resources\\json\\expected.json");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> expectedjsonDataMap = objectMapper.readValue(expectedJsonFile,
				new TypeReference<Map<String, Object>>() {
				});
		return expectedjsonDataMap;
	}

	// getting the expected subjects
	public List<String> expectedsubList() throws StreamReadException, DatabindException, IOException {
		Set<String> expectedSubjectSet = expectedResult().keySet();
		List<String> expectedSubject = new ArrayList<String>();
		expectedSubject.addAll(expectedSubjectSet);
		System.out.print("Expected Subjects :" + expectedSubject);
		return expectedSubject;

	}

	// selecting the topic of dropdown
	public Select selectTopic() {
		Select selectDropDownSub = new Select(dropdownTopic);
		return selectDropDownSub;
	}

	// for getting the list of selected topic
	public List<String> actualTopList() {
		List<String> actualTop = new ArrayList<String>();
		actualTop = selectTopic().getOptions().stream().map(ele -> ele.getText()).collect(Collectors.toList());
		actualTop.remove(0);
		System.out.print("Actual topics :" + actualTop);
		return actualTop;

	}

	// getting value for selected topic
	public Map<String, Object> expectedTopicResult(String subject)
			throws StreamReadException, DatabindException, IOException {
		Object frontEndvalue = expectedResult().get(subject);
		Map<String, Object> frontEndMap = (Map<String, Object>) frontEndvalue;
		return frontEndMap;
	}

	// getting the expected topics
	public List<String> expectedTopList(String subject) throws StreamReadException, DatabindException, IOException {
		Set<String> expectedTopicSet = expectedTopicResult(subject).keySet();
		List<String> expectedTopic = new ArrayList<String>();
		expectedTopic.addAll(expectedTopicSet);
		System.out.print("Expected Topics :" + expectedTopic);
		return expectedTopic;

	}
	
	// selecting the chapter of dropdown
	public Select selectChapter() {
		Select selectDropDownSub = new Select(dropdownChapter);
		return selectDropDownSub;
	}
	// for getting the list of selected chapter
		public List<String> actualChapterList() {
			List<String> actualChap = new ArrayList<String>();
			actualChap = selectChapter().getOptions().stream().map(ele -> ele.getText()).collect(Collectors.toList());
			actualChap.remove(0);
			System.out.print("Actual CHAPTER :" + actualChap);
			return actualChap;

		}
		//expected chapter list
		public List<String> expectedChapterList(String subject,String topic) throws StreamReadException, DatabindException, IOException {
			Object chapVal=expectedTopicResult(subject).get(topic);
			@SuppressWarnings("unchecked")
			List<String> expectedChap= (List<String>) chapVal;
			System.out.print("Expected chapter :" + expectedChap);
			return expectedChap;
		}

	// constructor
	public DependentdropdownPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
