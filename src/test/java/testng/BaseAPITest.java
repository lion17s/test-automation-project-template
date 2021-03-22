package testng;

import com.ta.core.testng.listeners.APITestListener;
import org.testng.annotations.Listeners;

@Listeners(APITestListener.class)
public abstract class BaseAPITest extends BaseTest {

}
