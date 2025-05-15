package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.utils.Utils;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String username = Utils.randomString(8);
        String password = "password";
        var email = String.format("%s@localhost", username);
        //создать адрес на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email, password);
        //заполняем форму и отправляем (браузер)
        app.create().createMantisUser(username, email);
        //получаем почту (mailHelper)
        var inbox = app.mail().receive(email, password, Duration.ofSeconds(10));
        //извлечь ссылку
        String url = app.create().getUrl(inbox);
        //проходим по ссылке и завершаем регистрацию (браузер)
        app.driver().get(url);
        app.create().confirmCreate(username, password);
        //проверяем, что пользователь может залогиниться (Http)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
