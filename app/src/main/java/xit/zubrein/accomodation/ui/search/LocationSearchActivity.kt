package xit.zubrein.accomodation.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_location_search.*
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.adapter.LocationAdapter
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityLocationSearchBinding

@AndroidEntryPoint
class LocationSearchActivity : BaseActivity<ActivityLocationSearchBinding>() {

    val adapter by lazy { LocationAdapter(this) }

    override fun getView() = R.layout.activity_location_search

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        binding.searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        binding.searchView.apply {
            setIconifiedByDefault(false);
            setFocusable(true);
            setIconified(false);
            requestFocusFromTouch();
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                progressBar.visibility = View.VISIBLE
                searchViewModel.getLocation(query).observe(
                    this@LocationSearchActivity,
                    {
                        progressBar.visibility = View.INVISIBLE
                        binding.recyclerView.adapter = adapter
                        adapter.addItems(it)
                        Log.d(TAG, "onQueryTextSubmit: $it")
                    })
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                progressBar.visibility = View.VISIBLE
                searchViewModel.getLocation(newText).observe(this@LocationSearchActivity,
                    {
                        progressBar.visibility = View.INVISIBLE
                        binding.recyclerView.adapter = adapter
                        adapter.addItems(it)
                    })
                return false
            }
        })

        binding.back.setOnClickListener {
            finish()
        }

    }


}