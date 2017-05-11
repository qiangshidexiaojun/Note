package com.example.note.Activity;

/**
 * Created by 李志军 on 2017/5/11.
 */

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

public class ModifyNoteActivity extends AppCompatActivity {

    public final static String TAG = "ModifyNoteActivity";

    public final static int RESULT_MODIFY_OK = 2;

    private Toolbar mToolbar;

    @Bind(R.id.et_title)
    EditText etTitle;

    @Bind(R.id.et_content)
    EditText etContent;

    private DatabaseAdapter dbAdapter;

    //id
    private int id;

    //要修改的title
    private String title;

    //要修改的内容
    private String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);

        ButterKnife.bind(this);

        dbAdapter = new DatabaseAdapter(this);

        initDatas();
        setupToolbar();
    }


    /**
     * 初始化数据
     */
    private void initDatas() {
        id = getIntent().getIntExtra("id", 1);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        etTitle.setText(title);
        etTitle.setSelection(title.length());
        etContent.setText(content);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int _id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (_id == android.R.id.home) {
            finish();
        }

        if (_id == R.id.action_delete) {
            dbAdapter.remove(id);
            setResult(RESULT_MODIFY_OK);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_modify) void modify() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String updateDate = format.format(curDate);
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        Note note = new Note(id, title, content, updateDate);

        dbAdapter.update(note);

        setResult(RESULT_MODIFY_OK);
        finish();
    }
}
