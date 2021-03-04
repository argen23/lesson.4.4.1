package com.argen;

import java.util.Random;

public class Main {

    public static int[] heroHealth = {270, 280, 250 ,300};
    public static int[] heroDamage = {20, 15, 25 , 0};
    public static String[] heroAttackType = {"Physycal",
            "Magical", "Kinetic" , "Medic"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("The game is stared");

        while (!isGameFinished()){
            round();
        }
    }

    public static void round(){
        roundNumber++;
        System.out.println("______ROUND - " + roundNumber
                +"________");
        bossDefenceType = changeBossDefence();
        System.out.println("Hero " + bossDefenceType + " Boss receive super damage" );
        bossHits();
        heroHits();
        medic();
        printStatistics();
    }

    public static boolean isGameFinished(){
        if (bossHealth <= 0){
            System.out.println("Heroes Win");
            return true;
        }

        boolean allHeroesDead = true;

        for (int hero: heroHealth) {
            if (hero > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss win");
        }

        return allHeroesDead;
    }
    public static void medic (){
       for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[3]> 0 && heroHealth[i] >100 && heroHealth[i] > 0);{
                Random t=new Random();
                int random=t.nextInt(60);
                heroHealth[i]= heroHealth[i] + random;
               System.out.println(" medic healed "+heroHealth[i]+ "by"+random+"hp");
               break;
            }
      }
    }

    public static void bossHits(){
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0 && bossHealth > 0){
                heroHealth[i] = heroHealth[i] - bossDamage;
                if (heroHealth[i] < 0){
                    heroHealth[i] = 0;
                }
            }
        }
    }

    public static void heroHits(){
        Random random = new Random();
        int coeff = random.nextInt(8)+ 2; //2,3,4,5,6,7,8,9,10
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0 && bossHealth > 0){
                if (heroAttackType[i] == bossDefenceType){
                    bossHealth = bossHealth - heroDamage[i] * coeff;
                    System.out.println("Super damage " + heroDamage[i]
                            * coeff + " [" + coeff + "]");
                } else {
                    bossHealth = bossHealth - heroDamage[i];
                }
                if (bossHealth < 0){
                    bossHealth = 0;
                }
            }
        }
    }

    public static String changeBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroAttackType.length);
        return heroAttackType[randomIndex];
    }

    public static void printStatistics(){
        System.out.println("Boss health: " + bossHealth +
                " [" + bossDamage + "]");
        for (int i = 0; i < heroAttackType.length; i++) {
            System.out.println(heroAttackType[i] + " health: " +
                    heroHealth[i] +
                    " [" + heroDamage[i] + "]");
        }
    }
}

