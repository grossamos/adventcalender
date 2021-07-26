package com.bundespolizei.adventcalender.helper;

public class Randomiser {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
