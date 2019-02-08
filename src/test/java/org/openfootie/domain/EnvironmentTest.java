package org.openfootie.domain;

import org.junit.Test;
import org.openfootie.openengine.domain.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EnvironmentTest {

    private final String TEST_DATA_PATH = "src/test/resources/data";

    @Test
    public void testLoad() {

        Environment environment = new Environment(TEST_DATA_PATH);

        // Sanity check for real data
        assertTrue(new Environment().load());

        // Test data are loaded successfully
        assertTrue(environment.load());

        assertEquals(32, environment.getClubs().size());
        assertEquals(32, environment.getNations().size());

        assertEquals("England", environment.getClub("Chelsea").getNation());
        assertEquals("Chelsea", environment.getClub(17).getName());

        assertEquals("Poland", environment.getNation(18).getName());

        Club realMadrid = environment.getClub("Real Madrid");
        Club barcelona = environment.getClub("FC Barcelona");
        Club atletico = environment.getClub("Atletico Madrid");

        assertEquals("Real Madrid", realMadrid.getName());
        assertEquals("FC Barcelona", barcelona.getName());
        assertEquals("Atletico Madrid", atletico.getName());

        assertEquals(28, realMadrid.getSquad().getPlayers().size());
        assertEquals(24, barcelona.getSquad().getPlayers().size());
        assertEquals(20, atletico.getSquad().getPlayers().size());

        Squad realMadridSquad = realMadrid.getSquad();
        Squad barcelonaSquad = barcelona.getSquad();
        Squad atleticoSquad = atletico.getSquad();

        Player realPlayer1 = realMadridSquad.getPlayers().get(0);
        Player barcaPlayer1 = barcelonaSquad.getPlayers().get(1);

        Player atleticoPlayer1 = atleticoSquad.getPlayers().get(0);
        Player atleticoPlayer10 = atleticoSquad.getPlayers().get(10);

        assertEquals(10, atleticoPlayer10.getShirtNumber());
        assertEquals("Ángel", atleticoPlayer10.getFirstName());
        assertEquals("Correa", atleticoPlayer10.getLastName());
        assertEquals("Argentina", atleticoPlayer10.getNation());
        assertEquals(23, atleticoPlayer10.getAge());
        assertEquals(Player.PositionX.F, atleticoPlayer10.getPositionX().get(0));
        assertEquals(Player.PositionY.R, atleticoPlayer10.getPositionY().get(0));
        assertEquals(Player.PositionY.L, atleticoPlayer10.getPositionY().get(1));
        assertEquals(Player.PositionY.C, atleticoPlayer10.getPositionY().get(2));
        assertEquals(70, atleticoPlayer10.getAbility());

        assertEquals(1, atleticoPlayer1.getShirtNumber());
        assertEquals("Antonio", atleticoPlayer1.getFirstName());
        assertEquals("Adán", atleticoPlayer1.getLastName());
        assertEquals("Spain", atleticoPlayer1.getNation());
        assertEquals(31, atleticoPlayer1.getAge());
        assertEquals(Player.PositionX.Gk, atleticoPlayer1.getPositionX().get(0));
        assertEquals(0, atleticoPlayer1.getPositionY().size());
        assertEquals(56, atleticoPlayer1.getAbility());

        assertEquals(1, barcaPlayer1.getShirtNumber());
        assertEquals("Marc-André", barcaPlayer1.getFirstName());
        assertEquals("ter Stegen", barcaPlayer1.getLastName());
        assertEquals("Germany", barcaPlayer1.getNation());
        assertEquals(26, barcaPlayer1.getAge());
        assertEquals(Player.PositionX.Gk, barcaPlayer1.getPositionX().get(0));
        assertEquals(0, barcaPlayer1.getPositionY().size());
        assertEquals(85, barcaPlayer1.getAbility());

        assertEquals(1, realPlayer1.getShirtNumber());
        assertEquals("Keylor", realPlayer1.getFirstName());
        assertEquals("Navas", realPlayer1.getLastName());
        assertEquals("Costa Rica", realPlayer1.getNation());
        assertEquals(32, realPlayer1.getAge());
        assertEquals(Player.PositionX.Gk, realPlayer1.getPositionX().get(0));
        assertEquals(0, realPlayer1.getPositionY().size());
        assertEquals(75, realPlayer1.getAbility());

        Player realPlayer25 = realMadridSquad.getPlayers().get(1);

        assertEquals(25, realPlayer25.getShirtNumber());
        assertEquals("Thibaut", realPlayer25.getFirstName());
        assertEquals("Courtois", realPlayer25.getLastName());
        assertEquals("Belgium", realPlayer25.getNation());
        assertEquals(26, realPlayer25.getAge());
        assertEquals(Player.PositionX.Gk, realPlayer25.getPositionX().get(0));
        assertEquals(0, realPlayer25.getPositionY().size());
        assertEquals(85, realPlayer25.getAbility());

        Player realPlayer36 = realMadridSquad.getPlayers().get(12);

        assertEquals(36, realPlayer36.getShirtNumber());
        assertEquals("Alvaro", realPlayer36.getFirstName());
        assertEquals("Fidalgo", realPlayer36.getLastName());
        assertEquals("Spain", realPlayer36.getNation());
        assertEquals(21, realPlayer36.getAge());
        assertEquals(Player.PositionX.M, realPlayer36.getPositionX().get(0));
        assertEquals(Player.PositionY.C, realPlayer36.getPositionY().get(0));
        assertEquals(41, realPlayer36.getAbility());

        Player player6 = realMadridSquad.getPlayers().get(8);

        assertEquals(6, player6.getShirtNumber());
        assertEquals("Nacho", player6.getFirstName());
        assertEquals("Fernández", player6.getLastName());
        assertEquals("Spain", player6.getNation());
        assertEquals(28, player6.getAge());
        assertEquals(Player.PositionX.D, player6.getPositionX().get(0));
        assertEquals(Player.PositionY.C, player6.getPositionY().get(0));
        assertEquals(72, player6.getAbility());

        Player player10 = realMadridSquad.getPlayers().get(17);

        assertEquals(10, player10.getShirtNumber());
        assertEquals("Luka", player10.getFirstName());
        assertEquals("Modrić", player10.getLastName());
        assertEquals("Croatia", player10.getNation());
        assertEquals(33, player10.getAge());
        assertEquals(Player.PositionX.M, player10.getPositionX().get(0));
        assertEquals(Player.PositionY.C, player10.getPositionY().get(0));
        assertEquals(83, player10.getAbility());

        Player player2 = realMadridSquad.getPlayers().get(9);

        assertEquals(2, player2.getShirtNumber());
        assertEquals("Daniel", player2.getFirstName());
        assertEquals("Carvajal", player2.getLastName());
        assertEquals("Spain", player2.getNation());
        assertEquals(27, player2.getAge());
        assertEquals(Player.PositionX.D, player2.getPositionX().get(0));
        assertEquals(Player.PositionY.R, player2.getPositionY().get(0));
        assertEquals(80, player2.getAbility());

        Player player20 = realMadridSquad.getPlayers().get(22);

        assertEquals(20, player20.getShirtNumber());
        assertEquals("Marco", player20.getFirstName());
        assertEquals("Asensio", player20.getLastName());
        assertEquals("Spain", player20.getNation());
        assertEquals(22, player20.getAge());
        assertEquals(Player.PositionX.M, player20.getPositionX().get(0));
        assertEquals(Player.PositionX.F, player20.getPositionX().get(1));
        assertEquals(Player.PositionY.R, player20.getPositionY().get(0));
        assertEquals(Player.PositionY.L, player20.getPositionY().get(1));
        assertEquals(Player.PositionY.C, player20.getPositionY().get(2));
        assertEquals(81, player20.getAbility());

        Player player21 = realMadridSquad.getPlayers().get(24);

        assertEquals(21, player21.getShirtNumber());
        assertEquals("Brahim", player21.getFirstName());
        assertEquals("Díaz", player21.getLastName());
        assertEquals("Spain", player21.getNation());
        assertEquals(19, player21.getAge());
        assertEquals(Player.PositionX.M, player21.getPositionX().get(0));
        assertEquals(Player.PositionX.F, player21.getPositionX().get(1));
        assertEquals(Player.PositionY.R, player21.getPositionY().get(0));
        assertEquals(Player.PositionY.L, player21.getPositionY().get(1));
        assertEquals(Player.PositionY.C, player21.getPositionY().get(2));
        assertEquals(60, player21.getAbility());

        Player player24 = realMadridSquad.getPlayers().get(15);

        assertEquals(24, player24.getShirtNumber());
        assertEquals("Dani", player24.getFirstName());
        assertEquals("Ceballos", player24.getLastName());
        assertEquals("Spain", player24.getNation());
        assertEquals(22, player24.getAge());
        assertEquals(Player.PositionX.M, player24.getPositionX().get(0));
        assertEquals(Player.PositionY.C, player24.getPositionY().get(0));
        assertEquals(72, player24.getAbility());

        Player player12 = realMadridSquad.getPlayers().get(10);

        assertEquals(12, player12.getShirtNumber());
        assertEquals("", player12.getFirstName());
        assertEquals("Marcelo", player12.getLastName());
        assertEquals("Brazil", player12.getNation());
        assertEquals(30, player12.getAge());
        assertEquals(Player.PositionX.D, player12.getPositionX().get(0));
        assertEquals(Player.PositionX.M, player12.getPositionX().get(1));
        assertEquals(Player.PositionY.L, player12.getPositionY().get(0));
        assertEquals(80, player12.getAbility());
    }
}
