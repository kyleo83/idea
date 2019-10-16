package com.yuyakaido.android.cardstackview.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.navigation.NavigationView
import com.yuyakaido.android.cardstackview.*
import java.util.*

class MainActivity : AppCompatActivity(), CardStackListener {

    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { CardStackAdapter(createSpots()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //commented out call to setup Navigation method that calls the navigation drawer on line 279
        //setupNavigation()
        setupCardStackView()
        setupButton()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    //commented out Navigation Drawer so that we can make changes
    //and re-implement the way we want it to look and function later.
    //navigation drawer
    /**
    private fun setupNavigation() {
        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // DrawerLayout
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        actionBarDrawerToggle.syncState()
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        // NavigationView
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.reload -> reload()
                R.id.add_spot_to_first -> addFirst(1)
                R.id.add_spot_to_last -> addLast(1)
                R.id.remove_spot_from_first -> removeFirst(1)
                R.id.remove_spot_from_last -> removeLast(1)
                R.id.replace_first_spot -> replace()
                R.id.swap_first_for_last -> swap()
            }
            drawerLayout.closeDrawers()
            true
        }
    }
**/

    private fun setupCardStackView() {
        initialize()
    }

    private fun setupButton() {
        val skip = findViewById<View>(R.id.skip_button)
        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }

        val rewind = findViewById<View>(R.id.rewind_button)
        rewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                    .setDirection(Direction.Bottom)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(DecelerateInterpolator())
                    .build()
            manager.setRewindAnimationSetting(setting)
            cardStackView.rewind()
        }

        val like = findViewById<View>(R.id.like_button)
        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(createSpots())
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    //reload function currently not being used so I commented it out
    /**
    private fun reload() {
        val old = adapter.getSpots()
        val new = createSpots()
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/


    //addFirst function currently not being used so I commented it out
    /**
    private fun addFirst(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                add(manager.topPosition, createSpot())
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/

    //addList function currently not being used so I commented it out
    /**
    private fun addLast(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            addAll(List(size) { createSpot() })
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/

    //removeFirst function currently not being used so I commented it out
    /**
    private fun removeFirst(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(manager.topPosition)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/

    //removeLast function currently not being used so I commented it out
    /**
    private fun removeLast(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(this.size - 1)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/

    //replace function currently not being used so I commented it out
    /**
    private fun replace() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            removeAt(manager.topPosition)
            add(manager.topPosition, createSpot())
        }
        adapter.setSpots(new)
        adapter.notifyItemChanged(manager.topPosition)
    }
    **/

    //swap function currently not being used so I commented it out
    /**
    private fun swap() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            val first = removeAt(manager.topPosition)
            val last = removeAt(this.size - 1)
            add(manager.topPosition, last)
            add(first)
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    **/

    //createSpot function currently not being used so I commented it out
    /**
    private fun createSpot(): Spot {
        return Spot(
                name = "Yasaka Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
        )
    }
    **/

    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()

        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-19_17-42-01.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-19_22-15-44.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-20_02-09-06.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-20_07-21-25.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-21_16-24-46.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-22_15-42-15.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-23_14-56-39.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-24_19-14-43.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-25_08-12-29.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-27_18-44-36.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-28_03-40-39.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-28_18-40-18.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-30_19-59-56.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-05-31_18-37-50.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-01_16-35-26.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-02_03-17-53.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-02_09-26-38.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-02_16-44-05.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-03_16-37-19.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-04_02-00-04.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-04_08-34-24.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-04_15-30-11.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-04_18-42-48.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-04_21-30-26.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-05_02-21-33.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-05_14-34-40.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-05_23-40-03.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-06_12-15-04.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-09_04-50-38.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-09_10-56-38.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-09_17-59-28.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-09_21-58-10.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-12_20-06-53.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-13_20-16-33.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-14_15-43-50.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-14_16-51-17.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-14_22-58-54.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-15_03-21-16.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-15_17-58-29.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-16_16-55-31.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-18_02-20-43.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-18_17-00-21.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-18_19-57-26.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-19_08-30-22.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-19_18-43-29.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-20_05-19-26.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-20_17-57-06.jpg"))
        spots.add(Spot(name = "", city = "", url = "http://alpenglow.online/Idea/_archidesignhome__2015-06-21_02-19-37.jpg"))

        return spots
    }

}
