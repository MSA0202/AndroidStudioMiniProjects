package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private Button vieww;
    private Button remove;
    private EditText addText;
    private EditText removeItem;
    private EditText viewwText;
    ArrayList<String> todo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.addtd);
        vieww = findViewById(R.id.viewtd);
        addText = findViewById(R.id.entertd);
        viewwText = findViewById(R.id.viewlist);
        remove = findViewById(R.id.rm);
        removeItem = findViewById(R.id.enterrm);

        // for the add to list button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });

        // for viewing the list
        vieww.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewList();
            }

        });

        // for the remove button
        remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                removeButton();
                viewList(); // Automatically view the list after removing an item
            }

        });

        // Set the block to empty once clicked
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem.setText("");
            }
        });

        // Set the block to empty once clicked
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText.setText("");
            }
        });

    }

    public void addButton(){
        String text = addText.getText().toString();
        if(!text.isEmpty()){
            todo.add(text);
            addText.setText(""); // Reset the textbox to nothing to so we dont have to backspace
            Toast.makeText(MainActivity.this, "Text added to ArrayList", Toast.LENGTH_SHORT).show(); // To indicate button clicked and stuff added to list
        }
    }

    public void removeButton() {
        String stringIndex = removeItem.getText().toString().trim();
        if (!stringIndex.isEmpty()) {
            try {
                int rmIndex = Integer.parseInt(stringIndex);
                if (todo.size() != 0 && rmIndex >= 0 && rmIndex < todo.size()) {
                    todo.remove(rmIndex);
                } else {
                    // Handle invalid index error
                    Toast.makeText(MainActivity.this, "Invalid item number entered.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                // Handle NumberFormatException
                Toast.makeText(MainActivity.this, "Invalid input. Please enter the item number.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < todo.size(); i++) {
            stringBuilder.append("Task " + i + " : " + todo.get(i)).append("\n");
        }
        viewwText.setText(stringBuilder.toString());
    }



}