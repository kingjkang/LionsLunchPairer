package sample;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

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
        members = LLDB.getMemberInfo();
        //LLDB.addMember("tst0", "test one", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none");
        //LLDB.addMember("tst1", "test two", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none");
        //LLDB.addMember("tst2", "test three", "0000000000", "testing1@gmail.com", "Junior", false, "Business", "full access", false, "introverted", "none");
        pairLions(members);
        System.out.println("lions paired!");
    }

    public static void pairLionsIE(ArrayList<LionsLunchMember> buddies){
        ArrayList<LionsLunchMember> Introverts = new ArrayList<>();
        ArrayList<LionsLunchMember> Extroverts = new ArrayList<>();

        createPotentialPairs(buddies);

        //splitting the list into introverts and extroverts and by potentialPairs size smallest first
        for (LionsLunchMember current : buddies){
            if (current.getPersonality().equals("Introverted")){
                Introverts.add(current);
                sortByPotentialSize(Introverts);
            } else {
                Extroverts.add(current);
                sortByPotentialSize(Extroverts);
            }
        }

        //getting the size of the smaller array so we dont get index OOB exception
        ArrayList<LionsLunchMember> pairings = new ArrayList<>();
        ArrayList<LionsLunchMember> exceptions = new ArrayList<>();
        Random rand = new Random();
        if (Introverts.size() > Extroverts.size()){
            //this means that the extrovers are smaller so
            while (!Extroverts.isEmpty()){
                int randomNumber = rand.nextInt(((Introverts.size() - 1) - 0) + 1);
                //this function if there are no more potential pairs will get rid of it from the extroverts array
                if (Extroverts.get(0).getPotentialPairs().size() == 0){
                    exceptions.add(Extroverts.get(0));
                    Extroverts.remove(0);
                } else if (Extroverts.get(0).getPastPairs() == null || !Extroverts.get(0).getPotentialPairs().contains(Introverts.get(randomNumber))) {
                    //this means that they have never been paired with anyone so just automatically pair them
                    pairings.add(Extroverts.get(0));
                    pairings.add(Introverts.get(randomNumber));
                    //if a pair has been found we need to remove this potentail from all the members personal lists
                    for (LionsLunchMember current : Extroverts){
                        ArrayList<LionsLunchMember> update = current.getPotentialPairs();
                        update.remove(Introverts.get(randomNumber));
                        current.setPotentialPairs(update);
                        //not sure if this works but we will see soon enoguh we need to remove the EID of the member that just got paired
                        //this should work beacue introvers.get returns a lionslunchmember object that we are removing from potential pairs
                    }
                    Extroverts.remove(0);
                    Introverts.remove(randomNumber);
                }
            }
        } else {
            //this means that the introverts array is amaller so there are less of them to pair
            while (!Introverts.isEmpty()){
                int randomNumber = rand.nextInt(((Extroverts.size() - 1) - 0) + 1);
                if (Introverts.get(0).getPotentialPairs().size() == 0){
                    exceptions.add(Introverts.get(0));
                    Introverts.remove(0);
                } else if (Introverts.get(0).getPastPairs() == null || !Introverts.get(0).getPotentialPairs().contains(Extroverts.get(randomNumber))){
                    pairings.add(Introverts.get(0));
                    pairings.add(Extroverts.get(randomNumber));
                    for (LionsLunchMember current : Introverts){
                        ArrayList<LionsLunchMember> update = current.getPotentialPairs();
                        update.remove(Extroverts.get(randomNumber));
                        current.setPotentialPairs(update);
                    }
                    Introverts.remove(0);
                    Extroverts.remove(randomNumber);
                }
            }
        }
        //that functino just paired all the introvers/extrovers with who was available now we have the rest of the other list and the exceptions to take care of
        


    }


    public static void createPotentialPairs(ArrayList<LionsLunchMember> potentials){
        for (LionsLunchMember current : potentials){
            for (LionsLunchMember check : potentials){
                if (!current.getPastPairs().contains(check.getEID()) && current.getEID() != check.getEID()){
                    current.addPotentialPair(check);
                }
            }
        }
    }

    public static void sortByPotentialSize(ArrayList<LionsLunchMember> people){
        LionsLunchMember temp = null;
        for (int i = 0; i < people.size(); i++){
            for (int index = 1; index < people.size(); index++){
                if (people.get(index).getPotentialPairs().size() < people.get(index--).getPotentialPairs().size()){
                    temp = people.get(index);
                    people.set(index, people.get(index--));
                    people.set(index--, temp);
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
