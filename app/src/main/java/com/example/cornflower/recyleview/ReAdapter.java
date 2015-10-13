package com.example.cornflower.recyleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xiejingbao on 2015/10/13.
 */
public class ReAdapter  extends RecyclerView.Adapter<MyHolder>{
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onclickListener;
    public  ReAdapter (Context mContext,List<String> mDatas){
        this.mContext = mContext;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onclickListener){
        this.onclickListener=onclickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        if(onclickListener!=null){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListener.onItemClick(view,holder.getLayoutPosition());
            }
        });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onclickListener.onLongItemClick(view,holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(View v, int postion);
        void onLongItemClick(View v, int postion);
    }

    /**
     * 添加一行
     */
    public void addItem(int pos){
         mDatas.add(pos,"addItem");
        notifyItemInserted(pos);
    }

    /**
     * 删除一行
     * @param pos
     */
    public void deleteItem(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

}

    class MyHolder extends RecyclerView.ViewHolder{
       TextView tv;

    public MyHolder(View itemView) {
        super(itemView);
        tv= (TextView) itemView.findViewById(R.id.tv);
    }


}