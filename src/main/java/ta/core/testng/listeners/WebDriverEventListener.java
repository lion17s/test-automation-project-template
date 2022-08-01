package ta.core.testng.listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
public class WebDriverEventListener implements WebDriverListener {

    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        log.error("error happened with target <{}> for method <{}> with args <{}>",
                target.toString(), method.toString(), args);
    }

    public void beforeGet(WebDriver driver, String url) {
        log.info("getting <{}>", url);
    }

    public void afterGet(WebDriver driver, String url) {
        log.info("<{}> gotten", url);
    }

    public void beforeGetCurrentUrl(WebDriver driver) {
        log.info("getting current url");
    }

    public void afterGetCurrentUrl(String result, WebDriver driver) {
        log.info("current url is <{}>", result);
    }

    public void beforeGetTitle(WebDriver driver) {
        log.info("getting title");
    }

    public void afterGetTitle(WebDriver driver, String result) {
        log.info("title is <{}>", result);
    }

    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("trying to find element with locator <{}>", locator.toString());
    }

    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("element with locator <{}> found with result <{}>", locator.toString(), result.toString());
    }

    public void beforeFindElements(WebDriver driver, By locator) {
        log.info("trying to find elements with locator <{}>", locator);
    }

    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        log.info("element(s) with locator <{}> found with result <{}>", locator.toString(), result.toString());
    }

    public void beforeGetPageSource(WebDriver driver) {
        log.info("getting page source");
    }

    public void afterGetPageSource(WebDriver driver, String result) {
        log.info("page source is \n<{}>", result);
    }

    public void beforeClose(WebDriver driver) {
        log.info("closing driver");
    }

    public void afterClose(WebDriver driver) {
        log.info("driver closed");
    }

    public void beforeQuit(WebDriver driver) {
        log.info("quitting driver");
    }

    public void afterQuit(WebDriver driver) {
        log.info("driver quit");
    }

    public void beforeGetWindowHandles(WebDriver driver) {
        log.info("getting window handles");
    }

    public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
        log.info("window handle(s) gotten <{}>", result.toString());
    }

    public void beforeGetWindowHandle(WebDriver driver) {
        log.info("getting window handle");
    }

    public void afterGetWindowHandle(WebDriver driver, String result) {
        log.info("window handle gotten <{}>", result);
    }

    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        log.info("executing script <{}> with args <{}>", script, args);
    }

    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        log.info("script <{}> with args <{}> executed with result <{}>", script, args, result.toString());
    }

    public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
        log.info("executing async script <{}> with args <{}>", script, args);
    }

    public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
        log.info("async script <{}> with args <{}> executed with result <{}>", script, args, result.toString());
    }

    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        log.info("performing actions <{}>", actions.toString());
    }

    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        log.info("actions <{}> performed", actions.toString());
    }

    public void beforeResetInputState(WebDriver driver) {
        log.info("resetting input state");
    }

    public void afterResetInputState(WebDriver driver) {
        log.info("input state reset");
    }

    public void beforeClick(WebElement element) {
        log.info("trying to click on element <{}>", element.toString());
    }

    public void afterClick(WebElement element) {
        log.info("clicked on element <{}>", element.toString());
    }

    public void beforeSubmit(WebElement element) {
        log.info("trying to submit an element <{}>", element.toString());
    }

    public void afterSubmit(WebElement element) {
        log.info("element submitted <{}>", element.toString());
    }

    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("sending key(s) <{}> to element <{}>", keysToSend, element.toString());
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("key(s) <{}> to element <{}> sent", keysToSend, element.toString());
    }

    public void beforeClear(WebElement element) {
        log.info("clearing element <{}>", element.toString());
    }

    public void afterClear(WebElement element) {
        log.info("element <{}> cleared", element.toString());
    }

    public void beforeGetTagName(WebElement element) {
        log.info("getting tag name from element <{}>", element.toString());
    }

    public void afterGetTagName(WebElement element, String result) {
        log.info("tag name from element <{}> is <{}> ", element, result);
    }

    public void beforeGetAttribute(WebElement element, String name) {
        log.info("getting attribute <{}> from element <{}>", name, element.toString());
    }

    public void afterGetAttribute(WebElement element, String name, String result) {
        log.info("attribute <{}> from element <{}> is <{}>", name, element.toString(), result);
    }

    public void beforeIsSelected(WebElement element) {
        log.info("checking if element <{}> is selected", element.toString());
    }

    public void afterIsSelected(WebElement element, boolean result) {
        log.info("element <{}> is selected: <{}>", element.toString(), result);
    }

    public void beforeIsEnabled(WebElement element) {
        log.info("checking if element <{}> is enabled", element.toString());
    }

    public void afterIsEnabled(WebElement element, boolean result) {
        log.info("element <{}> is enabled: <{}>", element.toString(), result);
    }

    public void beforeGetText(WebElement element) {
        log.info("getting text from element <{}>", element.toString());
    }

    public void afterGetText(WebElement element, String result) {
        log.info("text from element <{}> is <{}>", element.toString(), result);
    }

    public void beforeIsDisplayed(WebElement element) {
        log.info("checking if element <{}> is displayed", element.toString());
    }

    public void afterIsDisplayed(WebElement element, boolean result) {
        log.info("element <{}> is displayed: <{}>", element.toString(), result);
    }

    public void beforeGetLocation(WebElement element) {
        log.info("getting location for element <{}>", element.toString());
    }

    public void afterGetLocation(WebElement element, Point result) {
        log.info("location for element <{}> is <{}>", element.toString(), result.toString());
    }

    public void beforeGetSize(WebElement element) {
        log.info("getting size for element <{}>", element.toString());
    }

    public void afterGetSize(WebElement element, Dimension result) {
        log.info("size for element <{}> is <{}>", element.toString(), result.toString());
    }

    public void beforeGetCssValue(WebElement element, String propertyName) {
        log.info("getting css value for property <{}> of element <{}>", propertyName, element.toString());
    }

    public void afterGetCssValue(WebElement element, String propertyName, String result) {
        log.info("css property for <{}> of element <{}> is <{}>", propertyName, element.toString(), result);
    }

    public void beforeTo(WebDriver.Navigation navigation, String url) {
        log.info("navigating to <{}>", url);
    }

    public void afterTo(WebDriver.Navigation navigation, String url) {
        log.info("navigated to <{}>", url);
    }

    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        log.info("navigating to <{}>", url.toString());
    }

    public void afterTo(WebDriver.Navigation navigation, URL url) {
        log.info("navigated to <{}>", url.toString());
    }

    public void beforeBack(WebDriver.Navigation navigation) {
        log.info("navigating back");
    }

    public void afterBack(WebDriver.Navigation navigation) {
        log.info("navigated back");
    }

    public void beforeForward(WebDriver.Navigation navigation) {
        log.info("navigating forward");
    }

    public void afterForward(WebDriver.Navigation navigation) {
        log.info("navigated forward");
    }

    public void beforeRefresh(WebDriver.Navigation navigation) {
        log.info("refreshing");
    }

    public void afterRefresh(WebDriver.Navigation navigation) {
        log.info("refreshed");
    }

    public void beforeAccept(Alert alert) {
        log.info("accepting alert");
    }

    public void afterAccept(Alert alert) {
        log.info("alert accepted");
    }

    public void beforeDismiss(Alert alert) {
        log.info("dismissing alert");
    }

    public void afterDismiss(Alert alert) {
        log.info("alert dismissed");
    }

    public void beforeGetText(Alert alert) {
        log.info("getting text from alert");
    }

    public void afterGetText(Alert alert, String result) {
        log.info("text from alert is <{}> ", result);
    }

    public void beforeSendKeys(Alert alert, String text) {
        log.info("sending text <{}> to alert", text);
    }

    public void afterSendKeys(Alert alert, String text) {
        log.info("text <{}> sent to alert", text);
    }

    public void beforeAddCookie(WebDriver.Options options, Cookie cookie) {
        log.info("adding cookie <{}>", cookie.toString());
    }

    public void afterAddCookie(WebDriver.Options options, Cookie cookie) {
        log.info("cookie <{}> added", cookie.toString());
    }

    public void beforeDeleteCookieNamed(WebDriver.Options options, String name) {
        log.info("delete cookie named <{}>", name);
    }

    public void afterDeleteCookieNamed(WebDriver.Options options, String name) {
        log.info("cookie named <{}> deleted", name);
    }

    public void beforeDeleteCookie(WebDriver.Options options, Cookie cookie) {
        log.info("delete cookie <{}>", cookie.toString());
    }

    public void afterDeleteCookie(WebDriver.Options options, Cookie cookie) {
        log.info("cookie <{}> deleted", cookie.toString());
    }

    public void beforeDeleteAllCookies(WebDriver.Options options) {
        log.info("delete all cookies");
    }

    public void afterDeleteAllCookies(WebDriver.Options options) {
        log.info("all cookies deleted");
    }

    public void beforeGetCookies(WebDriver.Options options) {
        log.info("getting cookies");
    }

    public void afterGetCookies(WebDriver.Options options, Set<Cookie> result) {
        log.info("cookies gotten <{}>", result.toString());
    }

    public void beforeGetCookieNamed(WebDriver.Options options, String name) {
        log.info("getting cookies named <{}>", name);
    }

    public void afterGetCookieNamed(WebDriver.Options options, String name, Cookie result) {
        log.info("cookies named <{}> are <{}>", name, result);
    }

    public void beforeImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("implicitly waiting for <{}> with timeout <{}>", duration.toString(), timeouts.toString());
    }

    public void afterImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("implicitly waited for <{}> with timeout <{}>", duration.toString(), timeouts.toString());
    }

    public void beforeSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("setting script timeout to <{}> with duration <{}>", timeouts.toString(), duration.toString());
    }

    public void afterSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("script timeout <{}> with duration <{}> set", timeouts.toString(), duration.toString());
    }

    public void beforePageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("setting page load timeout to <{}> with duration <{}>", timeouts.toString(), duration.toString());
    }

    public void afterPageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.info("page load timeout <{}> with duration <{}> set", timeouts.toString(), duration.toString());
    }

    public void beforeGetSize(WebDriver.Window window) {
        log.info("getting size of window <{}>", window.toString());
    }

    public void afterGetSize(WebDriver.Window window, Dimension result) {
        log.info("window <{}> size is <{}>", window.toString(), result.toString());
    }

    public void beforeSetSize(WebDriver.Window window, Dimension size) {
        log.info("setting size of window <{}>", window.toString());
    }

    public void afterSetSize(WebDriver.Window window, Dimension size) {
        log.info("window <{}> size set to <{}>", window.toString(), size.toString());
    }

    public void beforeGetPosition(WebDriver.Window window) {
        log.info("getting position of window <{}>", window.toString());
    }

    public void afterGetPosition(WebDriver.Window window, Point result) {
        log.info("window <{}> position is <{}>", window.toString(), result.toString());
    }

    public void beforeSetPosition(WebDriver.Window window, Point position) {
        log.info("setting position <{}> of window <{}>", position.toString(), window.toString());
    }

    public void afterSetPosition(WebDriver.Window window, Point position) {
        log.info("window <{}> position set to <{}>", window.toString(), position.toString());
    }

    public void beforeMaximize(WebDriver.Window window) {
        log.info("maximizing window <{}>", window.toString());
    }

    public void afterMaximize(WebDriver.Window window) {
        log.info("window <{}> maximized", window.toString());
    }

    public void beforeFullscreen(WebDriver.Window window) {
        log.info("setting window <{}> to full screen", window.toString());
    }

    public void afterFullscreen(WebDriver.Window window) {
        log.info("window <{}> set to full screen", window.toString());
    }

}
