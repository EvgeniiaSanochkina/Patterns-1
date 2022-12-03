package data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }


    public static String generateDate(int addDays) {
        String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String city = faker.options().option("Якутск", "Владивосток", "Анадырь");
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = (name + " " + lastName);
        return fullName;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) { //метод generateUser возвращает новый экземпляр класса UserInfo
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo { //в конструктор передаются сгенерированные методом данные
        String city;
        String name;
        String phone;
    }
}
