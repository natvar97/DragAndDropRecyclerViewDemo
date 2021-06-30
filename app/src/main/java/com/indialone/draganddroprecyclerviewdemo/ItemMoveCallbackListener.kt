package com.indialone.draganddroprecyclerviewdemo

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.indialone.draganddroprecyclerviewdemo.DragDropRecyclerAdapter.DragDropViewHolder

class ItemMoveCallbackListener(
    val adapter: DragDropRecyclerAdapter
) : ItemTouchHelper.Callback() { // implements ItemTouchHelper class and implement the Callback abstract class
    // above class has following methods for

    // 1-> move flags up/down and start/end
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    // 2-> onMove the it is setting up the positions of items of recycler view
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    // 3-> swipe disabled
    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    // 4-> long press disabled for recycler view items
    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is DragDropViewHolder) {
                adapter.onRowSelected(viewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is DragDropViewHolder) {
            adapter.onRowClear(viewHolder)
        }
    }

    interface Listener {
        // use while moving the recycler item
        fun onRowMoved(fromPosition: Int, toPosition: Int)

        //use while the selection of recycler item
        fun onRowSelected(itemViewHolder: DragDropViewHolder)

        //use while we left selected recycler item in the recycler view
        fun onRowClear(itemViewHolder: DragDropViewHolder)
    }

}