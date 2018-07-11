package pl.net.parada.dietplus

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import kotlinx.android.synthetic.main.recycler_fragment_layout.*
import pl.net.parada.dietplus.model.FoodIngridient
import pl.net.parada.dietplus.recycler.RecyclerViewAdapter

class RecyclerViewFragment : android.support.v4.app.Fragment() {

    private val foodIngridient by lazy { arguments[FOOD_TAG] as FoodIngridient }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = inflater?.inflate(R.layout.recycler_fragment_layout, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        fragment_recycler.layoutManager = LinearLayoutManager(activity)
        fragment_recycler.setHasFixedSize(true)

        fragment_recycler.addItemDecoration(MaterialViewPagerHeaderDecorator())
        fragment_recycler.adapter = RecyclerViewAdapter(initRecyclerList())
    }

    private fun initRecyclerList(): ArrayList<Pair<String, String>> {
        val list = ArrayList<Pair<String, String>>()
        list.add(Pair(foodIngridient.name, foodIngridient.desc))
        list.add(Pair("Zapotrzebowanie", foodIngridient.intake))
        list.add(Pair("Zagrozenia", foodIngridient.danger))
        list.add(Pair("Zalecane", foodIngridient.foodExample))
        return list
    }

    companion object {
        fun newInstance(foodIngridient: FoodIngridient): RecyclerViewFragment {
            val recyclerViewFragment = RecyclerViewFragment()
            val bundle = Bundle()
            bundle.putSerializable(FOOD_TAG, foodIngridient)
            recyclerViewFragment.arguments = bundle
            return recyclerViewFragment
        }

        const val FOOD_TAG = "FOOD_TAG"
    }
}