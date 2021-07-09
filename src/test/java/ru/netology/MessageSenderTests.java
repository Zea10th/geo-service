package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTests {
    private Map<String, String> headers;

    @Test
    void shouldReturnRussianMsg() {
        String ip = "172.123.12.19";
        headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String expected = "Добро пожаловать";

        String actual = new MessageSenderImpl(geoService, localizationService).send(headers);

        Assertions.assertEquals(actual, expected);

    }

    @Test
    void shouldReturnEnglishMsg() {
        String ip = "96.xxx";
        headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String expected = "Welcome";

        String actual = new MessageSenderImpl(geoService, localizationService).send(headers);

        Assertions.assertEquals(actual, expected);

    }

    @Test
    void shouldReturnAlsoEnglishMsg() {
        String ip = "96.xxx";
        headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Berlin", Country.GERMANY, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.GERMANY)).thenReturn("Welcome");

        String expected = "Welcome";

        String actual = new MessageSenderImpl(geoService, localizationService).send(headers);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldReturnMoscowLocation() {
        String ip = "172.0.32.11";
        GeoService geoService = new GeoServiceImpl();
        Location expectedLocation = Mockito.mock(Location.class);
        Mockito.when(expectedLocation.getCity()).thenReturn("Moscow");
        Location actualLocation = geoService.byIp(ip);
        Assertions.assertEquals(expectedLocation.getCity(), actualLocation.getCity());
    }
}
