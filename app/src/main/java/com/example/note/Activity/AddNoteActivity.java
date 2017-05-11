package com.example.note.Activity;

/**
 * Created by 李志军 on 2017/5/11.
 */


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.note.R;
import com.example.note.datas.Note;
import com.example.note.util.DatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends AppCompatActivity {

    public final static String TAG = "AddNoteActivity";

    public final static int RESULT_ADD_OK = 1;

    private Toolbar mToolbar;

    @Bind(R.id.et_title)
    EditText etTitle;

    @Bind(R.id.et_content)
    EditText etContent;

    private DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ButterKnife.bind(this);

        setupToolbar();
    }

    /**
     * 设置toolbar
     */
    private void setupToolbar() {
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btn_save) void save() {
        dbAdapter = new DatabaseAdapter(this);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String createDate = format.format(curDate);
        String updateDate = createDate;
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        Note note = new Note(title, content, createDate, updateDate);

        dbAdapter.create(note);

        setResult(RESULT_ADD_OK);
        finish();
    }
}
