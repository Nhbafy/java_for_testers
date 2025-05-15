package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.utils.Utils;

public class JamesTests extends TestBase {
    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", Utils.randomString(8)), "password");
    }
}
