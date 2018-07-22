package br.com.paulosalvatore.movilenext_aula2_kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.paulosalvatore.movilenext_aula2_kotlin.R
import br.com.paulosalvatore.movilenext_aula2_kotlin.api.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryAdapter(
		private val items: List<Repository>,
		private val context: Context,
		private val listener: (Repository) -> Unit
) : Adapter<RepositoryAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(context).inflate(
				R.layout.repository_item,
				parent,
				false
		)

		return ViewHolder(view)
	}

	override fun getItemCount() = items.size
	/*override fun getItemCount(): Int {
		return items.size
	}*/

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items[position]
		holder.bindView(item, listener)
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		@SuppressLint("SetTextI18n")
		fun bindView(item: Repository,
		             listener: (Repository) -> Unit) = with(itemView) {
			/*ivMain.setImageDrawable(
					ContextCompat.getDrawable(
							context,
							item.imageResourceId
					)
			)*/

			// Mesma coisa que:
//			ivMain.setImageResource(item.imageResourceId)
			Glide.with(context).load(item.owner?.avatar_url).into(ivMain)

			tvTitle.text = item.name
			tvOwner.text = item.owner?.login
			tvDescription.text = item.description

			setOnClickListener {
				listener(item)
			}
		}
	}
}
