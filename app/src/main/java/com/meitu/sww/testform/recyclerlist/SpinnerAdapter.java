package com.meitu.sww.testform.recyclerlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.meitu.sww.testform.model.SpinnerNode;

import java.util.ArrayList;

/**
 * @author ShaoWenWen
 * @date 2019/5/29
 */
public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.Holder> {

    private ArrayList<SpinnerNode> arrayList;
    private OnSpinnerItemClickListener onItemClickListener;

    public SpinnerAdapter(ArrayList<SpinnerNode> arrayList) {
        this.arrayList = arrayList;
    }

    public void setOnItemClickListener(OnSpinnerItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(new SpinnerItemView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int positon) {
        ((SpinnerItemView) holder.itemView).updateViewByData(arrayList.get(positon).getValue());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(arrayList.get(positon), positon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setArrayList(ArrayList<SpinnerNode> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public interface OnSpinnerItemClickListener {
        void onItemClick(SpinnerNode node, int positon);
    }

}
