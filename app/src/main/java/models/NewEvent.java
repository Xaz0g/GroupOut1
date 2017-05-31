package models;


import java.sql.Date;
import java.sql.Time;
import java.util.function.Function;

/**
 * Created by Xaz0g on 2017-05-19.
 */

public class NewEvent
{
    private int minCapacity,maxCapacity, difficulty;
    private String name,category,description,placeId;
    private Date eventDate;
    private Time startTime,endTime;

    public String toJsonString()
    {
        String nameParsed = parseToLegal(name);
        String descParced = parseToLegal(description);

        return "{\"minCapacity\":\"" + minCapacity + "\",\"maxCapacity\":\"" + maxCapacity
                + "\",\"difficulty\":\"" + difficulty + "\",\"name\":\"" + nameParsed
                + "\",\"category\":\"" + category
                + "\",\"description\":\"" + descParced + "\",\"placeId\":\"" + placeId
                + "\",\"eventDate\":\"" + eventDate + "\",\"startTime\":\"" +startTime
                + "\",\"endTime\":\"" + endTime + "\"}";
    }

    private String parseToLegal(String s){
        String sParsed = "";

        for(char c : s.toCharArray())
        {
            sParsed += (c == '?') ? "*!*" : c;
        }

        return sParsed;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "NewEvent{" +
                "minCapacity=" + minCapacity +
                ", maxCapacity=" + maxCapacity +
                ", difficulty=" + difficulty +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", placeId='" + placeId + '\'' +
                ", eventDate=" + eventDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
