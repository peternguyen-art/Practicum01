import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

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

        //define user dir
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

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

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : csvPersons)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}


