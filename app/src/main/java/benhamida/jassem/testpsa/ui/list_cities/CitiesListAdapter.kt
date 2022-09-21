package benhamida.jassem.testpsa.ui.list_cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.testpsa.R
import benhamida.jassem.testpsa.databinding.ItemCityBinding

class CitiesListAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CitiesListAdapter.DataViewHolder>() {

    private var cities: List<City> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemCityBinding = DataBindingUtil.inflate<ItemCityBinding>(LayoutInflater.from(parent.context), R.layout.item_city, parent, false)
        return DataViewHolder(itemCityBinding)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener.onItemClickListener(cities.get(position))
        }
        return holder.bind(cities.get(position))
    }

    fun setData(list: List<City>) {
        cities = list
        notifyDataSetChanged()
    }

    inner class DataViewHolder(private val itemCityBinding: ItemCityBinding) :
            RecyclerView.ViewHolder(itemCityBinding.root) {

        fun bind(city: City) {
            itemCityBinding.cityName.text = city.name
        }
    }

    interface OnClickListener {
        fun onItemClickListener(city: City)
    }
}