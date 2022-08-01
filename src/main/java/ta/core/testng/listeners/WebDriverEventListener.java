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
        log.debug("error happened with target <{}> for method <{}> with args <{}>",
                target.toString(), method.toString(), args);
    }

    public void beforeGet(WebDriver driver, String url) {
        log.debug("getting <{}>", url);
    }

    public void afterGet(WebDriver driver, String url) {
        log.debug("<{}> gotten", url);
    }

    public void beforeGetCurrentUrl(WebDriver driver) {
        log.debug("getting current url");
    }

    public void afterGetCurrentUrl(String result, WebDriver driver) {
        log.debug("current url is <{}>", result);
    }

    public void beforeGetTitle(WebDriver driver) {
        log.debug("getting title");
    }

    public void afterGetTitle(WebDriver driver, String result) {
        log.debug("title is <{}>", result);
    }

    public void beforeFindElement(WebDriver driver, By locator) {
        log.debug("trying to find element with locator <{}>", locator.toString());
    }

    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.debug("element <{}> with locator <{}> found", result.toString(), locator.toString());
    }

    public void beforeFindElements(WebDriver driver, By locator) {
        log.debug("trying to find elements with locator <{}>", locator);
    }

    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        log.debug("element(s) <{}> with locator <{}> found", result.toString(), locator.toString());
    }

    public void beforeGetPageSource(WebDriver driver) {
        log.debug("getting page source");
    }

    public void afterGetPageSource(WebDriver driver, String result) {
        log.debug("page source gotten \n<{}>", result);
    }

    public void beforeClose(WebDriver driver) {
        log.debug("closing driver");
    }

    public void afterClose(WebDriver driver) {
        log.debug("driver closed");
    }

    public void beforeQuit(WebDriver driver) {
        log.debug("quitting driver");
    }

    public void afterQuit(WebDriver driver) {
        log.debug("driver quit");
    }

    public void beforeGetWindowHandles(WebDriver driver) {
        log.debug("getting window handles");
    }

    public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
        log.debug("window handle(s) gotten <{}>", result.toString());
    }

    public void beforeGetWindowHandle(WebDriver driver) {
        log.debug("getting window handle");
    }

    public void afterGetWindowHandle(WebDriver driver, String result) {
        log.debug("window handle gotten <{}>", result);
    }

    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        log.debug("executing script <{}> with args <{}>", script, args);
    }

    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        log.debug("script <{}> with args <{}> executed with result <{}>", script, args, result.toString());
    }

    public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
        log.debug("executing async script <{}> with args <{}>", script, args);
    }

    public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
        log.debug("async script <{}> with args <{}> executed with result <{}>", script, args, result.toString());
    }

    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        log.debug("performing actions <{}>", actions.toString());
    }

    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        log.debug("actions <{}> performed", actions.toString());
    }

    public void beforeResetInputState(WebDriver driver) {
        log.debug("resetting input state");
    }

    public void afterResetInputState(WebDriver driver) {
        log.debug("input state reset");
    }

    public void beforeClick(WebElement element) {
        log.debug("trying to click on element <{}>", element.toString());
    }

    public void afterClick(WebElement element) {
        log.debug("clicked on element <{}>", element.toString());
    }

    public void beforeSubmit(WebElement element) {
        log.debug("trying to submit an element <{}>", element.toString());
    }

    public void afterSubmit(WebElement element) {
        log.debug("element submitted <{}>", element.toString());
    }

    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.debug("sending key(s) <{}> to element <{}>", keysToSend, element.toString());
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.debug("key(s) <{}> to element <{}> sent", keysToSend, element.toString());
    }

    public void beforeClear(WebElement element) {
        log.debug("clearing element <{}>", element.toString());
    }

    public void afterClear(WebElement element) {
        log.debug("element <{}> cleared", element.toString());
    }

    public void beforeGetTagName(WebElement element) {
        log.debug("getting tag name from element <{}>", element.toString());
    }

    public void afterGetTagName(WebElement element, String result) {
        log.debug("tag name from element <{}> is <{}> ", element, result);
    }

    public void beforeGetAttribute(WebElement element, String name) {
        log.debug("getting attribute <{}> from element <{}>", name, element.toString());
    }

    public void afterGetAttribute(WebElement element, String name, String result) {
        log.debug("attribute <{}> from element <{}> is <{}>", name, element.toString(), result);
    }

    public void beforeIsSelected(WebElement element) {
        log.debug("checking if element <{}> is selected", element.toString());
    }

    public void afterIsSelected(WebElement element, boolean result) {
        log.debug("element <{}> is selected: <{}>", element.toString(), result);
    }

    public void beforeIsEnabled(WebElement element) {
        log.debug("checking if element <{}> is enabled", element.toString());
    }

    public void afterIsEnabled(WebElement element, boolean result) {
        log.debug("element <{}> is enabled: <{}>", element.toString(), result);
    }

    public void beforeGetText(WebElement element) {
        log.debug("getting text from element <{}>", element.toString());
    }

    public void afterGetText(WebElement element, String result) {
        log.debug("text from element <{}> is <{}>", element.toString(), result);
    }

    public void beforeIsDisplayed(WebElement element) {
        log.debug("checking if element <{}> is displayed", element.toString());
    }

    public void afterIsDisplayed(WebElement element, boolean result) {
        log.debug("element <{}> is displayed: <{}>", element.toString(), result);
    }

    public void beforeGetLocation(WebElement element) {
        log.debug("getting location for element <{}>", element.toString());
    }

    public void afterGetLocation(WebElement element, Point result) {
        log.debug("location for element <{}> is <{}>", element.toString(), result.toString());
    }

    public void beforeGetSize(WebElement element) {
        log.debug("getting size for element <{}>", element.toString());
    }

    public void afterGetSize(WebElement element, Dimension result) {
        log.debug("size for element <{}> is <{}>", element.toString(), result.toString());
    }

    public void beforeGetCssValue(WebElement element, String propertyName) {
        log.debug("getting css value for property <{}> of element <{}>", propertyName, element.toString());
    }

    public void afterGetCssValue(WebElement element, String propertyName, String result) {
        log.debug("css property for <{}> of element <{}> is <{}>", propertyName, element.toString(), result);
    }

    public void beforeTo(WebDriver.Navigation navigation, String url) {
        log.debug("navigating to <{}>", url);
    }

    public void afterTo(WebDriver.Navigation navigation, String url) {
        log.debug("navigated to <{}>", url);
    }

    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        log.debug("navigating to <{}>", url.toString());
    }

    public void afterTo(WebDriver.Navigation navigation, URL url) {
        log.debug("navigated to <{}>", url.toString());
    }

    public void beforeBack(WebDriver.Navigation navigation) {
        log.debug("navigating back");
    }

    public void afterBack(WebDriver.Navigation navigation) {
        log.debug("navigated back");
    }

    public void beforeForward(WebDriver.Navigation navigation) {
        log.debug("navigating forward");
    }

    public void afterForward(WebDriver.Navigation navigation) {
        log.debug("navigated forward");
    }

    public void beforeRefresh(WebDriver.Navigation navigation) {
        log.debug("refreshing");
    }

    public void afterRefresh(WebDriver.Navigation navigation) {
        log.debug("refreshed");
    }

    public void beforeAccept(Alert alert) {
        log.debug("accepting alert");
    }

    public void afterAccept(Alert alert) {
        log.debug("alert accepted");
    }

    public void beforeDismiss(Alert alert) {
        log.debug("dismissing alert");
    }

    public void afterDismiss(Alert alert) {
        log.debug("alert dismissed");
    }

    public void beforeGetText(Alert alert) {
        log.debug("getting text from alert");
    }

    public void afterGetText(Alert alert, String result) {
        log.debug("text from alert is <{}> ", result);
    }

    public void beforeSendKeys(Alert alert, String text) {
        log.debug("sending text <{}> to alert", text);
    }

    public void afterSendKeys(Alert alert, String text) {
        log.debug("text <{}> sent to alert", text);
    }

    public void beforeAddCookie(WebDriver.Options options, Cookie cookie) {
        log.debug("adding cookie <{}>", cookie.toString());
    }

    public void afterAddCookie(WebDriver.Options options, Cookie cookie) {
        log.debug("cookie <{}> added", cookie.toString());
    }

    public void beforeDeleteCookieNamed(WebDriver.Options options, String name) {
        log.debug("delete cookie named <{}>", name);
    }

    public void afterDeleteCookieNamed(WebDriver.Options options, String name) {
        log.debug("cookie named <{}> deleted", name);
    }

    public void beforeDeleteCookie(WebDriver.Options options, Cookie cookie) {
        log.debug("delete cookie <{}>", cookie.toString());
    }

    public void afterDeleteCookie(WebDriver.Options options, Cookie cookie) {
        log.debug("cookie <{}> deleted", cookie.toString());
    }

    public void beforeDeleteAllCookies(WebDriver.Options options) {
        log.debug("delete all cookies");
    }

    public void afterDeleteAllCookies(WebDriver.Options options) {
        log.debug("all cookies deleted");
    }

    public void beforeGetCookies(WebDriver.Options options) {
        log.debug("getting cookies");
    }

    public void afterGetCookies(WebDriver.Options options, Set<Cookie> result) {
        log.debug("cookies gotten <{}>", result.toString());
    }

    public void beforeGetCookieNamed(WebDriver.Options options, String name) {
        log.debug("getting cookies named <{}>", name);
    }

    public void afterGetCookieNamed(WebDriver.Options options, String name, Cookie result) {
        log.debug("cookies named <{}> are <{}>", name, result);
    }

    public void beforeImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("implicitly waiting for <{}> with timeout <{}>", duration.toString(), timeouts.toString());
    }

    public void afterImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("implicitly waited for <{}> with timeout <{}>", duration.toString(), timeouts.toString());
    }

    public void beforeSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("setting script timeout to <{}> with duration <{}>", timeouts.toString(), duration.toString());
    }

    public void afterSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("script timeout <{}> with duration <{}> set", timeouts.toString(), duration.toString());
    }

    public void beforePageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("setting page load timeout to <{}> with duration <{}>", timeouts.toString(), duration.toString());
    }

    public void afterPageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        log.debug("page load timeout <{}> with duration <{}> set", timeouts.toString(), duration.toString());
    }

    public void beforeGetSize(WebDriver.Window window) {
        log.debug("getting size of window <{}>", window.toString());
    }

    public void afterGetSize(WebDriver.Window window, Dimension result) {
        log.debug("window <{}> size is <{}>", window.toString(), result.toString());
    }

    public void beforeSetSize(WebDriver.Window window, Dimension size) {
        log.debug("setting size of window <{}>", window.toString());
    }

    public void afterSetSize(WebDriver.Window window, Dimension size) {
        log.debug("window <{}> size set to <{}>", window.toString(), size.toString());
    }

    public void beforeGetPosition(WebDriver.Window window) {
        log.debug("getting position of window <{}>", window.toString());
    }

    public void afterGetPosition(WebDriver.Window window, Point result) {
        log.debug("window <{}> position is <{}>", window.toString(), result.toString());
    }

    public void beforeSetPosition(WebDriver.Window window, Point position) {
        log.debug("setting position <{}> of window <{}>", position.toString(), window.toString());
    }

    public void afterSetPosition(WebDriver.Window window, Point position) {
        log.debug("window <{}> position set to <{}>", window.toString(), position.toString());
    }

    public void beforeMaximize(WebDriver.Window window) {
        log.debug("maximizing window <{}>", window.toString());
    }

    public void afterMaximize(WebDriver.Window window) {
        log.debug("window <{}> maximized", window.toString());
    }

    public void beforeFullscreen(WebDriver.Window window) {
        log.debug("setting window <{}> to full screen", window.toString());
    }

    public void afterFullscreen(WebDriver.Window window) {
        log.debug("window <{}> set to full screen", window.toString());
    }

}
