package cz.muni.fi.pv256.hw9.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ViewSwitcher
import androidx.recyclerview.widget.RecyclerView
import coil.load
import cz.muni.fi.pv256.hw9.R
import cz.muni.fi.pv256.hw9.data.Character
import cz.muni.fi.pv256.hw9.databinding.ListItemBinding

class MainAdapter(private val empty: ViewSwitcher, private val onClick: (Character) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var items = listOf<Character>()
        set(value) {
            field = value
            notifyDataSetChanged()
            if (value.isEmpty()) {
                if (empty.currentView.id != R.id.empty) {
                    empty.showNext()
                }
            } else if (empty.currentView.id != R.id.recycler_view) {
                empty.showNext()
            }
        }

    class ViewHolder(itemBinding: ListItemBinding, val onClick: (Character) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val img = itemBinding.img
        private val text = itemBinding.title

        fun bind(character: Character) {
            img.load(character.image)
            text.text = character.name
            // root view of the viewHolder
            itemView.setOnClickListener { onClick(character) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
