import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        /* a. ID (a String)
         b. FirstName
         c. LastName
         d. Title (a string like Mr., Mrs., Ms., Dr., etc.)
         e. YearOfBirth (an int)
         */

        String ID ="";
        String firstname = "";
        String lastname = "";
        String title = "";
        String CSVPersonRec = "";
        int yearOfBirth = 0;

        Scanner in = new Scanner(System.in);

        ArrayList<String> csvPersons = new ArrayList<>();

        boolean done = false;
        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter ID");
            firstname = SafeInput.getNonZeroLenString(in, "Enter First name");
            lastname = SafeInput.getNonZeroLenString(in, "Enter Last name");
            title = SafeInput.getNonZeroLenString(in, "Enter Title");
            yearOfBirth = SafeInput.getRangedInt(in, "Enter your YOB", 1000, 9999);

            CSVPersonRec = ID + ", " + firstname + ", " + lastname + ", " + title + ", " + yearOfBirth + "\n";

            csvPersons.add(CSVPersonRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");
        }while(!done);

        for(String p:csvPersons){
            System.out.println(p);
        }

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");


    }
}


