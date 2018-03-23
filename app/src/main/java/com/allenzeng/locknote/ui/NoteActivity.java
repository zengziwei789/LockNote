package com.allenzeng.locknote.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.allenzeng.locknote.R;

public class NoteActivity extends AppCompatActivity {
    private SharedPreferences mSharedPref;
    private EditText noteEtv;
    private Button saveBtn;
    private static String KEY_NOTE = "key_note";
    int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteEtv = findViewById(R.id.note_etv);
        saveBtn = findViewById(R.id.save_btn);
        mSharedPref = this.getPreferences(Context.MODE_PRIVATE);
        noteEtv.setText(mSharedPref.getString(KEY_NOTE, ""));
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(noteEtv.getText())) {
                    SharedPreferences.Editor editor = mSharedPref.edit();
                    editor.putString(KEY_NOTE, noteEtv.getText().toString());
                    editor.commit();
                    NoteActivity.this.finish();
                }
            }
        });

        noteEtv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.note_etv) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            clickCount ++;
                            if(clickCount >= 2) {
                                view.getParent().requestDisallowInterceptTouchEvent(false);
                                clickCount = 0;
                            }
                            break;
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
