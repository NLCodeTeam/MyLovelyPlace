package ru.nlct.mylovelyplace.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nlct.mylovelyplace.R
import ru.nlct.mylovelyplace.domain.models.Place

/**
 * Slider adapter - адаптер для списка предустановленных мест
 *
 * @property places - список мест
 * @property onItemClicked - обработка нажатия на элемент списка
 * @author Marianna Sabanchieva
 */

class SliderAdapter(
    private val places: MutableList<Place>,
    private val onItemClicked: (postId:Long) -> Unit
) : RecyclerView.Adapter<SliderCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderCard {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_card, parent, false)
        return SliderCard(view)
    }

    override fun onBindViewHolder(holder: SliderCard, position: Int) {
        holder.setContent(places[position].imageLink)
        holder.itemView.rootView.setOnClickListener {
            onItemClicked(places[position].id)
        }
    }

    fun setPlaces(placeList: List<Place>) {
        if (placeList.isNotEmpty()) {
            places.clear()
            places.addAll(placeList)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = places.size
}