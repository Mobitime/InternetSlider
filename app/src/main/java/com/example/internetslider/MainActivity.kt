package com.example.internetslider

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.internetslider.R
import com.example.internetslider.model.Page
import com.example.internetslider.ui.ViewPagerAdapter
import com.example.internetslider.ui.ViewPagerFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        setupViewPager()
        setupBackPressHandler()
    }

    private fun setupViewPager() {
        val pages = listOf(
            Page("Новости", "https://lenta.ru/", R.drawable.news),
            Page("Музыка", "https://music.yandex.ru/home", R.drawable.music),
            Page("Кино", "https://m.yandex.by/video/movies", R.drawable.movie),
            Page("Погода", "https://www.gismeteo.ru/", R.drawable.weather)
        )

        val adapter = ViewPagerAdapter(this, pages)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(pages[position].iconResId)
            tab.contentDescription = pages[position].title
        }.attach()
    }

    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this) {
            val currentFragment = supportFragmentManager.fragments
                .firstOrNull { it.isVisible }

            val webView = currentFragment?.view?.findViewById<WebView>(R.id.webView)
            when {
                webView?.canGoBack() == true -> webView.goBack()
                viewPager.currentItem > 0 -> viewPager.currentItem = viewPager.currentItem - 1
                else -> isEnabled = false
            }
        }
    }
}
