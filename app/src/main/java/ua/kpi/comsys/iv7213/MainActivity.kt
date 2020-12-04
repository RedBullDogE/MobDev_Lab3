package ua.kpi.comsys.iv7213


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ua.kpi.comsys.iv7213.adapters.CustomMovieListAdapter
import ua.kpi.comsys.iv7213.adapters.ViewPagerAdapter
import ua.kpi.comsys.iv7213.fragments.GalleryFragment
import ua.kpi.comsys.iv7213.fragments.MovieListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar?.hide()

        setupTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val listView = findViewById<ListView>(R.id.movieListView)

        val searchItem = menu.findItem(R.id.searchView)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    (listView?.adapter as CustomMovieListAdapter).filter(newText)
                    return true
                }
            })

        }

        return super.onCreateOptionsMenu(menu)
    }


    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieListFragment(), "Movies")
        adapter.addFragment(GalleryFragment(), "Gallery")

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_video_library_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_perm_media_24)
    }

}

