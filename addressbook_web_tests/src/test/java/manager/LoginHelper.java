package manager;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager)
    {
        super(manager);
        super.manager=manager;
    }
    void login(String user, String password) {
        sendKeys("user",user);
        sendKeys("pass",password);
        clickElementByXpath("//input[@value=\'Login\']");
    }

}
