package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.github.javafaker.Faker;
import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest_DateChanged {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    //@Test
    //void shouldGenerateTestData() {
      //  String name = faker.name().fullName();
      //  String phone = faker.phoneNumber().phoneNumber();
      //  System.out.println(name + " " + phone);
    //}

    //void shouldGenerateTestDataUsingUtils() {
      //  RegistationInfo = DataGenerator.Registration.generateInfo("ru");
    //}
   // String generateDate(int addDays) {
     //   return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    //}

    @Test
    void deliveryTest() {
       var validUser = DataGenerator.Registration.generateUser("ru");
        //var city = DataGenerator.generateCity("ru");
        var firstMeetingDate = DataGenerator.generateDate(3);
        //var name = DataGenerator.generateName("ru");
        //var phoneNumber = DataGenerator.generatePhone("ru");
        var secondMeetingDate = DataGenerator.generateDate(7);
        //$("[placeholder='Город']").setValue(validUser.getCity());
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[data-test-id=\"date\"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=\"date\"] input").sendKeys(firstMeetingDate);
        $("[data-test-id=\"name\"] input").setValue(validUser.getName());
        $("[data-test-id=\"phone\"] input").setValue(validUser.getPhone());
        $("[data-test-id=\"agreement\"] span").click();
        $(By.className("button")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
       // $(By.className("notification__content"))
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"date\"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=\"date\"] input").sendKeys(secondMeetingDate);
        $(By.className("button")).click();
       // $(byText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
       $("[data-test-id=\"replan-notification\"] .notification__content")
       // $(By.className("notification__content"))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=\"replan-notification\"] .button").click();
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}


