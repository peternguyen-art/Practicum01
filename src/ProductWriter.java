import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {

        /* a.	ID (a String as before in Person)
            b.	Name (a String)
            c.	Description (a String a short sentence)
            d.	Cost (This is currency so it will be a Java double)
         */

        String ID ="";
        String name = "";
        String description = "";
        double cost = 0;

        String CSVProductRec = "";

        Scanner in = new Scanner(System.in);

        ArrayList<String> csvProducts = new ArrayList<>();

        //define user dir
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;
        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter ID");
            name = SafeInput.getNonZeroLenString(in, "Enter Name");
            description = SafeInput.getNonZeroLenString(in, "Enter Description");
            cost = SafeInput.getDouble(in, "Enter Cost");

            CSVProductRec = ID + ", " + name + ", " + description + ", " + cost;

            csvProducts.add(CSVProductRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");
        }while(!done);

        for(String p:csvProducts){
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

            for(String rec : csvProducts)
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


