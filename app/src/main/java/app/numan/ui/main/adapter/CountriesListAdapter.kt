package app.numan.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.numan.R
import app.numan.data.model.Country
import app.numan.ui.main.view.DetailsActivity

class CountriesListAdapter(var mContext: Context) :
    ListAdapter<Country, CountriesListAdapter.CountriesViewHolder>(
        WORDS_COMPARATOR
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder.create(parent, mContext)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class CountriesViewHolder(itemView: View, context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val countryItemView: TextView = itemView.findViewById(R.id.txtCountry)
        private val countryFItemView: ImageView = itemView.findViewById(R.id.imgFav)
        private val mContext: Context = context

        fun bind(current: Country?) {
            countryItemView.text = current!!.country
            countryFItemView.isVisible = current.isFav == true
            countryItemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(mContext, DetailsActivity::class.java)
                intent.putExtra("Country", current.country)
                intent.putExtra("CountryIsFav", current.isFav)
                mContext.startActivity(intent)
            })

        }

        companion object {
            fun create(parent: ViewGroup, mContext: Context): CountriesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CountriesViewHolder(view, mContext)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.country == newItem.country
            }
        }
    }
}