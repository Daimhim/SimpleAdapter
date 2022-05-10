package org.daimhim.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.daimhim.widget.sa.OnItemClickListener;
import org.daimhim.widget.sa.OnItemLongClickListener;
import org.daimhim.widget.sa.SimpleRVAdapter;
import org.daimhim.widget.sa.SimpleViewHolder;

public class MainActivity extends Activity {


    private RecyclerView viewById;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.rv_list);
        testAdapter = new TestAdapter();
        viewById.setAdapter(testAdapter);
        testAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(SimpleViewHolder viewHolder, View view, int position) {
                Toast.makeText(view.getContext(),String.format("第%s个",position),Toast.LENGTH_SHORT).show();
            }
        });
        testAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(SimpleViewHolder viewHolder, View view, int position) {
                Toast.makeText(view.getContext(),String.format("长第%s个",position),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    static class TestAdapter extends SimpleRVAdapter {

        @NonNull
        @Override
        public SimpleViewHolder onCreateDataViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.i("TestAdapter","onCreateDataViewHolder start");
            SimpleViewHolder holder = new SimpleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_item, parent, false));
            Log.i("TestAdapter","onCreateDataViewHolder end");
            Log.i("TestAdapter","ClickListener start");
            holder.bindClickListener(R.id.tv_item);
            holder.bindLongClickListener(R.id.tv_item);
            Log.i("TestAdapter","ClickListener end");
            return holder;
        }

        @Override
        public void onBindDataViewHolder(@NonNull SimpleViewHolder holder, int position) {
            Log.i("TestAdapter","onBindDataViewHolder start");
            holder.<TextView>findViewById(R.id.tv_item).setText(String.format("第%s个",position));
            Log.i("TestAdapter","onBindDataViewHolder end");

        }


        @Override
        public int getItemCount() {
            return 20;
        }
    }

}