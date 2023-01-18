package com.jashna.sqlitesample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteAdapter extends ArrayAdapter<NotePojo> {

    public NoteAdapter(Context context, List<NotePojo> notes) {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        NotePojo note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_note_cell, parent, false);
        }

        TextView title = convertView.findViewById(R.id.TVCellTitle);
        TextView desc = convertView.findViewById(R.id.TVCellDesc);

        title.setText(note.getTitle());
        desc.setText(note.getDescription());

        return convertView;
    }
}
