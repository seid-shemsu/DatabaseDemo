package com.example.seid.databasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button view,add,reset;
    EditText text;
    TextView viewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.reset);
        view = (Button) findViewById(R.id.view);
        add =(Button) findViewById(R.id.add);
        text = (EditText) findViewById(R.id.name);
        viewName = (TextView) findViewById(R.id.viewName);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addName();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewName();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });
    }

    public void viewName(){
        Student stu = new Student(this);
        SQLiteDatabase db = stu.getReadableDatabase();
        String pro[] = {"name"};
        Cursor c = db.query("student",pro,null,null,null,null,null);
        try{
            if(c.moveToFirst()){
                do{
                    viewName.setText(c.getString(0));
                }while (c.moveToNext());
            }
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void addName(){
        Student stu = new Student(this);
        SQLiteDatabase db = stu.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name",text.getText().toString());
        long row = db.insert("student",null,value);
        String s = Long.toString(row);
        Toast.makeText(this,s+"name has been added",Toast.LENGTH_SHORT).show();
    }
    public void Reset(){
        Student stu = new Student(this);
        SQLiteDatabase db = stu.getReadableDatabase();
        stu.onUpgrade(db,1,2);
    }
}
