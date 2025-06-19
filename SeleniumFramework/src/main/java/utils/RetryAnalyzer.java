package utils;

import org.testng.ITestResult;
import org.testng.IRetryAnalyzer;
public class RetryAnalyzer implements IRetryAnalyzer{
	int count=0;
	int retryCount=1;
	    private static final int MAX_RETRY_COUNT = 3;
	    
	    @Override
	    public boolean retry(ITestResult result) {
	        if (count < MAX_RETRY_COUNT) {
	            count++;
	            return true; // Retry the test
	        }
	        return false; // Do not retry anymore
	    }

	

}
