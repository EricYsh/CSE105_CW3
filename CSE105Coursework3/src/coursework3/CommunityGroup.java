package coursework3;

import cw3interfaces.CommunityGroupInterface;
import java.util.ArrayList;

public class CommunityGroup implements CommunityGroupInterface {

    /**
     * This ArrayList is used to hold all Volunteers.
     */
    ArrayList<Volunteer> volArr = new ArrayList<>();

    @Override
    public int howManyVolunteers() {
        //get the number of volunteer in volArr(Volunteer Arraylist)
        return volArr.size();
    }

    public ArrayList<Volunteer> getVolunteerGroup() {
        //return a volunteer arraylist to use in other places
        return volArr;
    }

    public void addOneVolunteer(String skills) {
        //this method is used to create a volunteer and add to the volunteer arraylist
        Volunteer addVol = new Volunteer(skills);
        volArr.add(addVol);
    }

    @Override
    public String getSkillsTotals() {
        //This method is used to get the number of volunteers' skillsets

        /**
         * Declare and initialize the number of A,B,C,D,E.
         */
        int As = 0;
        int Bs = 0;
        int Cs = 0;
        int Ds = 0;
        int Es = 0;

        for (int i = 0; i < volArr.size(); i++) {                               //for loop to check each volunteer in Volunteer Arraylist

            String[] splitSkills = volArr.get(i).getSkillSet().split("");       //split the volunteer skillset into three string

            for (int j = 0; j < 3; j++) {                                       //for loop to check each skillset
                if (splitSkills[j].equals("A")) {
                    As++;                                                       //contain one A then A + 1
                }
                if (splitSkills[j].equals("B")) {
                    Bs++;                                                       //contain one B then B + 1
                }
                if (splitSkills[j].equals("C")) {
                    Cs++;                                                       //contain one C then C + 1
                }
                if (splitSkills[j].equals("D")) {
                    Ds++;                                                       //contain one D then D + 1
                }
                if (splitSkills[j].equals("E")) {
                    Es++;                                                       //contain one E then E + 1
                }
            }
        }

        int totalNumberOfSkills = As + Bs + Cs + Ds + Es;                       //add all skills together then get their total amount
        int totalNumberOfVols = totalNumberOfSkills / 3;                        //total amount divide 3 is the volunteer amount because each volunteer has three skills

        //return the total number of each skill,total amount of skills, and the volunteer amount in a String
        return "Skill A:" + As + "\tSkill B:" + Bs + "\tSkill C:" + Cs + "\tSkill D:" + Ds + "\tSkill E:" + Es + "\t| Total Skills:" + totalNumberOfSkills + "\tVolunteers' amount:" + totalNumberOfVols + " |";
    }

    /* The following six methods are used in my sorting algorithm.
     * They will return the amount of A or B or C or D or E or total.
     */
    
    public int getEachGroupSkillsTotals() {
        //Nearly same as the upper method [getSkillsTotals]
        //This method return an integer of total amount of skills
        //It will be used in my sorting algorithm

        int amountA = 0;
        int amountB = 0;
        int amountD = 0;
        int amountC = 0;
        int amountE = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("A")) {
                    amountA++;
                }
                if (splitSkills[j].equals("B")) {
                    amountB++;
                }
                if (splitSkills[j].equals("C")) {
                    amountC++;
                }
                if (splitSkills[j].equals("D")) {
                    amountD++;
                }
                if (splitSkills[j].equals("E")) {
                    amountE++;
                }
            }
        }

        int groupTotalSkillsAmt = amountA + amountB + amountC + amountD + amountE;

        return groupTotalSkillsAmt;
    }

    public int getAmountOfA() {
        //part of the upper method
        //This method just return the total amount of skill A
        //Then it will be used in my sorting algorithm
        
        int amountA = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("A")) {
                    amountA++;
                }

            }
        }
        return amountA;
    }

    public int getAmountOfB() {
        //This method just return the total amount of skill B
        //Then it will be used in my sorting algorithm
        int amountB = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("B")) {
                    amountB++;
                }

            }
        }
        return amountB;
    }

    public int getAmountOfC() {
        //This method just return the total amount of skill C
        //Then it will be used in my sorting algorithm
        int amountC = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("C")) {
                    amountC++;
                }

            }
        }
        return amountC;
    }

    public int getAmountOfD() {
        //This method just return the total amount of skill D
        //Then it will be used in my sorting algorithm
        int amountD = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("D")) {
                    amountD++;
                }

            }
        }
        return amountD;
    }

    public int getAmountOfE() {
        //This method just return the total amount of skill E
        //Then it will be used in my sorting algorithm
        int amountE = 0;

        for (int i = 0; i < volArr.size(); i++) {

            String[] splitSkills = volArr.get(i).getSkillSet().split("");

            for (int j = 0; j < 3; j++) {
                if (splitSkills[j].equals("E")) {
                    amountE++;
                }

            }
        }
        return amountE;
    }

}
