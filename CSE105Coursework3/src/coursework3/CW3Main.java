package coursework3;

import java.util.Scanner;

/*This is CSE105 INTRODUCTION TO JAVA coursework 3.
 *@author Shanghui Yin
 *ID Number: 1611530
 *
 *This is the main class, it contains a menu
 *And nearly every method in SkillSorter is called here.
 */
public class CW3Main {

    //Declare and instantiate the relevant objects.
    FileHandler handleFile = new FileHandler();
    Validations checkValid = new Validations();
    SkillSorter mySkillSorter = new SkillSorter();
    CommunityGroup myCommunityGroup = new CommunityGroup();
    //Scanner is used to get input from console.
    Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        CW3Main cw3 = new CW3Main();
    }

    public CW3Main() {
        //call [loadData] method in FileHandler to read the file, community groups need to be passed in meanwhile
        boolean successReadFile = handleFile.loadData(mySkillSorter.getCommunityGroups());
        //check whether the file is read successfully
        if (successReadFile == true) {
            //read the file successfully, then tell user the message and run the menu
            System.out.println("Persistence message: 'VolunteersGroups.txt' file is read successfully.");
            System.out.println("---------------------------------------------------------------------------------------------");//decorative line
            runMenu();
        } else {
            //otherwies fail to read the file, then tell user the exception and run the menu
            System.out.println("Persistence message: Error reading file. 'VolunteersGroups.txt' file may not exist.");
            System.out.println("---------------------------------------------------------------------------------------------");//decorative line
            runMenu();
        }
    }

    private void runMenu() {
        /* This is the [runMenu] method to hold the whole program.
         * Users could choose what they to do in this menu.
         * Nearly all method is called here.
         */
        //Some introduction to the program.
        System.out.println("Welcome! This program could help you automatically allocate volunteers to a community group.|");
        System.out.println("Look at the MENU OPTION to do what you want.                                                |");
        System.out.println("---------------------------------------------------------------------------------------------");//decorative line
        
        boolean exit = false;//control the menu, run again and again if false, till user input number "6" which means exit.

        while (exit == false) {
            //call [getMenuChoice] method to get a valid user input to realize corresponding functions
            int userChoice = getMenuChoice();

            switch (userChoice) {
                //Invalid input will return a "0"
                case 0:
                    //If the input number is not between 1 to 6 (return 0), then prompt user to input again
                    System.out.println("Invalid option! Please pay attention to the MENU OPTION.");
                    System.out.println("--------------------------------------------------------");//decorative line
                    break;

                case 1:
                    //call [getInputSkills] method to get a volunteer skillset, then add to a group
                    getInputSkills();
                    break;

                case 2:
                    String finalMoveSkills = getSkillsForMove();                //call [getSkillsForMove] method, then get a skillset to move
                    int fromGroup = getFromGroupNum();                          //call [getFromGroupNum] method to get a group number to move the volunteer
                    int toGroup = getToGroupNum();                              //call [getToGroupNum] method to get a group number to put the moved volunteer in
                    CommunityGroup from = mySkillSorter.getOneGroup(fromGroup); //get a "from" group according to user input
                    CommunityGroup to = mySkillSorter.getOneGroup(toGroup);     //get a "to" group according to user input
                    mySkillSorter.moveVolunteer(finalMoveSkills, from, to);     //call [moveVolunteer] method in SkillSorter to move a volunteer
                    break;

                case 3:
                    String finalDeleteSkills = getSkillsForDelete();            //call [getSkillsForDelete] method to get a volunteer skillset to delete
                    int groupNumForDelete = getFromGroupNum();                  //call [getFromGroupNum] method to get a group number to delete the volunteer
                    CommunityGroup groupNum = mySkillSorter.getOneGroup(groupNumForDelete);//get a group number to know in which group the volunteer will be deleted
                    mySkillSorter.deleteVolunteer(finalDeleteSkills, groupNum); //call [deleteVolunteer] method in SkillSorter to delete a volunteer
                    break;

                case 4:
                    mySkillSorter.deleteAllVolunteers();                        //call [deleteAllVolunteer] method in SkillSorter to clear all groups
                    break;

                case 5:
                    mySkillSorter.displayAllVolunteers();                       //call [displayAllVolunteers] method to display all volunteers in their groups
                    break;

                case 6:
                    exit = true;                                                //User input '6' and want to exit the program.
                    handleFile.SaveData(mySkillSorter.getCommunityGroups());    //call [SaveData] method in FileHandler to save all group data to file, pass in a community group meanwhile
                    break;

            }
        }

    }

    private int getMenuChoice() {
        //Print menu option messages to guide users
        System.out.println("* * * * * * * MENU OPTION * * * * * * * *");
        System.out.println("1 - Add a volunteer to a group          *");
        System.out.println("2 - Move a volunteer between two groups *");
        System.out.println("3 - Delete a volunteer from a group     *");
        System.out.println("4 - Delete all volunteers in all groups *");
        System.out.println("5 - Display all groups with volunteers  *");
        System.out.println("6 - Save volunteers and exit program    *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *");
        System.out.println("-----------------------");
        System.out.print("Please choose a number: ");
        
        Scanner getMenuInput = new Scanner(System.in);                          //Allow input in the console
        String userInput = getMenuInput.nextLine();                             //get user input

        try {
            int choice = Integer.parseInt(userInput);                           //try to convert String to a numeric value

            if (choice >= 1 && choice <= 6) {                                   //if it is a numeric value, also, the value should between 1 to 6
                return choice;
            } else {
                return 0;                                                       //even if it is a number, '0' will be returned if it is not between 1 to 6
            }
        } catch (NumberFormatException e) {                                     //fail to convert user input to number means invalid input
            return 0;                                                           //then return '0' to allow user to input again
        }

    }

    public void getInputSkills() {
        //guidance message to get input skillset
        System.out.println("Input law: You could only input three letters chosen from [A,B,C,D,E], form like 'BBB','CDE','AEE' etc.");
        System.out.print("Please input volunteer skils: ");

        boolean cont = true;                                                    //a boolean variable to get continue if user input invalid skillset
        while (cont) {
            Scanner getSkills = new Scanner(System.in);
            String volSkills = getSkills.nextLine();
            
            //call [skillsValidation] method in Validations to check whether input is follow the [input law]
            boolean case1SkillsValid = checkValid.skillsValidation(volSkills.toUpperCase());

            if (case1SkillsValid == true) {
                
                String finalSkills = checkValid.getAlphabeticalOrder(volSkills);//if skills are valid, then change to alphabetical order
                Volunteer newVol = new Volunteer(finalSkills);                  //Here, means skillset is valid, so a Volunteer could be created
                myCommunityGroup.volArr.add(newVol);                            //Add volunteer to community group's Volunteer Array
                mySkillSorter.addVolunteer(newVol);                             //call [addVolunteer] method in SkillSorter to add a volunteer by sorting algorithm
                
                cont = false;                                                   //A volunteer is added, no need to continue
            } else {
                System.out.print("Your input is invalid, please input again: ");//remind user the input is invalid
                cont = true;                                                    //then continue to input a skillset

            }
        }

    }
    
    /** The following four methods are used to move volunteer. */
    public String getSkillsForMove() {
        /* This method is to get a volunteer skillset to move
         * It will return a valid skillset
         */
        Scanner inputMoveSkills = new Scanner(System.in);
        System.out.println("Input law: You could only input three letters chosen from [A,B,C,D,E], form like 'BBB','CDE','AEE' etc.");
        System.out.print("Please input a volunteer's skills to move: ");        
        String skillsForMove = inputMoveSkills.nextLine();                      //get skillset input by user
        boolean case2SkillsValid = checkValid.skillsValidation(skillsForMove.toUpperCase());//check whether input skillset is valid
        String finalMoveSkills = checkValid.getAlphabeticalOrder(skillsForMove.toUpperCase());//change input skillset to alphabetical order
        while (case2SkillsValid == false) {                                     //invalid skillset will execute this while loop to receive input again
            System.out.print("Invalid skills, please input again: ");           //tell the user input is invalid
            skillsForMove = inputMoveSkills.nextLine();
            case2SkillsValid = checkValid.skillsValidation(skillsForMove.toUpperCase());
            finalMoveSkills = checkValid.getAlphabeticalOrder(skillsForMove.toUpperCase());
        }

        return finalMoveSkills;                                                 //return the valid skillset

    }

    
    public String getSkillsForDelete() {
        //Nearly same as the upper method [getSkillsForMove]
        //the only difference is the message
        
        Scanner inputDeleteSkill = new Scanner(System.in);
        System.out.print("Please input a volunteer's skills to delete: ");
        String skillsForDelete = inputDeleteSkill.nextLine();
        boolean case3SkillsValid = checkValid.skillsValidation(skillsForDelete.toUpperCase());
        String finalDeleteSkills = checkValid.getAlphabeticalOrder(skillsForDelete.toUpperCase());
        while (case3SkillsValid == false) {
            System.out.print("Invalid skills, please input again: ");
            skillsForDelete = inputDeleteSkill.nextLine();
            case3SkillsValid = checkValid.skillsValidation(skillsForDelete.toUpperCase());
            finalDeleteSkills = checkValid.getAlphabeticalOrder(skillsForDelete.toUpperCase());
        }

        return finalDeleteSkills;
    }

    public int getFromGroupNum() {
        /* This method is to get a group number to move volunteer out
         * It will return an integer to represent the group number
         * this method is shared with case3.deleteVolunteer
         */
        System.out.println("From which group? Choose a number from [1,2,3,4,5]");
        int fromGroup = keyboard.nextInt();                                       //get user input
        boolean case2_3_FromNumberValid = checkValid.numberValidation(fromGroup); //check whether the input number is between 1 to 5
        while (case2_3_FromNumberValid == false) {                                //invalid number will execute this while loop
            System.out.print("Invalid Group, please choose a whole number again:");//tell the user the input is invalid
            fromGroup = keyboard.nextInt();
            case2_3_FromNumberValid = checkValid.numberValidation(fromGroup);
        }

        return fromGroup;                                                         //return the valid group numebr

    }

    public int getToGroupNum() {
        /* This method is to get a group number to add volunteer in
         * It will return an integer to represent the group number
         * Nearly same as the upper method [getFromGroupNum]
         */
        System.out.println("To which group? Choose a number from [1,2,3,4,5]");
        int toGroup = keyboard.nextInt();
        boolean case2ToNumberValid = checkValid.numberValidation(toGroup);
        while (case2ToNumberValid == false) {
            System.out.print("Invalid Group number, please choose a whole number again:");
            toGroup = keyboard.nextInt();
            case2ToNumberValid = checkValid.numberValidation(toGroup);
        }

        return toGroup;

    }

}
