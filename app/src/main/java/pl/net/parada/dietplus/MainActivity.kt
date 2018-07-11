package pl.net.parada.dietplus

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.github.florent37.materialviewpager.header.HeaderDesign
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import pl.net.parada.dietplus.model.FoodIngridient

class MainActivity : AppCompatActivity() {

    val ingridientArray by lazy {
        val jsonStream = assets.open("base.json")
        val size = jsonStream.available()
        val buffor = ByteArray(size)
        jsonStream.read(buffor)
        jsonStream.close()
        val jsonString = String(buffor, Charsets.UTF_8)
        Gson().fromJson<List<FoodIngridient>>(jsonString, FOOD_ING_TYPE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPager()
    }

    private fun initPager() {
        materialviewpager.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int) = RecyclerViewFragment.newInstance(ingridientArray[p0])
            override fun getCount() = ingridientArray.size
            override fun getPageTitle(position: Int) = ingridientArray[position].name
        }
        materialviewpager.setMaterialViewPagerListener({ page ->
            HeaderDesign.fromColorResAndDrawable(R.color.blue, ingridientArray[page].drawable)
        })
        materialviewpager.viewPager.offscreenPageLimit = materialviewpager.viewPager.adapter.count

        materialviewpager.pagerTitleStrip.setViewPager(materialviewpager.viewPager)
    }

    companion object {
        private val FOOD_ING_TYPE = object : TypeToken<List<FoodIngridient>>() {}.type
    }
}
