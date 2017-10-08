package awe;

/**
 * Response object for TimeToGoController. Simple data container representing
 * time left until next departure.
 *
 * @author anders
 */
public class TimeLeft {

    private int minutesLeft = -1;
    private String nameOfStop = "NOT DEFINED";
    private String stopId = "-1";


    public TimeLeft() {

    }

    public TimeLeft(int minutesLeft, String stopId) {
        this.minutesLeft = minutesLeft;
        this.stopId = stopId;
    }

    /**
     * @return the minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * @param minutesLeft the minutesLeft to set
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * @return the nameOfStop
     */
    public String getNameOfStop() {
        return nameOfStop;
    }

    /**
     * @param nameOfStop the nameOfStop to set
     */
    public void setNameOfStop(String nameOfStop) {
        this.nameOfStop = nameOfStop;
    }
    
    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

}
