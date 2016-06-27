import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by justinkang on 6/27/16.
 */
public class ListCreator {
    public static void makeListFile(ArrayList<LionsLunchMember> list) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("pairedList.txt", "UTF-8");
        writer.println("Here are the lions lunch pairs for the week"); //use the java date function to find the actual week
        writer.println("\n");
        //loop to print all the names and eid's
        int first = 0;
        int second = 1;
        for (int i = 0; i < list.size()/2; i++){
            writer.println(list.get(first).getName() + " (" + list.get(first).getEID() + ") -- " + list.get(second).getName() + " (" + list.get(second).getEID() + ")");
            first = first + 2;
            second = second + 2;
        }
        writer.println("\n");
        writer.close();
    }

}
