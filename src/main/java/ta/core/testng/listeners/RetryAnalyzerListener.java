package ta.core.testng.listeners;

import lombok.extern.slf4j.Slf4j;
import ta.core.env.Environment;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


@Slf4j
@SuppressWarnings("unused")
public class RetryAnalyzerListener implements IRetryAnalyzer {

    private int counter = 0;
    private static final int RETRY_LIMIT = Environment.getOrDefault("retryOnFail", 0);

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            log.info("retrying failed test with count <{}>", counter);
            if (counter < RETRY_LIMIT) {
                counter++;
                return true;
            }
        }
        return false;
    }

}
