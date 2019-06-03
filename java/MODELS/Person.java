package MODELS;

import java.io.Serializable;

/** Contains all of a Person's info
 *
 * @author Rees Thomas
 *
 */
public class Person implements Serializable
{
    /** Create a new Person object
     *
     * @param personID
     * @param descendant
     * @param firstName
     * @param lastName
     * @param gender
     * @param father
     * @param mother
     * @param spouse
     *
     */
    public Person(String personID, String descendant,
                  String firstName, String lastName,
                  String gender, String father,
                  String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    /** Person's unique generated ID
     *
     */
    public String personID;

    /** User's username to which this person belongs
     *
     */
    public String descendant;

    /** Person's first name
     *
     */
    public String firstName;

    /** Person's last name
     *
     */
    public String lastName;

    /** Person's gender
     *
     */
    public String gender;

    /** ID of person's father (could be null)
     *
     */
    public String father;

    /** ID of person's mother (could be null)
     *
     */
    public String mother;

    /** ID of person's spouse (could be null)
     *
     */
    public String spouse;

    public String getPersonID() {
        return personID;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
}
