package models;

/**
 * Created by amaliaskantz on 2017-05-16.
 */

public class Event {

    private String name;
    private String place;
    private String date;
    private String startTime;
    private String endTime;
    private String participants;
    private String difficulty;

    public Event(String name, String place, String date, String startTime, String endTime, String participants, String difficulty) {

        this.name = name;
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.difficulty = difficulty;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getParticipants() {
        return "Anmälda: " + participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getDifficulty() {
        return "Svårighetsgrad: " + difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
