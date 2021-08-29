
package coursework3;

import cw3interfaces.SkillSorterInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class SkillSorter implements SkillSorterInterface {

    /**
     * This ArrayList is used to hold all community groups.
     */
    private ArrayList<CommunityGroup> myGroups = new ArrayList<>();
    /**
     * Create 5 different community groups.
     */
    private CommunityGroup group1 = new CommunityGroup();
    private CommunityGroup group2 = new CommunityGroup();
    private CommunityGroup group3 = new CommunityGroup();
    private CommunityGroup group4 = new CommunityGroup();
    private CommunityGroup group5 = new CommunityGroup();
    /**
     * This value is used to control the number of volunteers. MAXIMUM is 2500
     */
    private int totalAmountOfVol = 0;

    public SkillSorter() {
        //SkillSorter's constructor
        //Add all community groups to the ArrayList 'myGroups'
        myGroups.add(group1);
        myGroups.add(group2);
        myGroups.add(group3);
        myGroups.add(group4);
        myGroups.add(group5);

    }

    @Override
    public void addVolunteer(Volunteer vol) {
        /* This is a method used to add volunteer to a group
         * Here contain my sorting algorithm to balance skills and groups
         * Menu Case 1
         *@author Shanghui Yin
         *@date 12/12/2017
         * More details are available in my Word document
         */

        //check whether one of the groups are full [500] of Volunteers
        boolean oneGroupFull = checkGroupVolAmt();

        if (oneGroupFull == false && totalAmountOfVol < 2500) {//no group is full and total amount of volunteer is less than 2500

            if (totalAmountOfVol < 10) {//for the first 10 volunteers, I use a random number to add volunteer to groups
                int randomNum = (int) (1 + Math.random() * 5);                  //get a random number between 1 t0 5
                myGroups.get(randomNum - 1).volArr.add(vol);                    //Here minus 1 because index group start with 0
                totalAmountOfVol++;                                             //every time add a volunteer, total amount will +1
                System.out.println("The volunteer is allocated to a group automatically. Back to the menu.");//prompt user that add successfully 
                System.out.println("----------------------------------------------------------------------");//decorative line

            } else if (totalAmountOfVol >= 10 && totalAmountOfVol < 2500 && (totalAmountOfVol % 2) == 0) {//even number, balance groups

                int group1Amt = (myGroups.get(0).getEachGroupSkillsTotals()) / 3;//the number of vols in group1
                int group2Amt = (myGroups.get(1).getEachGroupSkillsTotals()) / 3;//the number of vols in group2
                int group3Amt = (myGroups.get(2).getEachGroupSkillsTotals()) / 3;//the number of vols in group3
                int group4Amt = (myGroups.get(3).getEachGroupSkillsTotals()) / 3;//the number of vols in group4
                int group5Amt = (myGroups.get(4).getEachGroupSkillsTotals()) / 3;//the number of vols in group5

                //call getSmallestGroup method to know which group has smallest number of Volunteer
                int smallestGroup = getSmallestGroup(group1Amt, group2Amt, group3Amt, group4Amt, group5Amt);
                //the return number "smallestGroup" mean which group has fewest number of volunteers
                //then use if statement to allocate this group a volunteer
                //so that to balance the number of volunteers in each group
                if (smallestGroup == 1) {
                    myGroups.get(0).volArr.add(vol);
                    totalAmountOfVol++;                                                                          //total amount need +1
                    System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");//prompt user that add successfully
                    System.out.println("----------------------------------------------------------------------");//decorative line
                } else if (smallestGroup == 2) {
                    myGroups.get(1).volArr.add(vol);
                    totalAmountOfVol++;
                    System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                    System.out.println("----------------------------------------------------------------------");
                } else if (smallestGroup == 3) {
                    myGroups.get(2).volArr.add(vol);
                    totalAmountOfVol++;
                    System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                    System.out.println("----------------------------------------------------------------------");
                } else if (smallestGroup == 4) {
                    myGroups.get(3).volArr.add(vol);
                    totalAmountOfVol++;
                    System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                    System.out.println("----------------------------------------------------------------------");
                } else if (smallestGroup == 5) {
                    myGroups.get(4).volArr.add(vol);
                    totalAmountOfVol++;
                    System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                    System.out.println("----------------------------------------------------------------------");
                } else {
                    System.out.println("All community groups are full of Volunteer. Please Choose other options.");
                    System.out.println("------------------------------------------------------------------------");
                }

            } else if (totalAmountOfVol >= 10 && totalAmountOfVol < 2500 && (totalAmountOfVol % 2) == 1) {//odd number, consider skill
                //but is total amount could be divide by 3 or 5 or 7, still balcane groups
                if (totalAmountOfVol % 3 == 0 || totalAmountOfVol % 5 == 0 || totalAmountOfVol % 7 == 0) {
                    int group1Amt = (myGroups.get(0).getEachGroupSkillsTotals()) / 3;//the number of vols in group1
                    int group2Amt = (myGroups.get(1).getEachGroupSkillsTotals()) / 3;//the number of vols in group2
                    int group3Amt = (myGroups.get(2).getEachGroupSkillsTotals()) / 3;//the number of vols in group3
                    int group4Amt = (myGroups.get(3).getEachGroupSkillsTotals()) / 3;//the number of vols in group4
                    int group5Amt = (myGroups.get(4).getEachGroupSkillsTotals()) / 3;//the number of vols in group5

                    //call getSmallestGroup method to know which group has smallest number of Volunteer
                    int smallestGroup = getSmallestGroup(group1Amt, group2Amt, group3Amt, group4Amt, group5Amt);
                    //the return number "smallestGroup" mean which group has fewest number of volunteers
                    //then use if statement to allocate this group a volunteer
                    //so that to balance the number of volunteers in each group
                    if (smallestGroup == 1) {
                        myGroups.get(0).volArr.add(vol);
                        totalAmountOfVol++;                                                                          //total amount need +1
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");//prompt user that add successfully
                        System.out.println("----------------------------------------------------------------------");//decorative line
                    } else if (smallestGroup == 2) {
                        myGroups.get(1).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else if (smallestGroup == 3) {
                        myGroups.get(2).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else if (smallestGroup == 4) {
                        myGroups.get(3).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else if (smallestGroup == 5) {
                        myGroups.get(4).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else {
                        System.out.println("All community groups are full of Volunteer. Please Choose other options.");
                        System.out.println("------------------------------------------------------------------------");
                    }

                } else {//otherwise, balance the skills. The main method is Standard Deviation

                    int group1A = myGroups.get(0).getAmountOfA();//get group1's skill A amounts
                    int group1B = myGroups.get(0).getAmountOfB();//get group1's skill B amounts
                    int group1C = myGroups.get(0).getAmountOfC();//get group1's skill C amounts
                    int group1D = myGroups.get(0).getAmountOfD();//get group1's skill D amounts
                    int group1E = myGroups.get(0).getAmountOfE();//get group1's skill E amounts
                    double group1DEV = getDeviation(group1A, group1B, group1C, group1D, group1E);//get the devitaion of skills in group1

                    int group2A = myGroups.get(1).getAmountOfA();//get group2's skill A amounts
                    int group2B = myGroups.get(1).getAmountOfB();//get group2's skill B amounts
                    int group2C = myGroups.get(1).getAmountOfC();//get group2's skill C amounts
                    int group2D = myGroups.get(1).getAmountOfD();//get group2's skill D amounts
                    int group2E = myGroups.get(1).getAmountOfE();//get group2's skill E amounts
                    double group2DEV = getDeviation(group2A, group2B, group2C, group2D, group2E);//get the devitaion of skills in group2

                    int group3A = myGroups.get(2).getAmountOfA();//get group3's skill A amounts
                    int group3B = myGroups.get(2).getAmountOfB();//get group3's skill B amounts
                    int group3C = myGroups.get(2).getAmountOfC();//get group3's skill C amounts
                    int group3D = myGroups.get(2).getAmountOfD();//get group3's skill D amounts
                    int group3E = myGroups.get(2).getAmountOfE();//get group3's skill E amounts
                    double group3DEV = getDeviation(group3A, group3B, group3C, group3D, group3E);//get the devitaion of skills in group3

                    int group4A = myGroups.get(3).getAmountOfA();//get group4's skill A amounts
                    int group4B = myGroups.get(3).getAmountOfB();//get group4's skill B amounts
                    int group4C = myGroups.get(3).getAmountOfC();//get group4's skill C amounts
                    int group4D = myGroups.get(3).getAmountOfD();//get group4's skill D amounts
                    int group4E = myGroups.get(3).getAmountOfE();//get group4's skill E amounts
                    double group4DEV = getDeviation(group4A, group4B, group4C, group4D, group4E);//get the devitaion of skills in group4

                    int group5A = myGroups.get(4).getAmountOfA();//get group5's skill A amounts
                    int group5B = myGroups.get(4).getAmountOfB();//get group5's skill B amounts
                    int group5C = myGroups.get(4).getAmountOfC();//get group5's skill C amounts
                    int group5D = myGroups.get(4).getAmountOfD();//get group5's skill D amounts
                    int group5E = myGroups.get(4).getAmountOfE();//get group5's skill E amounts
                    double group5DEV = getDeviation(group5A, group5B, group5C, group5D, group5E);//get the devitaion of skills in group5

                    //user a double array to sort the number from lowest to highest
                    double[] arrayOfDEV = {group1DEV, group2DEV, group3DEV, group4DEV, group5DEV};
                    Arrays.sort(arrayOfDEV);//ACKNOWLEDGE: This sort method is from Internet

                    //according to the definition of standard deviation, we need the largest one because its deviation is biggest
                    //then add a volunteer to the group which standard deviation is biggest
                    if (arrayOfDEV[4] == group1DEV) {
                        myGroups.get(0).volArr.add(vol);
                        totalAmountOfVol++;                                                                          //The total Amount of Volunteer +1
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");//prompt user that add successfully
                        System.out.println("----------------------------------------------------------------------");//decorative line
                    } else if (arrayOfDEV[4] == group2DEV) {
                        myGroups.get(1).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else if (arrayOfDEV[4] == group3DEV) {
                        myGroups.get(2).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else if (arrayOfDEV[4] == group4DEV) {
                        myGroups.get(3).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    } else {
                        myGroups.get(4).volArr.add(vol);
                        totalAmountOfVol++;
                        System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
            } else {
                System.out.println("Sorry, the Community Groups are full! Please choose other options.");
            }
        } else if (oneGroupFull == true && totalAmountOfVol < 2500) {//when one of the groups' amount achieve 500, only use this algorithm to add volunteers
            int group1Amt = (myGroups.get(0).getEachGroupSkillsTotals()) / 3;//the number of vols in group1
            int group2Amt = (myGroups.get(1).getEachGroupSkillsTotals()) / 3;//the number of vols in group2
            int group3Amt = (myGroups.get(2).getEachGroupSkillsTotals()) / 3;//the number of vols in group3
            int group4Amt = (myGroups.get(3).getEachGroupSkillsTotals()) / 3;//the number of vols in group4
            int group5Amt = (myGroups.get(4).getEachGroupSkillsTotals()) / 3;//the number of vols in group5

            //call getSmallestGroup method to know which group has smallest number of Volunteer
            int smallestGroup = getSmallestGroup(group1Amt, group2Amt, group3Amt, group4Amt, group5Amt);
            //the return number "smallestGroup" mean which group has fewest number of volunteers
            //then use if statement to allocate this group a volunteer
            //so that to balance the number of volunteers in each group
            if (smallestGroup == 1) {
                myGroups.get(0).volArr.add(vol);
                totalAmountOfVol++;                                                                          //total amount +1
                System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");//prompt user that add successfully
                System.out.println("----------------------------------------------------------------------");//decorative line
            } else if (smallestGroup == 2) {
                myGroups.get(1).volArr.add(vol);
                totalAmountOfVol++;
                System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                System.out.println("----------------------------------------------------------------------");
            } else if (smallestGroup == 3) {
                myGroups.get(2).volArr.add(vol);
                totalAmountOfVol++;
                System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                System.out.println("----------------------------------------------------------------------");
            } else if (smallestGroup == 4) {
                myGroups.get(3).volArr.add(vol);
                totalAmountOfVol++;
                System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                System.out.println("----------------------------------------------------------------------");
            } else if (smallestGroup == 5) {
                myGroups.get(4).volArr.add(vol);
                totalAmountOfVol++;
                System.out.println("The volunteer is allocated to a group automatically. Back to the MENU.");
                System.out.println("----------------------------------------------------------------------");
            } else {
                System.out.println("All community groups are full of Volunteer. Please Choose other options.");
                System.out.println("------------------------------------------------------------------------");
            }

        } else {
            //when total amount of volunteer achieve 2500, it meand all groups are full.
            //then prompt user to do other options in the MENU
            System.out.println("Sorry, the Community Groups are full! Please choose other options.");
        }

    }

    public boolean checkGroupVolAmt() {
        /* This method is used to check whether one of groups' total amount achieve 500
         * return true(full) or false(not full) to indicate
         */
        boolean oneGroupFull;

        int group1Amt = (myGroups.get(0).getEachGroupSkillsTotals()) / 3;//the number of vols in group1
        int group2Amt = (myGroups.get(1).getEachGroupSkillsTotals()) / 3;//the number of vols in group2
        int group3Amt = (myGroups.get(2).getEachGroupSkillsTotals()) / 3;//the number of vols in group3
        int group4Amt = (myGroups.get(3).getEachGroupSkillsTotals()) / 3;//the number of vols in group4
        int group5Amt = (myGroups.get(4).getEachGroupSkillsTotals()) / 3;//the number of vols in group5

        //create an integer array to sort
        int[] fullVol = {group1Amt, group2Amt, group3Amt, group4Amt, group5Amt};
        Arrays.sort(fullVol);//ACKNOWLEDGE: This sort method is from Internet

        //if statement to check
        if (fullVol[4] == 500) {
            oneGroupFull = true;

        } else {
            oneGroupFull = false;
        }
        return oneGroupFull;

    }

    public int getSmallestGroup(int group1Amt, int group2Amt, int group3Amt, int group4Amt, int group5Amt) {
        /* This method is used to get the group which has smallest amount of volunteer
         * It will return a corresponding group number to indicate
         */
        
        //put all passed in values in an array to sort
        int[] smallestGroup = {group1Amt, group2Amt, group3Amt, group4Amt, group5Amt};
        Arrays.sort(smallestGroup);//ACKNOWLEDGE: This sort method is from Internet
        
        //if statement to check which one is smallest
        if (smallestGroup[0] == group1Amt) {
            return 1;
        } else if (smallestGroup[0] == group2Amt) {
            return 2;
        } else if (smallestGroup[0] == group3Amt) {
            return 3;
        } else if (smallestGroup[0] == group4Amt) {
            return 4;
        } else {
            return 5;
        }

    }

    public double getDeviation(int amountA, int amountB, int amountC, int amountD, int amountE) {
        /* This method is used to calculate the standard deviation of each groups' skills
         * It will return each group's skillset' standard deviation
         */
        double groupDeviation;

        double averageNumSkill = (amountA + amountB + amountC + amountD + amountE) / 5;//average value of skills

        double differenceOfTwoSquaresA = Math.pow((amountA - averageNumSkill), 2);//perfect square of skill A
        double differenceOfTwoSquaresB = Math.pow((amountB - averageNumSkill), 2);//perfect square of skill B
        double differenceOfTwoSquaresC = Math.pow((amountC - averageNumSkill), 2);//perfect square of skill C
        double differenceOfTwoSquaresD = Math.pow((amountD - averageNumSkill), 2);//perfect square of skill D
        double differenceOfTwoSquaresE = Math.pow((amountE - averageNumSkill), 2);//perfect square of skill E

        //add all perfect square together
        double sumOfDif = differenceOfTwoSquaresA + differenceOfTwoSquaresB + differenceOfTwoSquaresC + differenceOfTwoSquaresD + differenceOfTwoSquaresE;

        //divide by total amount of skills
        groupDeviation = sumOfDif / (amountA + amountB + amountC + amountD + amountE);

        //return the standard deviation of each group
        return Math.sqrt(groupDeviation);

    }

    @Override
    public void moveVolunteer(String skillSet, CommunityGroup from, CommunityGroup to) {
        /* Move a volunteer with this skillset (eg AAA, BCD) from one CommunityGroup to another
         * It is divided into two situations, volunteer exists or does not exist
         * Menu Case 2
         */
        ListIterator indexVol = from.getVolunteerGroup().listIterator();        //Iterator the whole group
        Volunteer vol;                                                          //declare a Volunteer
        boolean moveVol = false;                                                //boolean to check move or not
        while (indexVol.hasNext()) {                                            //while loop to keep check
            vol = (Volunteer) indexVol.next();                                  //get the skillset as a Volunteer
            if (vol.getSkillSet().equals(skillSet)) {                           //If skillset match user input
                moveVol = true;                                                 //Then able to move
            }
        }
        if (moveVol == true) {                                                  
            indexVol.remove();                                                  //remove the volunteer from a group
            to.addOneVolunteer(skillSet);                                       //add the volunteer to another group
            System.out.println("The volunteer with skill " + skillSet + " is moved. Back to the MENU.");//prompt user that move successfully
            System.out.println("--------------------------------------------------------");//decorative line

        } else {                                                                //If move is false, it means the volunteer does not exist
            System.out.println("The volunteer " + skillSet + " does not exit. Back to the MENU.");//prompt the user not exist message
            System.out.println("---------------------------------------------------------------");//decorative
        }

    }

    @Override
    public void deleteVolunteer(String skillSet, CommunityGroup from) {
        /* Delete a volunteer with this skillset from this CommunityGroup
         * It also divided into two situations, volunteer exists or does not exist
         * Nearly same as the upper method [moveVolunteer]
         * Only difference is that this method does not need to add, just remove
         * Menu Case 3
         */
        ListIterator indexVol = from.getVolunteerGroup().listIterator();
        Volunteer vol;
        boolean deleteVol = false;
        while (indexVol.hasNext()) {
            vol = (Volunteer) indexVol.next();
            if (vol.getSkillSet().equals(skillSet)) {
                deleteVol = true;
            }
        }
        if (deleteVol == true) {
            indexVol.remove();
            this.totalAmountOfVol--;                                            //delete it, means totalAmountOfVol -1
            System.out.println("The volunteer with skill " + skillSet + " is deleted. Back to the menu.");//prompt user that delete succesfully
            System.out.println("----------------------------------------------------------");

        } else {
            System.out.println("The volunteer " + skillSet + " does not exit. Back to the MENU.");
            System.out.println("--------------------------------------------------");
        }

    }

    @Override
    public void deleteAllVolunteers() {
        /* Delete all volunteers from all CommunityGroups
         * Menu Case 4
         */
        ListIterator<CommunityGroup> it = myGroups.listIterator();
        while (it.hasNext()) {
            it.next().getVolunteerGroup().clear();                              //clear method to clean directly
        }
        System.out.println("All volunteers are deleted. Back to the MENU.");    //prompt user that delete all successfully
        System.out.println("---------------------------------------------");    //decorative line

        this.totalAmountOfVol = 0;                                              //delete all mean no volunteer
    }

    @Override
    public ArrayList<CommunityGroup> getCommunityGroups() {
        //return an ArrayList of all this application's CommunityGroups
        return myGroups;
    }

    public CommunityGroup getOneGroup(int index) {
        //Return a community group to move or delete volunteer.
        //Pass in a number between 1 to 5 to use if statement
        if (index == 1) {
            return group1;
        } else if (index == 2) {
            return group2;
        } else if (index == 3) {
            return group3;
        } else if (index == 4) {
            return group4;
        } else {
            return group5;
        }
    }

    public void displayAllVolunteers() {
        /* This is a method to display all groups
         * Responding to the case 5:
         */
        int groupNumber = 1;//display group number
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        for (CommunityGroup displayGroup : myGroups) {                          //for iterator to check each group           
            System.out.println("Group" + groupNumber + "  " + displayGroup.getSkillsTotals());//related to the method in CommunityGroups
            groupNumber++;                                                      //each time get a group, the number +1
        }
        System.out.println("All groups are displayed above, back to the MENU.");//prompt user that display successfully
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

    }

}
