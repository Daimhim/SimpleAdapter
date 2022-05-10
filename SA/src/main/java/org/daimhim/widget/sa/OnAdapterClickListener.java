package org.daimhim.widget.sa;

import android.view.View;

public interface OnAdapterClickListener {

    void onItemClick(SimpleViewHolder viewHolder, View view, int position);
    boolean onItemLongClick(SimpleViewHolder viewHolder, View view, int position);
}
