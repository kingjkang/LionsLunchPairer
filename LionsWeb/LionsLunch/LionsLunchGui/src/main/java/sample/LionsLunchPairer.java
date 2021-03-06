package sample;
import com.google.gdata.data.appsforyourdomain.Email;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by justinkang on 6/22/16.
 */
public class LionsLunchPairer {
    public static void main(String [] args){
        System.out.println("welcome to lions lunch pairer lol");
        ArrayList<LionsLunchMember> members;
        //LLDB.deleteMember("tst3");
        //LLDB.deleteMember("tst2");
        members = LLDB.getMemberInfo();
        //LLDB.addMember("tst3", "test four", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "extroverted", "none");
        //LLDB.addMember("tst1", "test two", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none");
        //LLDB.addMember("tst2", "test three", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none");

        LionsLunchMember ob = new LionsLunchMember("oddbreaker", "testloldamnit", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none", null);
        //LionsLunchMember ob0 = new LionsLunchMember("tst0", "test three", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none", "tst0");
        //LionsLunchMember ob1 = new LionsLunchMember("tst2", "test three", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none", "tst2");
        //LLDB.updatePastPairs(ob0);
        //LLDB.updatePastPairs(ob1);
        ArrayList<LionsLunchMember> pairedList = pairLionsIE(members, ob);
        System.out.println("lions paired!");
        //EmailDriver.sendList(pairedList);
        EmailDriver.sendMember(pairedList);
        //String curDir = System.getProperty("user.dir");
        //System.out.println(curDir);
    }

    public static ArrayList<LionsLunchMember> pairLionsIE(ArrayList<LionsLunchMember> buddies, LionsLunchMember oddBreaker){
        ArrayList<LionsLunchMember> introverts = new ArrayList<LionsLunchMember>();
        ArrayList<LionsLunchMember> extroverts = new ArrayList<LionsLunchMember>();

        createPotentialPairsIE(buddies);

        //splitting the list into introverts and extroverts and by potentialPairs size smallest first
        for (LionsLunchMember current : buddies){
            if (current.getPersonality().equals("introverted")){
                introverts.add(current);
            } else {
                extroverts.add(current);
            }
        }

        sortByPotentialSize(introverts);
        sortByPotentialSize(extroverts);

        //getting the size of the smaller array so we dont get index OOB exception
        ArrayList<LionsLunchMember> pairings = new ArrayList<LionsLunchMember>();
        ArrayList<LionsLunchMember> exceptions = new ArrayList<LionsLunchMember>();
        Random rand = new Random();
        if (introverts.size() > extroverts.size()){
            //this means that the extrovers are smaller so
            while (!extroverts.isEmpty()){
                int randomNumber = rand.nextInt(((introverts.size() - 1) - 0) + 1);
                //this function if there are no more potential pairs will get rid of it from the extroverts array
                if (extroverts.get(0).getPotentialPairs().size() == 0){
                    exceptions.add(extroverts.get(0));
                    extroverts.remove(0);
                } else if (extroverts.get(0).getPastPairs() == null || extroverts.get(0).getPotentialPairs().contains(introverts.get(randomNumber))) {
                    //this means that they have never been paired with anyone so just automatically pair them
                    pairings.add(extroverts.get(0));
                    pairings.add(introverts.get(randomNumber));
                    //if a pair has been found we need to remove this potentail from all the members personal lists
                    for (LionsLunchMember current : extroverts){
                        ArrayList<LionsLunchMember> update = current.getPotentialPairs();
                        update.remove(introverts.get(randomNumber));
                        current.setPotentialPairs(update);
                        //not sure if this works but we will see soon enoguh we need to remove the EID of the member that just got paired
                        //this should work beacue introvers.get returns a lionslunchmember object that we are removing from potential pairs
                    }
                    extroverts.remove(0);
                    introverts.remove(randomNumber);
                }
            }
        } else {
            //this means that the introverts array is amaller so there are less of them to pair
            while (!introverts.isEmpty()){
                int randomNumber = rand.nextInt(((extroverts.size() - 1) - 0) + 1);
                if (introverts.get(0).getPotentialPairs().size() == 0){
                    exceptions.add(introverts.get(0));
                    introverts.remove(0);
                } else if (introverts.get(0).getPastPairs() == null || introverts.get(0).getPotentialPairs().contains(extroverts.get(randomNumber))){
                    pairings.add(introverts.get(0));
                    pairings.add(extroverts.get(randomNumber));
                    for (LionsLunchMember current : introverts){
                        ArrayList<LionsLunchMember> update = current.getPotentialPairs();
                        update.remove(extroverts.get(randomNumber));
                        current.setPotentialPairs(update);
                    }
                    introverts.remove(0);
                    extroverts.remove(randomNumber);
                }
            }
        }
        //that functino just paired all the introvers/extrovers with who was available now we have the rest of the other list and the exceptions to take care of

        ArrayList<LionsLunchMember> remaining = new ArrayList<LionsLunchMember>();
        for (LionsLunchMember current : exceptions){
            remaining.add(current);
        }
        for (LionsLunchMember current : introverts){
            remaining.add(current);
        }
        for (LionsLunchMember current : extroverts){
            remaining.add(current);
        }
        //added all the remainig people that need to be paired into one array

        createPotentialPairs(remaining);
        sortByPotentialSize(remaining);
        //this recreates the potential pairs list for each of the remaining members and sorts by size now

        if (remaining.size() % 2 != 0){
            //this means that there si a remainder so that the remaining size is not evenly divisible by 2 so therefore there is an odd number of poeple
            //we are going to match a specific person on the barod to them that we have set aside as the designated cover to go on lunch date with
            //*****************
            //send notification
            //*****************
            for (LionsLunchMember current : remaining ){
                if (!current.getPastPairs().contains(oddBreaker.getEID())) {
                    // this means that they have not already met so i am going to pair these two individuals up
                    pairings.add(current);
                    pairings.add(oddBreaker);
                    remaining.remove(current);
                    break;
                }
            }
            //this means that the oddbreaker that we have set up default has already eaten with all of the people paired
            //so i am making an exception member so that we can find someone to eat with the member
            LionsLunchMember bm = new LionsLunchMember("Board Member", "bm0000", "0000000000", "justin.kang@utexas.edu", "Senior", false, "Business", "full access", false, "introverted", "none", null);
            pairings.add(remaining.get(0));
            pairings.add(bm);
            remaining.remove(remaining.get(0));
        }

        //checking to see if there was a potential match and if there isnt i am just going to leave someone unpaired and send an email to Teenuh to ask chairs
        //theoretically should not ever happen  but a case to check
        if (remaining.size() % 2 != 0){
            //**************
            // send an email or someting figure this part out later lol not a big deal for now cause it shoudl never happen for the next billino years
            //**************
        }

        //now list should be even so pair up the following completely random based on the condition if they ahve or have not already been paried
        exceptions = new ArrayList<LionsLunchMember>();
        while (!remaining.isEmpty()){
            //int randomNum = rand.nextInt((max - min) + 1) + min;
            int randomNum = rand.nextInt(remaining.size() - 1 - 1 + 1 + 1);
            if (remaining.get(0).getPotentialPairs().size() == 0){
                exceptions.add(remaining.get(0));
                remaining.remove(0);
            } else if (remaining.size() >= 2 && remaining.get(0).getPotentialPairs().contains(remaining.get(randomNum)) || remaining.get(0).getPastPairs() == null){
                //this means that they have never been paired with anyone so just automatically pair them
                pairings.add(remaining.get(0));
                pairings.add(remaining.get(randomNum));
                //if a pair has been found we need to remove this potentail from all the members personal lists
                for (LionsLunchMember current : remaining){
                    ArrayList<LionsLunchMember> update = current.getPotentialPairs();
                    update.remove(remaining.get(randomNum));
                    current.setPotentialPairs(update);
                    //not sure if this works but we will see soon enoguh we need to remove the EID of the member that just got paired
                    //this should work beacue introvers.get returns a lionslunchmember object that we are removing from potential pairs
                }
                remaining.remove(0);
                remaining.remove(randomNum);
            } else if (remaining.size() < 2){
                exceptions.add(remaining.get(0));
                remaining.remove(0);
            }
        }

        //this checks to see if excetions has anything
        if (!exceptions.isEmpty()){
            //**********
            //send notification
            //**********
            for (LionsLunchMember current : exceptions){
                pairings.add(current);
            }
        }

        //this means that everyone is paired and i need to return the array or do whatever i need to do with it print it save it to whatever send an email
        //also have confirmation etc. final steps but the pairings array is complete
        printPairings(pairings);
        int i = 0;
        for (int c = 0; c < pairings.size()/2; c++){
            int j = i + 1;
            pairings.get(i).addLunchPair(pairings.get(i).getPastPairs() + ", " + pairings.get(j).getEID());
            pairings.get(j).addLunchPair(pairings.get(j).getPastPairs() + ", " + pairings.get(i).getEID());
            i = i + 2;
        }

        for (LionsLunchMember current : pairings){
            LLDB.updatePastPairs(current);
        }
        return pairings;

    }

    public static void printPairings(ArrayList<LionsLunchMember> pairings){
        System.err.println("printing pairings straight down");
        for (LionsLunchMember current : pairings){
            System.err.println(current.getEID());
        }
    }

    public static void createPotentialPairs(ArrayList<LionsLunchMember> potentials){
        for (LionsLunchMember current : potentials){
            current.clearPotentialPairs();
            for (LionsLunchMember check : potentials){
                if (!current.getPastPairs().contains(check.getEID()) && current.getEID().equals(check.getEID())){
                    current.addPotentialPair(check);
                }
            }
        }
    }

    public static void createPotentialPairsIE(ArrayList<LionsLunchMember> potentials){
        for (LionsLunchMember current : potentials){
            current.clearPotentialPairs();
            for (LionsLunchMember check : potentials){
                if (!current.getPastPairs().contains(check.getEID()) && !current.getEID().equals(check.getEID()) && !current.getPersonality().equals(check.getPersonality())){
                    current.addPotentialPair(check);
                }
            }
        }
    }

    public static void sortByPotentialSize(ArrayList<LionsLunchMember> people){
        LionsLunchMember temp = null;
        for (int i = 0; i < people.size(); i++){
            for (int index = 1; index < people.size(); index++){
                int pointer = index - 1;
                if (people.get(index).getPotentialPairs().size() < people.get(pointer).getPotentialPairs().size()){
                    temp = people.get(index);
                    people.set(index, people.get(pointer));
                    people.set(pointer, temp);
                }
            }
        }
    }

    public static void pairLions(ArrayList<LionsLunchMember> buddies){
        //not working yet
        Random rand = new Random();
        int randomNumber;
        ArrayList<LionsLunchMember> pairedList = new ArrayList<LionsLunchMember>();
        LionsLunchMember [][] pairedBuddies = new LionsLunchMember[buddies.size()/2][3];
        int numOfBuddies = buddies.size();
        if ((numOfBuddies % 2) == 0){
            //this means that the number of people signed up is even
            while (buddies.size() > 2){
                System.err.println(buddies.size());
                randomNumber = rand.nextInt(((buddies.size() - 1) - 1) + 1) + 1;
                //int randomNum = rand.nextInt((max - min) + 1) + min;
                //this gets a random number to choose random index of remaining members in the arraylist
                if (buddies.get(0).getPastPairs() == null){
                    pairedList.add(buddies.get(0));
                    pairedList.add(buddies.get(randomNumber));
                    buddies.remove(buddies.get(0));
                    buddies.remove(buddies.get(randomNumber));
                }
                else if (!buddies.get(0).getPastPairs().contains(buddies.get(randomNumber).getEID())){
                    pairedList.add(buddies.get(0));
                    pairedList.add(buddies.get(randomNumber));
                    buddies.remove(buddies.get(0));
                    buddies.remove(buddies.get(randomNumber));
                }
            }
            for (int i = 0; i < 2; i++){
                pairedList.add(buddies.get(0));
                buddies.remove(buddies.get(0));
            }
            //i need to add a check here to see if the last two people have already been paired togehter
            //*********
            //*********
            int i = 0;
            int j;
            for (int c = 0; c < pairedList.size()/2; c++){
                j = i++;
                pairedBuddies[c][0] = pairedList.get(i);
                pairedList.get(i).addLunchPair((pairedList.get(i).getPastPairs() + ", " + pairedList.get(j).getEID()));
                LLDB.updatePastPairs(pairedList.get(i));
                //update the database with new pastPairs

                pairedBuddies[c][1] = pairedList.get(j);
                pairedList.get(j).addLunchPair((pairedList.get(j).getPastPairs() + ", " + pairedList.get(i).getEID()));
                LLDB.updatePastPairs(pairedList.get(j));
                //update the database with new pastPairs

                i = i + 2;
            }
        }
    }

}
