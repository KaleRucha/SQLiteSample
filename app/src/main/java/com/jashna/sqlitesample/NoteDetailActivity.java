package com.jashna.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText ETTitle, ETDesc;
    private Button btnDelete;
    private NotePojo selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        ETTitle = findViewById(R.id.ETTitle);
        ETDesc = findViewById(R.id.ETDescription);
        btnDelete= findViewById(R.id.deleteNoteButton);

        checkForEditNote();
    }

    private void checkForEditNote() {
        Intent prevIntent = getIntent();
        int passedNoteId = prevIntent.getIntExtra(NotePojo.NOTE_EDI_EXTRA, -1);
        selectedNote = NotePojo.getNoteForId(passedNoteId);

        if (selectedNote != null){
            ETTitle.setText(selectedNote.getTitle());
            ETDesc.setText(selectedNote.getDescription());
        }
        else {
            btnDelete.setVisibility(View.INVISIBLE);
        }
    }

    public void saveNote(View view) {

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(ETTitle.getText());
        String desc = String.valueOf(ETDesc.getText());

        if (selectedNote == null) {
            int id = NotePojo.noteArrayList.size();
            NotePojo newPojo = new NotePojo(id, title, desc);
            NotePojo.noteArrayList.add(newPojo);
            sqLiteManager.addNoteToDatabase(newPojo);
        }
        else {
            selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }
        finish();
    }

    public void deleteNote(View view) {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}