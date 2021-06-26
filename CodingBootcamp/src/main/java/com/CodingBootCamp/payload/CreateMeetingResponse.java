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
public class CreateMeetingResponse {

    private CreateMeetingPayload response;

    public CreateMeetingPayload getResponse() {
        return response;
    }

    public void setResponse(CreateMeetingPayload response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CreateMeetingResponse [" + response + "]";
    }

}
