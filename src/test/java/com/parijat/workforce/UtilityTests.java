package com.parijat.workforce;

import com.parijat.workforce.exception.WorkforceValidationException;
import com.parijat.workforce.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

/**
 * The type Utility tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UtilityTests
{
	@Autowired
	private Utility utility;
	
	private static final String ERROR_NAME = "ERROR";
	private static final String ERROR_MESSAGE = "SAMPLE ERROR MESSAGE";
	
	/**
	 * Test handle validation errors 1.
	 */
	@Test(expected = WorkforceValidationException.class)
	public void Test_handleValidationErrors1()
	{
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(ERROR_NAME,ERROR_NAME);
		result.addError(new ObjectError(ERROR_NAME,ERROR_MESSAGE));
		
		utility.handleValidationErrors(result);
	
	}
	
	/**
	 * Test handle validation errors 2.
	 */
	@Test(expected = WorkforceValidationException.class)
	public void Test_handleValidationErrors2()
	{
		BeanPropertyBindingResult result = new BeanPropertyBindingResult("ERROR","ERROR");
		result.addError(new ObjectError(ERROR_NAME,ERROR_MESSAGE));
		result.addError(new ObjectError(ERROR_NAME,ERROR_MESSAGE));
		
		utility.handleValidationErrors(result);
		
	}
}
