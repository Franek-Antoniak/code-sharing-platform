package com.code.sharing.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.code.sharing.platform.tools.RandomStringUUID;

import javax.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class CodeHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Column(columnDefinition="Text")
    private String code = "";
    private LocalDateTime date = LocalDateTime.now();
    @JsonProperty("views")
    private long viewsRestriction = 0;
    @JsonIgnore
    private boolean restriction = false;
    @JsonIgnore
    private String uniqueId;
    @JsonIgnore
    private boolean toRemove = false;
    @JsonIgnore
    private LocalDateTime endDate = null;

    public CodeHolder() {
    }

    // GETTERS AND SETTERS
    public boolean isToRemove() {
        return toRemove;
    }

    public void setToRemove(boolean toRemove) {
        this.toRemove = toRemove;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @JsonProperty("date")
    public String getDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    @JsonProperty("time")
    public long getTimeRestriction() {
        if(endDate != null)
            return Duration.between(LocalDateTime.now(), endDate).toSeconds();
        else
            return 0;
    }

    public void setTimeRestriction(long timeRestriction) {
        if(timeRestriction != 0)
            endDate = date.plusSeconds(timeRestriction);
    }

    public long getViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(long viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    public boolean isRestriction() {
        return restriction;
    }

    public void setRestriction(boolean restriction) {
        this.restriction = restriction;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }


    // EQUALS TO STRING AND HASH

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeHolder that = (CodeHolder) o;
        return viewsRestriction == that.viewsRestriction && restriction == that.restriction && toRemove == that.toRemove && id.equals(that.id) && code.equals(that.code) && date.equals(that.date) && Objects.equals(uniqueId, that.uniqueId) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, date, viewsRestriction, restriction, uniqueId, toRemove, endDate);
    }

    @Override
    public String toString() {
        return "CodeHolder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", viewsRestriction=" + viewsRestriction +
                ", restriction=" + restriction +
                ", uniqueId='" + uniqueId + '\'' +
                ", toRemove=" + toRemove +
                ", endDate=" + endDate +
                '}';
    }

    public void checkAndSetRestriction() {
        if(endDate != null || viewsRestriction != 0)
            setRestriction(true);
    }

    public void createAndSetUniqueId() {
        uniqueId = RandomStringUUID.getUnique();
    }

    public boolean setCurrentRestrictionsAndGetState() {
        if(this.toRemove)
            return true;
        if(viewsRestriction != 0) {
            viewsRestriction--;
            if(viewsRestriction == 0)
                toRemove = true;
        }
        if(endDate != null) {
            var now = LocalDateTime.now();
            return endDate.isEqual(now) || endDate.isBefore(now);
        }
        return false;
    }
}

