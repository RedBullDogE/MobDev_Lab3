package ua.kpi.comsys.iv7213

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import ua.kpi.comsys.iv7213.adapters.ViewPagerAdapter
import ua.kpi.comsys.iv7213.fragments.AnotherOneFragment
import ua.kpi.comsys.iv7213.fragments.MovieListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieListFragment(), "Movies")
        adapter.addFragment(AnotherOneFragment(), "tbc")

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

//        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_video_library_24)
//        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_block_24)
    }

}

