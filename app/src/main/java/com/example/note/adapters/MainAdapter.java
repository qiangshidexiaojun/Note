package com.example.note.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.note.R;
import com.example.note.datas.Note;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 李志军 on 2017/5/11.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Note> listDatas;

    private int[] imgs = {R.drawable.img_bg1,R.drawable.img_bg2,
            R.drawable.img_bg3,R.drawable.img_bg4,};

    private OnItemClickListener itemClickListener;

    public MainAdapter(List<Note> listDatas) {
        this.listDatas = listDatas;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = listDatas.get(position);

        int imgBg = imgs[position%imgs.length];
        holder.img.setImageResource(imgBg);
        holder.tvLetter.setText(note.getTitle().substring(0, 1));
        holder.tvTitle.setText(note.getTitle());
        holder.tvSubtitle.setText(note.getContent());
        holder.tvDate.setText(note.getUpdateDate());
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView img;
        @Bind(R.id.tv_letter)
        TextView tvLetter;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_subtitle)
        TextView tvSubtitle;
        @Bind(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 设置点击事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
