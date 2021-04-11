package ru.testProtei;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.testProtei.settings.WebDriverSettings;

public class AuthenticationTests extends WebDriverSettings {

    @Test
    public void correctAuthentication() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys("test@protei.ru");
        driver.findElementById("loginPassword").sendKeys("test");
        driver.findElementById("authButton").click();

        //проверка открытия новой формы записи
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[onsubmit=\"sendData();return false;\"]")));
    }

    @Test
    public void emptyAuthentication() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        driver.findElementById("authButton").click();

        //ожидаем форму с ошибкой аутентификации
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("emailFormatError")));

        //проверка формы ответа
        String emailError = driver.findElementById("emailFormatError").getText();
        Assert.assertEquals("Неверный формат E-Mail", emailError);
    }

    @Test
    public void emptyPasswordAuthentication() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин, авторизуемся
        driver.findElementById("loginEmail").sendKeys("test@protei.ru");
        driver.findElementById("authButton").click();

        //ожидаем форму с ошибкой аутентификации
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("invalidEmailPassword")));

        //проверка формы ответа
        String emailOrPasswordError = driver.findElementById("invalidEmailPassword").getText();
        Assert.assertEquals("Неверный E-Mail или пароль", emailOrPasswordError);
    }

    @Test
    public void emptyEmailAuthentication() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим пароль, авторизуемся
        driver.findElementById("loginPassword").sendKeys("test");
        driver.findElementById("authButton").click();

        //ожидаем форму с ошибкой аутентификации
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("emailFormatError")));

        //проверка формы ответа
        String emailError = driver.findElementById("emailFormatError").getText();
        Assert.assertEquals("Неверный формат E-Mail", emailError);
    }

    @Test
    public void incorrectAuthentication() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим некорректный email и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys("test@protei.com");
        driver.findElementById("loginPassword").sendKeys("test1");
        driver.findElementById("authButton").click();

        //ожидаем форму с ошибкой аутентификации
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("invalidEmailPassword")));

        //проверка формы ответа
        String emailOrPasswordError = driver.findElementById("invalidEmailPassword").getText();
        Assert.assertEquals("Неверный E-Mail или пароль", emailOrPasswordError);
    }
}
