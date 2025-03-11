import java.util.Random;
import java.util.Scanner;

public class Main {
    public static long[] pullResults = new long[5]; //Array that counts all acquired items

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the Zenless Zone Zero Signal Search~ Limited banner edition!");
        System.out.println("Please select an option:\n1.Single Signal Search\n2.Signal Search X 10\n3.Exit\n4.Signal Search X 100\n");
        Scanner input = new Scanner(System.in);
        int sSearch; //Search option
        int totalPulls = 0; //Total pull counter
        int[] pityCount = new int[3]; //Array that keeps track of pity for S-ranks (index 0), A-ranks (index 1) and Limited S-rank guarantee (index 2)

        System.out.println("" + (90 - pityCount[0]) + " pulls until an S-Rank!");
        System.out.println("" + (10 - pityCount[1]) + " pulls until an A-Rank!");
        do {
            sSearch = input.nextInt();


        if(sSearch <= 4 && sSearch > 0)
        switch(sSearch){
            case 1: pityCount = singleSearch(pityCount);totalPulls += 1;break;
            case 2: pityCount = multipleSearch(pityCount);totalPulls += 10;break;
            case 3: System.out.println("Thank you for pulling - See ya next time!" + "\n Total Pulls: " + totalPulls);break;
            case 4: pityCount = hundredSearch(pityCount);totalPulls +=100;break;
        }

            if(sSearch != 1 && sSearch != 2 && sSearch != 3 && sSearch != 4)
                System.out.println("Enter a valid option!");
            if(sSearch !=3)
            {System.out.println("" + (90 - pityCount[0]) + " pulls until an S-Rank!");
                System.out.println("" + (10 - pityCount[1]) + " pulls until an A-Rank!");
            }
        }while(sSearch != 3);

        System.out.println(" Pull results:");
        System.out.println(" " + pullResults[0] +" S-rank Agent(s)");
        System.out.println(" " + pullResults[1] +" Limited S-rank Agent(s)");
        System.out.println(" " + pullResults[2] +" A-rank Agent(s)");
        System.out.println(" " + pullResults[3] +" A-rank W-Engine(s)");
        System.out.println(" " + pullResults[4] +" B-rank W-Engine(s)");
    }


    public static int[] singleSearch(int[] pityCount) throws InterruptedException {
        Random rand = new Random();
        SoundManager.stopSound();
        Scanner scanner = new Scanner(System.in);
        double pull = rand.nextDouble() * 100;
        System.out.println("Pulling...");

        double chanceS = 0.6; //Base chance for an S-rank pull
        int pityCountzero = 0; //failsafe for pitycount

        //String arrays for agent names -> Used in randomizer when an S-rank is acquired
        String[] sRankNames = {"Grace", "Koleda", "Alexandrina", "Nekomata", "Lycaon", "Soldier 11"};
        String[] limitedSRankNames = {"Soldier 0 - Anby", "Trigger", "Burnice"};
        if (pityCount[0] > 73)
            chanceS = 0.6 + 6 * (pityCount[0] - 73); //Soft Pity calculation (After pull 73, chances for an S-rank get higher and higher with each pull until an S-rank is acquired; As such, pull 90 will have a 102.6% chance)
        if (pull < chanceS) {
            double soundSelector = rand.nextDouble() * 100;
            if (soundSelector > 50)
                SoundManager.playSound("SpullM.wav", -6f);
            else
                SoundManager.playSound("SpullF.wav", -6f);
        } else
            SoundManager.playSound("pull.wav", -5f);
        //Music variants will be chosen based on if an S-rank is acquired or not. Both possible tracks for the S-rank theme are included
        pityCount[0]++;
        pityCount[1]++;
        Thread.sleep(500);

        System.out.printf("♪");
        Thread.sleep(400);
        System.out.printf(" ♪");
        Thread.sleep(400);
        System.out.printf(" ♩");
        Thread.sleep(600);
        System.out.printf(" ♬");
        Thread.sleep(300);
        System.out.printf(" ♫");
        Thread.sleep(450);
        System.out.printf(" ♩");
        Thread.sleep(500);
        System.out.printf(" ♩\n");
        if(pull < chanceS)
        {
        Thread.sleep(1000);
        System.out.printf(".");
        Thread.sleep(250);
        System.out.printf(".");
        Thread.sleep(250);
        System.out.printf(".");
        Thread.sleep(250);
        System.out.printf("!!!\n");
        Thread.sleep(2000);
        }
        else
        {

            Thread.sleep(3650);
        }
    if (pull < chanceS){ //S-rank chance check
        System.out.println("S-Rank Pulled!");
        double chance = rand.nextDouble() * 100;
        System.out.println("The result");
        Thread.sleep(1000);
        System.out.printf(".");
        Thread.sleep(1000);
        System.out.printf(".");
        Thread.sleep(1000);
        System.out.printf(".");
        Thread.sleep(1000);
        System.out.printf("!\n");
        String randomAgent = sRankNames[rand.nextInt(sRankNames.length)];
        String randomLimitedAgent = limitedSRankNames[rand.nextInt(limitedSRankNames.length)];
        if (chance > 50 && pityCount[2]==0) //S-rank limited banner 50/50 check
        {System.out.println("S-Rank Agent Contracted! - "+ randomAgent);pityCountzero = 1;pityCount[2]=1;
            pullResults[0]++;
        }
        else
        {System.out.println("Limited S-Rank Agent Contracted! - " + randomLimitedAgent);pityCountzero = 1;pityCount[2]=0;
            pullResults[1]++;
        }
    }
    else
    if ((pull >=chanceS && pull<8.6) || pityCount[1] == 10){ //A-rank chance check
        System.out.println("A-Rank Pulled!");
        pityCount[1] = 0;
        double chance = rand.nextDouble() * 100;
        System.out.println("The result...!");
        Thread.sleep(1000);
        if (chance > 35)
        {System.out.println("A-Rank Agent Contracted!");pullResults[2]++;}
        else
        {System.out.println("A-Rank W-Engine Acquired!");pullResults[3]++;}
    }
    else
    if (pull >= 8.6){
        Thread.sleep(350);
        System.out.println("B-Rank W-Engine.");
        pullResults[4]++;
    }


        System.out.println("Press ENTER to continue...");
        scanner.nextLine();
        SoundManager.stopSound();
        if(pityCountzero == 1)
            pityCount[0] = 0;
    return pityCount;
    }


    //Same as a single search, but an array is used to store pull info in order to be able to play the different music tracks for S-ranks
    public static int[] multipleSearch(int[] pityCount) throws InterruptedException{
        SoundManager.stopSound();
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] pullRanks = new int[11];
        boolean sPull;
        sPull = false;
        int count = 10;
        int pityCountzero = 0;

        do{

            double pull = rand.nextDouble() * 100;
            double chanceS = 0.6;
            if(pityCount[0] > 73 && sPull == false)
                chanceS = 0.6 + 6 * (pityCount[0] - 73);
            pityCount[0]++;
            pityCount[1]++;

            if (pull < chanceS){

                sPull = true;
                double chance = rand.nextDouble() * 100;



                if (chance > 50 && pityCount[2]==0)
                {//System.out.println("S-Rank Agent Contracted! - " + randomAgent);
                    sPull = true;
                    pullRanks[count]=1;
                    pityCount[0] = 0;
                    pityCount[2]=1;
                    pullResults[0]++;
                }
                else
                {//System.out.println("S-Rank W-Engine Acquired!");
                    sPull = true;
                    pullRanks[count]=11;
                    pityCount[0] = 0;
                    pityCount[2]=0;
                    pullResults[1]++;}
            }
            else
            if ((pull >=chanceS && pull<8.6) || pityCount[1] == 10){
                //System.out.println("A-Rank Pulled!");
                pityCount[1] = 0;
                double chance = rand.nextDouble() * 100;
                if (chance > 35)
                    //System.out.println("A-Rank Agent Contracted!");
                {pullRanks[count]=2;pullResults[2]++;}
                else
                    //System.out.println("A-Rank W-Engine Acquired!");
                {pullRanks[count]=22;pullResults[3]++;}
            }
            else
            if (pull >= 8.6){
                //System.out.println("B-Rank W-Engine.");
                pullRanks[count]=3;
                pullResults[4]++;
            }
            //System.out.println("Chance of pull was:" + pull);
            count--;
        }while(count>0);

        if(sPull == false)
        SoundManager.playSound("pull.wav", -5f);
        else
        {
            double soundSelector = rand.nextDouble() * 100;
            if(soundSelector > 50)
            SoundManager.playSound("SpullM.wav", -6f);
        else
                SoundManager.playSound("SpullF.wav", -6f);}
        System.out.println("Pulling...");

        Thread.sleep(500);

        System.out.printf("♪");
        Thread.sleep(400);
        System.out.printf(" ♪");
        Thread.sleep(400);
        System.out.printf(" ♩");
        Thread.sleep(600);
        System.out.printf(" ♬");
        Thread.sleep(300);
        System.out.printf(" ♫");
        Thread.sleep(450);
        System.out.printf(" ♩");
        Thread.sleep(500);
        System.out.printf(" ♩\n");
        if(sPull == false)
            Thread.sleep(3650);
        else {
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf("!!!\n");
            Thread.sleep(2000);
        }

        String[] sRankNames = {"Grace", "Koleda", "Alexandrina", "Nekomata", "Lycaon", "Soldier 11"};
        String[] limitedSRankNames = {"Soldier 0 - Anby", "Trigger", "Burnice"};
        int i;
        count = 10;
        for(i = count; i >0 ; i--){


        if(pullRanks[i] == 1 || pullRanks[i] == 11){
            System.out.println("S-Rank Pulled!");
            sPull = true;
            double chance = rand.nextDouble() * 100;
            System.out.println("The result");
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(1000);
            System.out.printf("!\n");

            String randomAgent = sRankNames[rand.nextInt(sRankNames.length)];
            String randomLimitedAgent = limitedSRankNames[rand.nextInt(limitedSRankNames.length)];
            if (pullRanks[i] == 1)
            {System.out.println("S-Rank Agent Contracted! - " + randomAgent);}
            else
            {System.out.println("Limited S-Rank Agent Contracted! - " + randomLimitedAgent);}
        }
        else
        if(pullRanks[i] == 2 || pullRanks[i] == 22){
            System.out.println("A-Rank Pulled!");
            double chance = rand.nextDouble() * 100;
            System.out.println("The result...!");
            Thread.sleep(1000);
            if (pullRanks[i] == 2)
                System.out.println("A-Rank Agent Contracted!");
            else
                System.out.println("A-Rank W-Engine Acquired!");
        }
        else
        if(pullRanks[i] == 3){
            Thread.sleep(350);
            System.out.println("B-Rank W-Engine.");
        }
        //System.out.println("Chance of pull was:" + pull);

        }

        System.out.println("Press ENTER to continue...");
        scanner.nextLine();
        SoundManager.stopSound();

        if(pityCountzero == 1)
            pityCount[0] = 0;
        return pityCount;
    }

    public static int[] hundredSearch(int[] pityCount) throws InterruptedException{
        SoundManager.stopSound();
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] pullRanks = new int[101];
        boolean sPull;
        sPull = false;
        int count = 100;
        int pityCountzero = 0;

        do{


            double pull = rand.nextDouble() * 100;
            double chanceS = 0.6;
            if(pityCount[0] > 73 && sPull == false)
                chanceS = 0.6 + 6 * (pityCount[0] - 73);
            pityCount[0]++;
            pityCount[1]++;

            if (pull < chanceS){
                //System.out.println("S-Rank Pulled!");
                sPull = true;
                double chance = rand.nextDouble() * 100;

                if (chance > 50 && pityCount[2]==0)
                {//System.out.println("S-Rank Agent Contracted! - " + randomAgent);
                    sPull = true;
                    pullRanks[count]=1;
                    pityCount[0] = 0;
                    pityCount[2]=1;
                    pullResults[0]++;
                }
                else
                {//System.out.println("S-Rank W-Engine Acquired!");
                    sPull = true;
                    pullRanks[count]=11;
                    pityCount[0] = 0;
                    pityCount[2]=0;
                    pullResults[1]++;}
            }
            else
            if ((pull >=chanceS && pull<8.6) || pityCount[1] == 10){
                //System.out.println("A-Rank Pulled!");
                pityCount[1] = 0;
                double chance = rand.nextDouble() * 100;
                if (chance > 35)
                    //System.out.println("A-Rank Agent Contracted!");
                {pullRanks[count]=2;pullResults[2]++;}
                else
                    //System.out.println("A-Rank W-Engine Acquired!");
                {pullRanks[count]=22;pullResults[3]++;}
            }
            else
            if (pull >= 8.6){
                //Thread.sleep(500);
                //System.out.println("B-Rank W-Engine.");
                pullRanks[count]=3;
                pullResults[4]++;
            }
            //System.out.println("Chance of pull was:" + pull);
            count--;
        }while(count>0);

        if(sPull == false)
            SoundManager.playSound("pull.wav", -5f);
        else
        {
            double soundSelector = rand.nextDouble() * 100;
            if(soundSelector > 50)
                SoundManager.playSound("SpullM.wav", -6f);
            else
                SoundManager.playSound("SpullF.wav", -6f);}
        System.out.println("Pulling...");

        Thread.sleep(500);

        System.out.printf("♪");
        Thread.sleep(400);
        System.out.printf(" ♪");
        Thread.sleep(400);
        System.out.printf(" ♩");
        Thread.sleep(600);
        System.out.printf(" ♬");
        Thread.sleep(300);
        System.out.printf(" ♫");
        Thread.sleep(450);
        System.out.printf(" ♩");
        Thread.sleep(500);
        System.out.printf(" ♩\n");

        //System.out.println("Du du  du dadada da du dum...");

        if(sPull == false)
            Thread.sleep(3650);
        else {
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf(".");
            Thread.sleep(250);
            System.out.printf("!!!\n");
            Thread.sleep(2000);
        }

        String[] sRankNames = {"Grace", "Koleda", "Alexandrina", "Nekomata", "Lycaon", "Soldier 11"};
        String[] limitedSRankNames = {"Soldier 0 - Anby", "Trigger", "Burnice"};
        int i;
        count = 100;
        for(i = count; i >0 ; i--){


            if(pullRanks[i] == 1 || pullRanks[i] == 11){
                System.out.println("S-Rank Pulled!");
                sPull = true;
                double chance = rand.nextDouble() * 100;
                System.out.println("The result");
                Thread.sleep(500);
                System.out.printf(".");
                Thread.sleep(500);
                System.out.printf(".");
                Thread.sleep(500);
                System.out.printf(".");
                Thread.sleep(500);
                System.out.printf("!\n");

                String randomAgent = sRankNames[rand.nextInt(sRankNames.length)];
                String randomLimitedAgent = limitedSRankNames[rand.nextInt(limitedSRankNames.length)];
                if (pullRanks[i] == 1)
                {System.out.println("S-Rank Agent Contracted! - " + randomAgent);}
                else
                {System.out.println("Limited S-Rank Agent Contracted! - " + randomLimitedAgent);}
            }
            else
            if(pullRanks[i] == 2 || pullRanks[i] == 22){
                System.out.println("A-Rank Pulled!");
                double chance = rand.nextDouble() * 100;
                System.out.println("The result...!");
                Thread.sleep(250);
                if (pullRanks[i] == 2)
                    System.out.println("A-Rank Agent Contracted!");
                else
                    System.out.println("A-Rank W-Engine Acquired!");
            }
            else
            if(pullRanks[i] == 3){
                Thread.sleep(50);
                System.out.println("B-Rank W-Engine.");
            }
            //System.out.println("Chance of pull was:" + pull);

        }

        System.out.println("Press ENTER to continue...");
        scanner.nextLine();
        SoundManager.stopSound();

        if(pityCountzero == 1)
            pityCount[0] = 0;
        return pityCount;

    }

}

