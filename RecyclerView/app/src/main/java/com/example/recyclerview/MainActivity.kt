package com.example.recyclerview

import Adapter
import Adapter.CellClickListener
import ColorData

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = Adapter(this, fetchList(), this)
        recyclerView.adapter = adapter
    }

    private fun fetchList(): ArrayList<ColorData> {
        val list = ArrayList<ColorData>()

        val colorList = ArrayList<ColorData>()
        colorList.add(ColorData("Red", 0xFF0000))
        colorList.add(ColorData("Green", 0x00FF00))
        colorList.add(ColorData("Blue", 0x0000FF))
        colorList.add(ColorData("Yellow", 0xFFFF00))
        colorList.add(ColorData("Orange", 0xFFA500))

        for (i in 0 until colorList.size) {
            val data = colorList[i]
            val model = ColorData(
                data.colorName,
                data.colorHex)
            list.add(model)
        }
        return list
    }

    override fun onCellClickListener(data: ColorData) {
        Toast.makeText(this, "IT'S ${data.colorName.toUpperCase()}", Toast.LENGTH_SHORT).show()
    }
}
