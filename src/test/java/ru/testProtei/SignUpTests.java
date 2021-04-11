package ru.testProtei;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.testProtei.settings.WebDriverSettings;

public class SignUpTests extends WebDriverSettings {
    public static final String email = "test@protei.ru";
    public static final String password = "test";
    public static final String name = "Андрей";
    public static final String genderType = "Мужской";
    public static final String choice1 = "1.1";
    public static final String choice2 = "2.1";

    @Test
    public void correctSignUp() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys(email);
        driver.findElementById("loginPassword").sendKeys(password);
        driver.findElementById("authButton").click();

        //проверка открытия новой формы записи
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[onsubmit=\"sendData();return false;\"]")));

        //заполняем все поля формы
        driver.findElementById("dataEmail").sendKeys(email);
        driver.findElementById("dataName").sendKeys(name);
        WebElement gender = driver.findElementByCssSelector("#dataGender > option:nth-child(1)");
        gender.click();
        driver.findElementById("dataCheck11").click();
        driver.findElementById("dataSelect21").click();

        //отправляем форму
        driver.findElementById("dataSend").click();

        //ожидаем подтверждение заполненной формы
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("div[class=\"uk-margin uk-modal-content\"]")));

        //проверка формы ответа
        String dataAdded = driver.findElementByCssSelector("div[class=\"uk-margin uk-modal-content\"")
                .getText();
        Assert.assertEquals("Данные добавлены.", dataAdded);

        driver.findElement(By.cssSelector("button.uk-button.uk-button-primary.uk-modal-close")).click();

        //ожидаем появления таблицы с правильно заполненной формой
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("#dataTable > tbody > tr:nth-child(1)")));

        WebElement tableEmail = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(1)");
        Assert.assertEquals(email, tableEmail.getText());

        WebElement tableName = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(2)");
        Assert.assertEquals(name, tableName.getText());

        WebElement tableGender = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(3)");
        Assert.assertEquals(genderType, tableGender.getText());

        WebElement tableChoice1 = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(4)");
        Assert.assertEquals(choice1, tableChoice1.getText());

        WebElement tableChoice2 = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(5)");
        Assert.assertEquals(choice2, tableChoice2.getText());
    }

    @Test
    public void emptySignUp() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys(email);
        driver.findElementById("loginPassword").sendKeys(password);
        driver.findElementById("authButton").click();

        driver.findElementById("dataSend").click();

        //проверка открытия новой формы записи
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.id("dataAlertsHolder")));

        //проверка формы ответа
        String emailError = driver.findElementById("emailFormatError").getText();
        Assert.assertEquals("Неверный формат E-Mail", emailError);
    }

    @Test
    public void emptyNameSignUp() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys(email);
        driver.findElementById("loginPassword").sendKeys(password);
        driver.findElementById("authButton").click();

        //заполняем поле email
        driver.findElementById("dataEmail").sendKeys(email);
        driver.findElementById("dataSend").click();

        //проверка открытия новой формы записи
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.id("dataAlertsHolder")));

        //проверка формы ответа
        String emailError = driver.findElementById("blankNameError").getText();
        Assert.assertEquals("Поле имя не может быть пустым", emailError);
    }

    @Test
    public void incorrectEmailSignUp() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys(email);
        driver.findElementById("loginPassword").sendKeys(password);
        driver.findElementById("authButton").click();

        //заполняем поле email
        driver.findElementById("dataEmail").sendKeys("test@");
        driver.findElementById("dataName").sendKeys(name);
        driver.findElementById("dataSend").click();

        //проверка открытия новой формы записи
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.id("dataAlertsHolder")));

        //проверка формы ответа
        String emailError = driver.findElementById("emailFormatError").getText();
        Assert.assertEquals("Неверный формат E-Mail", emailError);
    }

    @Test
    public void emptyChoicesSignUp() {
        driver.get("C:/TestTaskProtei/Test task QA.html");

        //вводим логин и пароль, авторизуемся
        driver.findElementById("loginEmail").sendKeys(email);
        driver.findElementById("loginPassword").sendKeys(password);
        driver.findElementById("authButton").click();

        //заполняем все поля формы
        driver.findElementById("dataEmail").sendKeys(email);
        driver.findElementById("dataName").sendKeys(name);

        //отправляем форму
        driver.findElementById("dataSend").click();

        //ожидаем подтверждение заполненной формы
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("div[class=\"uk-margin uk-modal-content\"]")));
        driver.findElement(By.cssSelector("button.uk-button.uk-button-primary.uk-modal-close")).click();

        //ожидаем появления таблицы с правильно заполненной формой
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("#dataTable > tbody > tr:nth-child(1)")));

        WebElement tableEmail = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(1)");
        Assert.assertEquals(email, tableEmail.getText());

        WebElement tableName = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(2)");
        Assert.assertEquals(name, tableName.getText());

        WebElement tableGender = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(3)");
        Assert.assertEquals(genderType, tableGender.getText());

        WebElement tableChoice1 = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(4)");
        Assert.assertEquals("Нет", tableChoice1.getText());

        WebElement tableChoice2 = driver.findElementByCssSelector("#dataTable > tbody > tr > td:nth-child(5)");
        Assert.assertEquals("", tableChoice2.getText());
    }
}
