package org.daimhim.widget.sa;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public abstract class SimpleLRVAdapter<T> extends ListAdapter<T,SimpleViewHolder> implements OnAdapterClickListener {
    protected SimpleLRVAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    protected SimpleLRVAdapter(@NonNull AsyncDifferConfig<T> config) {
        super(config);
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SimpleViewHolder simpleViewHolder = onCreateDataViewHolder(parent, viewType);
        simpleViewHolder.setOnAdapterClickListener(this);
        return simpleViewHolder;
    }


    @Override
    public final void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        onBindDataViewHolder(holder, position);
    }

    @NonNull
    public abstract SimpleViewHolder onCreateDataViewHolder(@NonNull ViewGroup parent, int viewType);


    public abstract void onBindDataViewHolder(@NonNull SimpleViewHolder holder, int position);

    @Override
    public void onItemClick(SimpleViewHolder viewHolder, View view, int position) {
        if (onItemClickListener == null) {
            return;
        }
        onItemClickListener.onItemClick(viewHolder, view, position);
    }

    @Override
    public boolean onItemLongClick(SimpleViewHolder viewHolder, View view, int position) {
        if (onItemLongClickListener == null) {
            return false;
        }
        return onItemLongClickListener.onItemLongClick(viewHolder, view, position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
