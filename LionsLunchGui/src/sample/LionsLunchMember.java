package sample;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by justinkang on 6/21/16.
 */
public class LionsLunchMember implements java.io.Serializable{

    private String name = null;
    private String eid = null;
    private String phoneNumber = null;
    private String emailAddress = null;
    private String yearClassification = null;
    private Boolean pairMajor = false;
    private String major = null;
    private String permissions = null;
    //do we need to change that to string becabuse of other permissions types
    private Boolean weeklyPair = false;
    private String personality = null;
    private String additionalInfo = null;
    private String pastPairs = null;

    public LionsLunchMember(String initEID, String initName, String initPhoneNumber, String initEmailAddress, String initYearClassification,
                            Boolean initPairMajor, String initMajor, String initPermissions, Boolean initWeeklyPair, String initPersonality, String initAdditionalInfo, String initPastPairs){
        this.name = initName;
        this.eid = initEID;
        this.phoneNumber = initPhoneNumber;
        this.emailAddress = initEmailAddress;
        this.yearClassification = initYearClassification;
        this.pairMajor = initPairMajor;
        this.major = initMajor;
        this.permissions = initPermissions;
        this.weeklyPair = initWeeklyPair;
        this.personality = initPersonality;
        this.additionalInfo = initAdditionalInfo;
        this.pastPairs = initPastPairs;
    }

    public void setName(String sName){this.name = sName;}

    public String getName(){return this.name;}

    public void setEID(String sEID){this.eid = sEID;}

    public String getEID(){return this.eid;}

    public void setPhoneNumber(String sPhoneNumber){this.phoneNumber = sPhoneNumber;}

    public String getPhoneNumber(){return this.phoneNumber;}

    public void setEmailAddress(String sEmailAddress){this.emailAddress = sEmailAddress;}

    public String getEmailAddress(){return this.emailAddress;}

    public void setYearClassification(String sYearClassification){this.yearClassification = sYearClassification;}

    public String getYearClassification(){return this.yearClassification;}

    public void setPairMajor(Boolean sPairMajor){this.pairMajor = sPairMajor;}

    public Boolean getPairMajor(){return this.pairMajor;}

    public void setMajor(String sMajor){this.major = sMajor;}

    public String getMajor(){return this.major;}

    public void setPermissions(String sSetPermissions){this.permissions = sSetPermissions;}

    public String getPermissions(){return this.permissions;}

    public void setWeeklyPair(Boolean sWeeklyPair){this.weeklyPair = sWeeklyPair;}

    public Boolean getWeeklyPair(){return this.weeklyPair;}

    public void setPersonality(String sPersonality){this.personality = sPersonality;}

    public String getPersonality(){return this.personality;}

    public void setAdditionalInfo(String sAdditionalInfo){this.additionalInfo = sAdditionalInfo;}

    public String getAdditionalInfo(){return this.additionalInfo;}

    public String getPastPairs(){return this.pastPairs;}

    public void addLunchPair(String sMember){this.pastPairs = sMember;}

}
