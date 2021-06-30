package com.indialone.draganddroprecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() , OnStartDragListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var touchHelper : ItemTouchHelper
    private lateinit var dragDropAdapter : DragDropRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        // initialize the adapter
        dragDropAdapter = DragDropRecyclerAdapter(getImagesList() , this)

        // get callback
        val callback = ItemMoveCallbackListener(dragDropAdapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dragDropAdapter
    }

    private fun getImagesList(): ArrayList<Int> {
        return arrayListOf(
            R.drawable.bedroom1,
            R.drawable.bedroom2,
            R.drawable.bedroom3,
            R.drawable.bedroom4,
            R.drawable.bedroom5,
            R.drawable.bedroom6,
            R.drawable.bedroom7,
            R.drawable.bedroom8,
            R.drawable.bedroom9,
            R.drawable.bedroom10,
            R.drawable.crowne_plaze,
            R.drawable.movie_avatar,
            R.drawable.movie_birds_of_prey,
            R.drawable.movie_captain_marvel,
            R.drawable.movie_dark_knight,
            R.drawable.movie_thor,
            R.drawable.movie_wonder_woman
        )
    }

    override fun onStartDrag(viewHolder: DragDropRecyclerAdapter.DragDropViewHolder) {
        touchHelper.startDrag(viewHolder)
    }
}