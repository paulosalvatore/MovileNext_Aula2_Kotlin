package br.com.paulosalvatore.movilenext_aula2_kotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.paulosalvatore.movilenext_aula2_kotlin.R
import br.com.paulosalvatore.movilenext_aula2_kotlin.adapter.ProgrammingLanguageAdapter
import br.com.paulosalvatore.movilenext_aula2_kotlin.adapter.RepositoryAdapter
import br.com.paulosalvatore.movilenext_aula2_kotlin.api.GithubRepositoriesResult
import br.com.paulosalvatore.movilenext_aula2_kotlin.api.RepositoryRetriever
import br.com.paulosalvatore.movilenext_aula2_kotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

	private val repositoryRetriever = RepositoryRetriever()

	private val callback = object : Callback<GithubRepositoriesResult> {
		override fun onFailure(call: Call<GithubRepositoriesResult>?,
		                       t: Throwable?) {
			longToast("Fail loading repositories.")
		}

		override fun onResponse(call: Call<GithubRepositoriesResult>?,
		                        response: Response<GithubRepositoriesResult>?) {
			longToast("Load finished.")

			response?.isSuccessful.let {
				response?.body()?.repositories?.let {
					recyclerView.adapter =
							RepositoryAdapter(
									it,
									this@MainActivity) {
								longToast("Clicked item: ${it.full_name}")
							}
				}
			}
		}

	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		loadDefaultRecyclerView()

		btReload.setOnClickListener {
			loadDefaultRecyclerView()
		}
	}

	private fun loadDefaultRecyclerView() {
		recyclerView.adapter = ProgrammingLanguageAdapter(
				recyclerViewItems(),
				this) {
			longToast("Loading ${it.title} repositories...")

			repositoryRetriever.getLanguageRepositories(
					callback,
					it.title
			)
		}

		// Lista na vertical
		val layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
	}

	private fun recyclerViewItems(): List<ProgrammingLanguage> {
		val kotlin = ProgrammingLanguage(
				R.drawable.kotlin,
				"Kotlin",
				2010,
				getString(R.string.kotlin_description)
		)

		return listOf(kotlin, kotlin)
	}
}
