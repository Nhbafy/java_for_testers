package manager;

public class LoginHelper extends HelperBase {

    private final ApplicationManager manager;
    public LoginHelper(ApplicationManager manager)
    {
        super(manager);
        this.manager=manager;
    }
    void login(String user, String password) {
        sendKeys("user",user);
        sendKeys("pass",password);
        clickElementByXpath("//input[@value=\'Login\']");
    }

}
