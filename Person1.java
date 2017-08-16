package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  @author Michael Goodrum
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
    /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
  public Person1(String pname) {
    name = pname;
  }
    /**
     * This method should take the string
     * input and return its characters rotated
     * 2 positions.
     * given "gtg123b" it should return
     * "g123bgt".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    public String calc(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Cannot calc null string.");
        }
        char[] array = input.toCharArray();
        if (array.length == 1 || array.length == 2) {
            return new String(array);
        } else {
            char[] array2 = new char[array.length];
            for (int x = 0; x < array.length - 2; x++) {
                array2[x] = array[x + 2];
            }
            array2[array.length - 2] = array[0];
            array2[array.length - 1] = array[1];
            return new String(array2);
        }
    }
    
    /**
     * Return a string rep of this object
     * that varies with an input string
     *
     * @param input the varying string
     * @return the string representing the 
     *         object
     */
    public String toString(String input) {
      return name + calc(input);
    }

}