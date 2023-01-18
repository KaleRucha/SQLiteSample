package com.jashna.sqlitesample;

import java.util.ArrayList;
import java.util.Date;

public class NotePojo {

    public static ArrayList<NotePojo> noteArrayList = new ArrayList<>();
    public static String NOTE_EDI_EXTRA = "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public NotePojo() {
    }

    public NotePojo(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public NotePojo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static NotePojo getNoteForId(int passedNoteId) {
        for (NotePojo notePojo : noteArrayList){
            if (notePojo.getId() == passedNoteId)
                return notePojo;
        }
        return null;

    }

    public static ArrayList<NotePojo> nonDeletedNotes(){
        ArrayList<NotePojo> nonDeleted = new ArrayList<>();
        for (NotePojo note : noteArrayList){
            if (note.getDeleted() == null)
                nonDeleted.add(note);
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
