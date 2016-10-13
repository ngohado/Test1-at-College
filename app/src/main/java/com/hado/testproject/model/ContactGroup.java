package com.hado.testproject.model;

import java.util.ArrayList;

/**
 * Created by Hado on 12-Oct-16.
 */

public class ContactGroup {
    public String groupName;
    public String dateTaken;
    public ArrayList<Contact> contacts = new ArrayList<>();

    public ContactGroup(String groupName, String dateTaken) {
        this.groupName = groupName;
        this.dateTaken = dateTaken;
    }
}
