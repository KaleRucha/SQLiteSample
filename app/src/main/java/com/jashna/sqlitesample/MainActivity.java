package com.jashna.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteListView = findViewById(R.id.noteListView);

        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();

    }

    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotePojo selectedNote = (NotePojo) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailActivity.class);
                editNoteIntent.putExtra(NotePojo.NOTE_EDI_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), NotePojo.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

    public void newNote(View view) {

        Intent newNoteIntent = new Intent(this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
}