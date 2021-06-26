/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CodingBootCamp.payload;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vineet Raj
 */
@XmlRootElement(name="response")
public class CreateMeetingPayload {

    private String returncode;
    private String meetingID;
    private Object internalMeetingID;
    private String parentMeetingID;
    private String attendeePW;
    private String moderatorPW;
    private double createTime;
    private int voiceBridge;
    private String dialNumber;
    private String createDate;
    private boolean hasUserJoined;
    private int duration;
    private boolean hasBeenForciblyEnded;
    private String messageKey;
    private String message;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    public Object getInternalMeetingID() {
        return internalMeetingID;
    }

    public void setInternalMeetingID(Object internalMeetingID) {
        this.internalMeetingID = internalMeetingID;
    }

    public String getParentMeetingID() {
        return parentMeetingID;
    }

    public void setParentMeetingID(String parentMeetingID) {
        this.parentMeetingID = parentMeetingID;
    }

    public String getAttendeePW() {
        return attendeePW;
    }

    public void setAttendeePW(String attendeePW) {
        this.attendeePW = attendeePW;
    }

    public String getModeratorPW() {
        return moderatorPW;
    }

    public void setModeratorPW(String moderatorPW) {
        this.moderatorPW = moderatorPW;
    }

    public double getCreateTime() {
        return createTime;
    }

    public void setCreateTime(double createTime) {
        this.createTime = createTime;
    }

    public int getVoiceBridge() {
        return voiceBridge;
    }

    public void setVoiceBridge(int voiceBridge) {
        this.voiceBridge = voiceBridge;
    }

    public String getDialNumber() {
        return dialNumber;
    }

    public void setDialNumber(String dialNumber) {
        this.dialNumber = dialNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isHasUserJoined() {
        return hasUserJoined;
    }

    public void setHasUserJoined(boolean hasUserJoined) {
        this.hasUserJoined = hasUserJoined;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHasBeenForciblyEnded() {
        return hasBeenForciblyEnded;
    }

    public void setHasBeenForciblyEnded(boolean hasBeenForciblyEnded) {
        this.hasBeenForciblyEnded = hasBeenForciblyEnded;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
