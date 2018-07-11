package pl.net.parada.dietplus.model

import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import pl.net.parada.dietplus.App
import java.io.Serializable

data class FoodIngridient(val id: Int,
                          val name: String,
                          val desc: String,
                          val danger: String,
                          val foodExample: String,
                          private val dailyMale: String,
                          private val dailyFem: String,
                          private val unit: IntakeDoseUnit,
                          private val imgName: String) : Serializable {
    val intake
        get() = """${"\u2642"} = $dailyMale ${unit.unitName}
                |  ${"\u2640"} = $dailyFem ${unit.unitName}""".trimMargin()

    val drawable: Drawable?
        get() {
            val ctx = App.appContext
            val id = ctx.resources.getIdentifier(imgName, DRAWABLE_TYPE_DEF, ctx.packageName)
            return ResourcesCompat.getDrawable(ctx.resources, id, null)
        }

    companion object {
        const val DRAWABLE_TYPE_DEF = "drawable"
    }

}

enum class IntakeDoseUnit(val unitName: String) {
    GRAM("gram"), MGRAM("miligram"), UGRAM("mikrogram"), IU("j.m."), UNKNOWN("unknown")
}
