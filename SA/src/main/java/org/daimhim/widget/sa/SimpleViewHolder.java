package org.daimhim.widget.sa;

import android.util.SparseArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.SoftReference;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private SoftReference<OnAdapterClickListener> onAdapterClickListenerSoftReference;
    private SimpleOnClickListener simpleOnClickListener;
    private SimpleOnLongClickListener simpleOnLongClickListener;

    private final SparseArray<View> viewSparseArray;

    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>(0);
    }

    public void setOnAdapterClickListener(OnAdapterClickListener onAdapterClickListener) {
        if (onAdapterClickListener == null) {
            return;
        }
        onAdapterClickListenerSoftReference = new SoftReference<>(onAdapterClickListener);
    }

    public void bindClickListener(View view) {
        if (view == null) {
            return;
        }
        if (onAdapterClickListenerSoftReference == null) {
            return;
        }
        OnAdapterClickListener onAdapterClickListener = onAdapterClickListenerSoftReference.get();
        if (onAdapterClickListener == null) {
            return;
        }
        if (simpleOnClickListener == null) {
            simpleOnClickListener = new SimpleOnClickListener(this, onAdapterClickListener);
        }
        view.setOnClickListener(simpleOnClickListener);
    }

    public void bindClickListener(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
        }
        bindClickListener(view);
    }

    public void bindLongClickListener(View view) {
        if (view == null) {
            return;
        }
        if (onAdapterClickListenerSoftReference == null) {
            return;
        }
        OnAdapterClickListener onAdapterClickListener = onAdapterClickListenerSoftReference.get();
        if (onAdapterClickListener == null) {
            return;
        }
        if (simpleOnLongClickListener == null) {
            simpleOnLongClickListener = new SimpleOnLongClickListener(this, onAdapterClickListener);
        }
        view.setOnLongClickListener(simpleOnLongClickListener);
    }

    public void bindLongClickListener(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
        }
        bindLongClickListener(view);
    }

    public <V extends View> V findViewById(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
        }
        if (view != null) {
            return (V) view;
        }
        return null;
    }

    private static class SimpleOnClickListener implements View.OnClickListener {
        private final SoftReference<SimpleViewHolder> viewHolderSoftReference;
        private final SoftReference<OnAdapterClickListener> adapterClickListenerSoftReference;

        public SimpleOnClickListener(SimpleViewHolder viewHolder, OnAdapterClickListener onItemClickListener) {
            viewHolderSoftReference = new SoftReference<>(viewHolder);
            adapterClickListenerSoftReference = new SoftReference<>(onItemClickListener);
        }

        @Override
        public void onClick(View v) {
            if (adapterClickListenerSoftReference.get() == null) {
                return;
            }
            if (viewHolderSoftReference.get() == null) {
                return;
            }
            adapterClickListenerSoftReference
                    .get()
                    .onItemClick(
                            viewHolderSoftReference.get(),
                            v,
                            viewHolderSoftReference.get()
                                    .getBindingAdapterPosition()
                    );
        }
    }

    private static class SimpleOnLongClickListener implements View.OnLongClickListener {
        private final SoftReference<SimpleViewHolder> viewHolderSoftReference;
        private final SoftReference<OnAdapterClickListener> adapterClickListenerSoftReference;

        public SimpleOnLongClickListener(SimpleViewHolder viewHolder, OnAdapterClickListener onItemLongClickListener) {
            viewHolderSoftReference = new SoftReference<>(viewHolder);
            adapterClickListenerSoftReference = new SoftReference<>(onItemLongClickListener);
        }


        @Override
        public boolean onLongClick(View view) {
            if (adapterClickListenerSoftReference.get() == null) {
                return false;
            }
            if (viewHolderSoftReference.get() == null) {
                return false;
            }
            return adapterClickListenerSoftReference
                    .get()
                    .onItemLongClick(
                            viewHolderSoftReference.get(),
                            view,
                            viewHolderSoftReference.get()
                                    .getBindingAdapterPosition()
                    );
        }
    }
}
