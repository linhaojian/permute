package com.lhj.permute.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lhj.permute.PermuteView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private PermuteView p1,p2,p3,p4,p5,p6;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initProgress();
        initPoint();
        initGridView();
    }

    private void initPoint() {
        p4 = findViewById(R.id.p4);
        p4.setCircle(true);
        p4.setContent("");
        p5 = findViewById(R.id.p5);
        p5.setCircle(true);
        p5.setContent("");
        p5.setBackGroundColor(Color.parseColor("#ffffff"));
        p6 = findViewById(R.id.p6);
        p6.setCircle(true);
        p6.setContent("");
    }

    private void initGridView() {
        recyclerView = findViewById(R.id.recylcer);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new PremuteAdater(this));
    }

    private void initProgress(){
        p1 = findViewById(R.id.p1);
        p1.setCircle(true);
        p1.setContentColor(Color.parseColor("#4E7EAE"));
        p1.setBackGroundColor(Color.parseColor("#ffffff"));
        p1.setContent("1");
        p2 = findViewById(R.id.p2);
        p2.setCircle(true);
        p2.setContent("2");
        p3 = findViewById(R.id.p3);
        p3.setCircle(true);
        p3.setContent("3");
    }

    class PremuteAdater extends RecyclerView.Adapter{
        private ArrayList<Integer> list;
        private Context context;

        public PremuteAdater(Context context){
            this.context = context;
            list = new ArrayList<>();
            for(int i=1;i<10;i++){
                list.add(i);
            }
            list.add(-1);
            list.add(0);
            list.add(-1);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
            PermuteHolder permuteHolder = new PermuteHolder(view);
            return permuteHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
            PermuteHolder permuteHolder = (PermuteHolder) viewHolder;
            if(list.get(i)!=-1){
                permuteHolder.permuteView.setVisibility(View.VISIBLE);
                permuteHolder.permuteView.setContent(list.get(i)+"");
                permuteHolder.permuteView.setClickEnable(true);
                permuteHolder.permuteView.setCorner(10);
                permuteHolder.permuteView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, list.get(i)+"", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                permuteHolder.permuteView.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class PermuteHolder extends RecyclerView.ViewHolder{
            private PermuteView permuteView;

            public PermuteHolder(@NonNull View itemView) {
                super(itemView);
                permuteView = itemView.findViewById(R.id.permute);
            }
        }

    }

}
