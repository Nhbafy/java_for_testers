package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.MailMessage;

import java.util.List;
import java.util.regex.Pattern;

public class CreationHelper extends HelperBase {
    public CreationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createMantisUser(String username, String email) {
        clickElementByLocator(By.cssSelector("a[href='signup_page.php']"));
        sendKeys(By.name("username"), username);
        sendKeys(By.name("email"), email);
        clickElementByLocator(By.cssSelector("input[value='Signup']"));
    }

    public void confirmCreate(String username, String password) {
        sendKeys(By.name("realname"), username);
        sendKeys(By.name("password"), password);
        sendKeys(By.name("password_confirm"), password);
        clickElementByLocator(By.xpath("//fieldset/span/button"));
    }

    public String getUrl(List<MailMessage> inbox) {
        var text = inbox.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }
}
