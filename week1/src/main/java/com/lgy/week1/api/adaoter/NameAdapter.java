package com.lgy.week1.api.adaoter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lgy.week1.R;
import com.lgy.week1.bean.ByNameBean;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {
    Context context;
    ArrayList<ByNameBean.ResultBean> list;

    public NameAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
        this.list=list;
    }

    public void setData(List<ByNameBean.ResultBean> datas) {
        list.clear();
        if (datas!=null){
            list.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NameAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.recy_iteam, null);
        return new NameViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NameAdapter.NameViewHolder nameViewHolder, final int i) {
        nameViewHolder.dree.setImageURI(list.get(i).getMasterPic());
        nameViewHolder.t1.setText(list.get(i).getCommodityName());
        nameViewHolder.t2.setText("￥："+list.get(i).getPrice()+"");
        nameViewHolder.t3.setText("已售"+list.get(i).getSaleNum()+"件");
        nameViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.setonclicklisener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView dree;
        private final TextView t1;
        private final TextView t2;
        private final TextView t3;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            dree = itemView.findViewById(R.id.dree);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
        }
    }
    private Cicklistener listener;

    public void result(Cicklistener listener) {
        this.listener = listener;
    }
    public interface Cicklistener {
        void setonclicklisener(int index);
    }
}
