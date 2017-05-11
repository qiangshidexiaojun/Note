package com.example.note.Activity;

/**
 * Created by 李志军 on 2017/5/11.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.note.R;
import com.example.note.adapters.MainAdapter;
import com.example.note.datas.Note;
import com.example.note.util.DatabaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener{

    public final static String TAG = "MainActivity";

    public final static int REQUEST_ADD_OK = 1;

    public final static int REQUEST_MODIFY_OK = 2;

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private MainAdapter adapter;

    private List<Note> listDatas;

    private DatabaseAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView = ButterKnife.findById(this, R.id.recycler_view);
        dbAdapter = new DatabaseAdapter(this);
        listDatas = new ArrayList<Note>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(listDatas);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        setupToolbar();
//        setupDatas();
        initDatas();


    }

    /**
     * 设置toolbar
     */
    private void setupToolbar() {
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    /**
     * 添加测试数据
     */
    private void setupDatas() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String createDate = format.format(curDate);
        String updateDate = createDate;
        String title = "Believe";
        String content = "Believe in yourself.";
        Note note = new Note(title, content, createDate, updateDate);
        dbAdapter.create(note);
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        listDatas.clear();
        ArrayList<Note> list = dbAdapter.findLimit(15, 0);
        if (list.size() > 0) {
            for (Note note : list) {
                listDatas.add(note);
//                Log.d(TAG, note.toString());
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Note note = listDatas.get(position);
        Intent intent = new Intent(this, ModifyNoteActivity.class);
        intent.putExtra("id", note.getId());
        intent.putExtra("title", note.getTitle());
        intent.putExtra("content", note.getContent());
        startActivityForResult(intent, REQUEST_MODIFY_OK);
    }

    /**
     * 为添加按钮添加点击事件
     */
    @OnClick (R.id.btn_add) void add() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, REQUEST_ADD_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_OK && resultCode == REQUEST_ADD_OK) {
            initDatas();
        }
        if (requestCode == REQUEST_MODIFY_OK && resultCode == REQUEST_MODIFY_OK) {
            initDatas();
        }
    }
}
