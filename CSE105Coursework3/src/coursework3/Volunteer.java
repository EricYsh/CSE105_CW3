
package coursework3;

import cw3interfaces.VolunteerInterface;

public class Volunteer implements VolunteerInterface {
    /**This value is used to get volunteers' three skills. */
    private String skills;

    public Volunteer(String skills) {
        /**Volunteer constructor to receive a skills String. */
        this.skills = skills;

    }
   
    @Override
    public String getSkillSet() {
        /**return a String of this volunteers skills, eg BBB, ABC, CDD etc. */
        return skills;
    }

}
