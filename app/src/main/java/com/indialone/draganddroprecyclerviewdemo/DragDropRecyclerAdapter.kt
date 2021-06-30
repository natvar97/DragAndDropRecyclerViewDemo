package com.indialone.draganddroprecyclerviewdemo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.DragStartHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class DragDropRecyclerAdapter(
    private val list: MutableList<Int>,
    private val dragListener : OnStartDragListener // for dragging the view Holder item
) : RecyclerView.Adapter<DragDropRecyclerAdapter.DragDropViewHolder>(),
    ItemMoveCallbackListener.Listener {  // when we make a recycler view item to move up or down
    // Listener is an interface inside the ItemMoveCallbackListener class
    //this class is use when calling the item to move

    class DragDropViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // view holder is as usual for recycler view
        val ivImage = itemView.findViewById<ImageView>(R.id.iv_image)
//        val ivDrag = itemView.findViewById<ImageView>(R.id.iv_drag)
        fun bind(image: Int) {
            Glide.with(itemView.context)
                .load(image)
                .into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragDropViewHolder {
        // this is also as usual for recycler view
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return DragDropViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: DragDropViewHolder, position: Int) {
        val image = list[position]
        holder.bind(image)

        // adding this setOnTouchListener for iv_drag so when i touch it enabled to drag up or down
        holder.itemView.findViewById<ImageView>(R.id.iv_drag).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                dragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(itemViewHolder: DragDropViewHolder) {
        itemViewHolder.ivImage.alpha = 0.6f
    }

    override fun onRowClear(itemViewHolder: DragDropViewHolder) {
        itemViewHolder.ivImage.alpha = 1.0f
    }


}