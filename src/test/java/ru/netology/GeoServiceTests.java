package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeoServiceTests {
    @Test
    void shouldReturnMoscowLocation() {
        String ip = "172.0.32.11";
        GeoService geoService = new GeoServiceImpl();
        Location expectedLocation = Mockito.mock(Location.class);
        Mockito.when(expectedLocation.getCity()).thenReturn("Moscow");
        Location actualLocation = geoService.byIp(ip);
        Assertions.assertEquals(expectedLocation.getCity(), actualLocation.getCity());
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.10.180.1"})
    void shouldReturnMoscowLocation(String ip) {
        GeoService geoService = new GeoServiceImpl();

        String expected = "Moscow";
        String actual = geoService.byIp(ip).getCity();

        assertTrue(actual.equals(expected));
    }

    @Test
    void shouldThrowException() {
        GeoService geoService = new GeoServiceImpl();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(0.0d, 1.0d);
        });

        String expectedMessage = "Not implemented";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
