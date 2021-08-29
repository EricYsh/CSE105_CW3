
package coursework3;


import java.util.Arrays;

//The whole class is used to check whether user's inputs are valid or not.
public class Validations {

    //this method is used to check whether three skills are valid or not
    public boolean skillsValidation(String threeskills) {

        String[] eachSkill = threeskills.split("");                             //split skillset into 3 String
        int validNumber = 0;                                                    //check valid numbers of skill String

        if (eachSkill.length == 3) {                                            //each volunteer only has three skills
            for (int i = 0; i < 3; i++) {                                       //if length is 3, then check 3 times
                if (eachSkill[i].equals("A") || eachSkill[i].equals("B") || eachSkill[i].equals("C") || eachSkill[i].equals("D") || eachSkill[i].equals("E")) {

                    validNumber++;                                              //get valid string, then valid number +1
                }
            }
        }
        if (validNumber == 3) {                                                 //three skills are valid together
            return true;                                                        //return true to indicate this volunteer is valid
        } else {
            return false;                                                       //otherwise this volunteer is invalid
        }

    }

    //this method is used to check whether input group number is valid or not
    public boolean numberValidation(int groupNumber) {

        //we only have five community groups
        if (groupNumber == 1 || groupNumber == 2 || groupNumber == 3 || groupNumber == 4 || groupNumber == 5) {
            return true;
        } else {
            return false;
        }
    }

    public String getAlphabeticalOrder(String volSkills) {
        //This method is used to change the order of volunteer skills because 'ABC' is same as 'CBA'
        
        char alphabetical_skills[] = volSkills.toCharArray();
        Arrays.sort(alphabetical_skills);//ACKNOWLEDGE: This sort method is from Internet
        String finalSkills = String.valueOf(alphabetical_skills);
        //return an alphabetical order string
        return finalSkills;
    }

}
