package models;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Elina on 2017-05-23.
 */

public class EveObject {
    String name;
    String category;
    String description;
    String placeId;
    String eventDate;
    String startTime;
    String endTime;
    Boolean visible;
    int id;
    int leaderId;
    int minCapacity;
    int maxCapacity;
    int registration;
    int difficulty;

    public EveObject(String name, String category, String description, String placeId, String eventDate, String startTime, String endTime, Boolean visible, int id, int leaderId, int minCapacity, int maxCapacity, int registration, int  difficulty){
        this.name = name;
        this.category = category;
        this.description = description;
        this.placeId = placeId;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.visible = visible;
        this.id = id;
        this.leaderId = leaderId;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.registration = registration;
        this.difficulty = difficulty;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setCategory (String category){
        this.category = category;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public void setPlaceId (String placeId){
        this.placeId = placeId;
    }

    public void setEventDate (String eventDate){
        this.eventDate = eventDate;
    }

    public void setStartTime (String startTime){
        this.startTime = startTime;
    }

    public void setEndTime (String endTime){
        this.endTime = endTime;
    }

    public void setVisible (Boolean visible){
        this.visible = visible;
    }

    public void setId (int id){
        this.id = id;
    }

    public void setLeaderId (int leaderId){
        this.leaderId = leaderId;
    }

    public void setMaxCapacity (int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public void setMinCapacity (int minCapacity){
        this.minCapacity = minCapacity;
    }

    public void setRegistration (int registration){
        this.registration = registration;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public String getName (){
        return name;
    }

    public String getCategory (){
        return category;
    }

    public String getDescription (){
        return description;
    }

    public String getPlaceId (){
        return placeId;
    }

    public String getEventDate (){
        return eventDate;
    }

    public String getStartTime (){ return startTime; }

    public String getEndTime (){
        return endTime;
    }

    public Boolean getVisible (){
        return visible;
    }

    public int getId (){
        return id;
    }

    public int getLeaderId (){
        return leaderId;
    }

    public int getMaxCapacity (){
        return maxCapacity;
    }

    public int getMinCapacity (){
        return minCapacity;
    }

    public String getRegistration (){
        return "" + registration;
    }

    public String getDifficulty(){
        return "" + difficulty;
    }
    public String toString(){
        return name + "\n" + category + "\n" + description + "\n" + placeId + "\n" + eventDate + "\n" + startTime + "\n" + endTime + "\n" + visible + "\n" + id + "\n" + leaderId + "\n" + minCapacity + "\n" + maxCapacity + "\n" + registration + "\n" + difficulty + "\n" + "\n";
    }
}
