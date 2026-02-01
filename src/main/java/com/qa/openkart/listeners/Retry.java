package com.qa.openkart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;  // Keeps track of retry count
    private static final int MAX_RETRY_COUNT = 3; // Maximum retry attempts

    @Override
    public boolean retry(ITestResult result) {
        // Check if test failed
        if (!result.isSuccess()) {
            if (count < MAX_RETRY_COUNT) {
                count++;  // Increment retry count
                return true;  // Retry the test
            }
        }
        // No retries if test passed or max retries reached
        return false;
    }
}
