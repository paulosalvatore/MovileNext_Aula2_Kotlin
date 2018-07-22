package br.com.paulosalvatore.movilenext_aula2_kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.paulosalvatore.movilenext_aula2_kotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.programming_language_item.view.*
import br.com.paulosalvatore.movilenext_aula2_kotlin.R

class ProgrammingLanguageAdapter(
		private val items: List<ProgrammingLanguage>,
		private val context: Context,
		private val listener: (ProgrammingLanguage) -> Unit
) : Adapter<ProgrammingLanguageAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(context).inflate(
				R.layout.programming_language_item,
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
		fun bindView(item: ProgrammingLanguage,
		             listener: (ProgrammingLanguage) -> Unit) = with(itemView) {
			ivMain.setImageDrawable(
					ContextCompat.getDrawable(
							context,
							item.imageResourceId
					)
			)

			// Mesma coisa que:
//			ivMain.setImageResource(item.imageResourceId)

			tvTitle.text = item.title
//			tvLaunchYear.text = item.year.toString()
			tvLaunchYear.text = "Year: ${item.year}"
			tvDescription.text = item.description

			setOnClickListener {
				listener(item)
			}
		}
	}
}
