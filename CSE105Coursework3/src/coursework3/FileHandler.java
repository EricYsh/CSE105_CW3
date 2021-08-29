package coursework3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//This class is used to save and read all groups.
public class FileHandler {

    public void SaveData(ArrayList<CommunityGroup> saveGroup) {
        //Save groups method

        File file = new File("VolunteersGroups.txt");                           //create a file named 'VolunteersGroups', type: txt
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(file, false));//every time save from the beginning

            for (int i = 0; i < saveGroup.size(); i++) {
                bfw.write("Group" + (i + 1) + ":");                             //write in group number to distinguish
                bfw.newLine();                                                  //change to next line

                for (Volunteer eachVol : saveGroup.get(i).getVolunteerGroup()) {//get each group
                    bfw.write(eachVol.getSkillSet());                           //write in volunteers' skillsets
                    bfw.newLine();
                }
            }
            bfw.flush();
            bfw.close();                                                        //close the file
            System.out.println("All data written to the file. Bye Bye:)");      //prompt user that written successfully, end the program
        } catch (IOException e) {
            System.out.println("Error writing to file");                        //fail to write to file

        }

    }

    public boolean loadData(ArrayList<CommunityGroup> loadGroup) {
        //load data method, run before starting the menu

        boolean success;                                                        //return a boolean to check whether seccess or not
        File file = new File("VolunteersGroups.txt");                           //find the file
        Validations checkSkills = new Validations();                            //check volunteers' skillset
        CommunityGroup loadData = new CommunityGroup();                         //get community groups
        String line;
        int counter = 0;                                                        //index groups
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            while ((line = bfr.readLine()) != null) {
                if (line.contains("Group")) {                                   //while encounter groups, it means next group
                    loadData = loadGroup.get(counter);
                    counter++;
                } else {
                    boolean validSkills = checkSkills.skillsValidation(line);   //if encounter other string, check whether it is volunteer skills
                    if (validSkills == true) {                                  //vaild skills, add to a community group
                        checkSkills.getAlphabeticalOrder(line);                 //change to alphabetical order
                        loadData.addOneVolunteer(line);                         
                    } else {
                        System.out.println("Invalid Skills");                   //otherwise prompt user its invalid skillset
                    }
                }
            }
            bfr.close();
            success = true;                                                     //return true to indivate read file successfully

        } catch (IOException e) {
            success = false;                                                    //otherwise get expection
        }
        return success;                                                         //return final boolean value

    }

}
