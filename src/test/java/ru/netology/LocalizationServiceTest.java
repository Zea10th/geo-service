package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void shouldReturnWelcomeRu() {
        LocalizationService localService = new LocalizationServiceImpl();

        String expected = "Добро пожаловать";
        String actual = localService.locale(Country.RUSSIA);
        assertTrue(actual.equals(expected));
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"GERMANY", "USA", "BRAZIL"})
    void shouldReturnWriteAnswer(Country country) {
        LocalizationService localService = new LocalizationServiceImpl();

        String expected = "Welcome";
        String actual = localService.locale(country);
        assertTrue(actual.equals(expected));
    }
}