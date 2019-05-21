package com.example.arms.mvp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CommonViewPagerAdapter(
    fragmentManager: androidx.fragment.app.FragmentManager,
    val tabTitles: Array<String>,
    val fragments: MutableList<androidx.fragment.app.Fragment>
): androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {
	override fun getItem(position: Int): androidx.fragment.app.Fragment {
		return fragments[position]
	}

	override fun getCount(): Int {
		return fragments.size
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return tabTitles[position]
	}
}