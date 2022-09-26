package testCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class DependentDropdown {

	@Test
	public void verifyCascadingDropdown() throws StreamReadException, DatabindException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		driver.get("file:///C:\\Users\\Mansur\\eclipse-workspace\\SeleniumPractice\\src\\main\\resources\\html\\Cascade.html");
		
		WebElement dropdownSub=driver.findElement(By.id("subject"));
		Select selectDropDownSub=new Select(dropdownSub);
		List<String> actualSub=new ArrayList<String>();
		actualSub=selectDropDownSub.getOptions().stream().map(ele -> ele.getText()).collect(Collectors.toList());
		actualSub.remove(0);
		System.out.print("Actual Subjects :" + actualSub);
		
		File expectedJsonFile=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\json\\expected.json");
		ObjectMapper objectMapper=new ObjectMapper();
		Map<String,Object> expectedjsonDataMap=objectMapper.readValue(expectedJsonFile,new TypeReference<Map<String,Object>>(){});
		
		Set<String> expectedSubjectSet=expectedjsonDataMap.keySet();
		List<String> expectedSubject=new ArrayList<String>();
		expectedSubject.addAll(expectedSubjectSet);
		System.out.print("Expected Subjects :" + expectedSubject);
		Assert.assertEquals(actualSub, expectedSubject);
		
		
		
		
		
		
	}
}
