package org.openfootie.openengine.domain;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

import static org.openfootie.openengine.domain.Player.PositionX.*;
import static org.openfootie.openengine.domain.Player.PositionY.*;

public class Player {

    public enum PositionX {Gk, D, M, F}
    public enum PositionY {R, L, C}

    private int shirtNumber;
    private String firstName;
    private String lastName;
    private String nation;
    private int age;
    private List<PositionX> positionX = new ArrayList<>();
    private List<PositionY> positionY = new ArrayList<>();
    private int ability;

    public Player(CSVRecord playerRecord) {
        this(playerRecord.get(0), playerRecord.get(1), playerRecord.get(2), playerRecord.get(3), playerRecord.get(4), playerRecord.get(5));
    }

    public Player(String shirtNumber, String name, String nation, String age, String position, String ability) {

        this.shirtNumber = Integer.valueOf(shirtNumber);

        int surnameIndex = name.lastIndexOf(" ");
        if (surnameIndex > 0) {
            this.firstName = name.substring(0, surnameIndex);
            this.lastName = name.substring(surnameIndex + 1);
        } else {
            this.lastName = name;
        }

        this.nation = nation;
        this.age = Integer.valueOf(age);

        int posSeparatorIndex = position.lastIndexOf(" ");

        String positionX;
        String positionY = null;

        if (posSeparatorIndex > 0) {
            positionX = position.substring(0, posSeparatorIndex);
            positionY = position.substring(posSeparatorIndex + 1);
        } else {
            positionX = position;
        }

        positionX.chars().forEachOrdered(ch -> this.positionX.add(resolvePositionX((char) ch)));

        if (positionY != null) {
            positionY.chars().forEachOrdered(ch -> this.positionY.add(resolvePositionY((char) ch)));
        }

        this.ability = Integer.valueOf(ability);
    }

    private PositionX resolvePositionX(char ch) {
        switch (ch) {
            case 'G':
                return Gk;
            case 'D':
                return D;
            case 'M':
                return M;
            case 'F':
                return F;
            default:
                return null;
        }
    }

    private PositionY resolvePositionY(char ch) {
        switch(ch) {
            case 'R':
                return R;
            case 'L':
                return L;
            case 'C':
                return C;
            default:
                return null;
        }
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PositionX> getPositionX() {
        return positionX;
    }

    public void setPositionX(List<PositionX> positionX) {
        this.positionX = positionX;
    }

    public List<PositionY> getPositionY() {
        return positionY;
    }

    public void setPositionY(List<PositionY> positionY) {
        this.positionY = positionY;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }
}
