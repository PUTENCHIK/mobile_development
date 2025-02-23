package com.example.tabsapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class MyPagerAdapter(fragmentActivity: FragmentActivity, private val context: Context) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: ArrayList<Fragment> = arrayListOf(
        MovieFragment(),
        BookFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun getPageTitle(position: Int): String {
        return context.resources.getString(TAB_TITLES[position])
    }
}