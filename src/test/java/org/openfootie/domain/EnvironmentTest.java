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
        Club bayern = environment.getClub("Bayern Munich");
        Club manCity = environment.getClub("Manchester City");
        Club juventus = environment.getClub("Juventus");
        Club liverpool = environment.getClub("Liverpool");
        Club roma = environment.getClub("Roma");

        Nation brazil = environment.getNation("Brazil");
        Nation france = environment.getNation("France");
        Nation belgium = environment.getNation("Belgium");
        Nation spain = environment.getNation("Spain");
        Nation croatia = environment.getNation("Croatia");

        assertEquals("Real Madrid", realMadrid.getName());
        assertEquals("FC Barcelona", barcelona.getName());
        assertEquals("Atletico Madrid", atletico.getName());
        assertEquals("Bayern Munich", bayern.getName());
        assertEquals("Manchester City", manCity.getName());
        assertEquals("Juventus", juventus.getName());
        assertEquals("Liverpool", liverpool.getName());
        assertEquals("Roma", roma.getName());
        assertEquals("Croatia", croatia.getName());

        assertEquals("Brazil", brazil.getName());
        assertEquals("France", france.getName());
        assertEquals("Belgium", belgium.getName());
        assertEquals("Spain", spain.getName());
        assertEquals("Croatia", croatia.getName());

        assertEquals(28, realMadrid.getSquad().getPlayers().size());
        assertEquals(24, barcelona.getSquad().getPlayers().size());
        assertEquals(20, atletico.getSquad().getPlayers().size());
        assertEquals(22, bayern.getSquad().getPlayers().size());
        assertEquals(25, manCity.getSquad().getPlayers().size());
        assertEquals(24, juventus.getSquad().getPlayers().size());
        assertEquals(25, liverpool.getSquad().getPlayers().size());
        assertEquals(27, roma.getSquad().getPlayers().size());

        assertEquals(23, brazil.getSquad().getPlayers().size());
        assertEquals(23, france.getSquad().getPlayers().size());
        assertEquals(23, belgium.getSquad().getPlayers().size());
        assertEquals(23, spain.getSquad().getPlayers().size());
        assertEquals(23, croatia.getSquad().getPlayers().size());

        Squad realMadridSquad = realMadrid.getSquad();
        Squad barcelonaSquad = barcelona.getSquad();
        Squad atleticoSquad = atletico.getSquad();
        Squad bayernSquad = bayern.getSquad();
        Squad juventusSquad = juventus.getSquad();
        Squad romaSquad = roma.getSquad();

        Player realPlayer1 = realMadridSquad.getPlayers().get(0);

        Player barcaPlayer1 = barcelonaSquad.getPlayers().get(1);

        Player atleticoPlayer1 = atleticoSquad.getPlayers().get(0);
        Player atleticoPlayer10 = atleticoSquad.getPlayers().get(10);

        Player bayernPlayer35 = bayernSquad.getPlayers().get(12);
        Player bayernPlayer29 = bayernSquad.getPlayers().get(18);

        Player juventusPlayer1 = juventusSquad.getPlayers().get(2);
        Player juventusPlayer16 = juventusSquad.getPlayers().get(13);

        Player romaPlayer9 = romaSquad.getPlayers().get(26);

        assertEquals(9, romaPlayer9.getShirtNumber());
        assertEquals("Edin", romaPlayer9.getFirstName());
        assertEquals("Džeko", romaPlayer9.getLastName());
        assertEquals("Bosnia-Herzegovina", romaPlayer9.getNation());
        assertEquals(32, romaPlayer9.getAge());
        assertEquals(Player.PositionX.F, romaPlayer9.getPositionX().get(0));
        assertEquals(Player.PositionY.C, romaPlayer9.getPositionY().get(0));
        assertEquals(77, romaPlayer9.getAbility());

        assertEquals(1, juventusPlayer1.getShirtNumber());
        assertEquals("Szczęsny", juventusPlayer1.getLastName());
        assertEquals("Wojciech", juventusPlayer1.getFirstName());
        assertEquals("Poland", juventusPlayer1.getNation());
        assertEquals(28, juventusPlayer1.getAge());
        assertEquals(Player.PositionX.Gk, juventusPlayer1.getPositionX().get(0));
        assertEquals(0, juventusPlayer1.getPositionY().size());
        assertEquals(80, juventusPlayer1.getAbility());

        assertEquals(16, juventusPlayer16.getShirtNumber());
        assertEquals("Juan", juventusPlayer16.getFirstName());
        assertEquals("Cuadrado", juventusPlayer16.getLastName());
        assertEquals("Colombia", juventusPlayer16.getNation());
        assertEquals(30, juventusPlayer16.getAge());
        assertEquals(Player.PositionX.D, juventusPlayer16.getPositionX().get(0));
        assertEquals(Player.PositionX.M, juventusPlayer16.getPositionX().get(1));
        assertEquals(Player.PositionX.F, juventusPlayer16.getPositionX().get(2));
        assertEquals(Player.PositionY.R, juventusPlayer16.getPositionY().get(0));
        assertEquals(76, juventusPlayer16.getAbility());

        assertEquals(29, bayernPlayer29.getShirtNumber());
        assertEquals("Kingsley", bayernPlayer29.getFirstName());
        assertEquals("Coman", bayernPlayer29.getLastName());
        assertEquals("France", bayernPlayer29.getNation());
        assertEquals(22, bayernPlayer29.getAge());
        assertEquals(Player.PositionX.F, bayernPlayer29.getPositionX().get(0));
        assertEquals(Player.PositionY.R, bayernPlayer29.getPositionY().get(0));
        assertEquals(Player.PositionY.L, bayernPlayer29.getPositionY().get(1));
        assertEquals(Player.PositionY.C, bayernPlayer29.getPositionY().get(2));
        assertEquals(81, bayernPlayer29.getAbility());

        assertEquals(35, bayernPlayer35.getShirtNumber());
        assertEquals("Renato", bayernPlayer35.getFirstName());
        assertEquals("Sanches", bayernPlayer35.getLastName());
        assertEquals("Portugal", bayernPlayer35.getNation());
        assertEquals(21, bayernPlayer35.getAge());
        assertEquals(Player.PositionX.M, bayernPlayer35.getPositionX().get(0));
        assertEquals(Player.PositionY.C, bayernPlayer35.getPositionY().get(0));
        assertEquals(70, bayernPlayer35.getAbility());

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
        assertEquals(29, player6.getAge());
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
        assertEquals(23, player20.getAge());
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
