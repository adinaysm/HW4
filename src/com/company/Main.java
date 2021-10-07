package com.company;

import java.util.Random;

public class Main {


    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", "Medic", "Thor", "Lucky", "Golem", "Berserk"};
    public static int[] heroesHealth = {290, 280, 250, 200, 190, 210, 100, 170};
    public static int[] heroesDamages = {20, 25, 15, 0, 30, 40, 70, 10};
    public static int roundNumber = 0;

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackTypes.length); // 0, 1, 2
        bossDefenceType = heroesAttackTypes[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static void main(String[] args) {
	// write your code here
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHit();
        thorsSuperAbility();
        superAbilityOfMedic();
        luckySuperAbility();
        printStatistics();
        luckySuperAbility();
        abilityOfGolem();
        Berserk();
        bossIsBack();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
    }

    public static void printStatistics() {
        System.out.println("________ ROUND " + roundNumber);
        System.out.println("Boss health: " + bossHealth +
                " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackTypes[i] +
                    " health: " + heroesHealth[i] +
                    " [" + heroesDamages[i] + "]");
        }
        System.out.println("________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackTypes[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println("Critical damage: "
                            + heroesDamages[i] * coeff);
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }
    public static void superAbilityOfMedic(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0){
                heroesHealth[i] = heroesHealth[i] + 10;

            }



        }
    }
    public static void thorsSuperAbility(){
        Random random = new Random();
        boolean randomRound = random.nextBoolean();
        if(randomRound == true){
            bossDamage = 0;
        }
        else if (randomRound == false){
            bossDamage = 50;
        }
    }
    public static void bossIsBack(){
        bossDamage = 50;
    }
    public static void luckySuperAbility(){
        Random random = new Random();
        boolean luckyRandom = random.nextBoolean();
        if(luckyRandom == true){
            heroesHealth[5] = 0;
        } else if(luckyRandom == false){
            heroesHealth[5] = 210;
        }
    }
    public static void luckyIsBack(){
    heroesHealth[5] = 210;
    }

    public static boolean abilityOfGolem() {
        boolean protect = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] - bossDamage < 0) {
                heroesHealth[6] = 0;
                protect = false;
                break;
            } else if (heroesHealth[6] <= 0 && heroesHealth[i] <= 0) {
                protect = false;
                break;
            } else if (heroesHealth[6] - (bossDamage / 5) > 0 && heroesHealth[i] < 0) {
                protect = false;
                break;
            } else if (heroesHealth[6] > 0) {
                heroesHealth[6] = heroesHealth[6] - bossDamage / 5;
                heroesHealth[i] = heroesHealth[i] + bossDamage / 5;
                protect = true;
                break;
            } else {
                protect= false;
                break;
            }

        }
        return protect;
    }

    public static void Berserk() {
        Random random = new Random();
        int protectionOfBerserk = random.nextInt(25) + 1;
        if(heroesHealth[7] > 0 ) {
            heroesHealth[7] = (heroesHealth[7] - bossDamage) + protectionOfBerserk;
            heroesDamages[7] = heroesDamages[7] + protectionOfBerserk;
            System.out.println("Berserk activated SUPER");
        }
        if(heroesHealth[7] < 0){
            heroesHealth[7] = 0;
        }
    }

}




