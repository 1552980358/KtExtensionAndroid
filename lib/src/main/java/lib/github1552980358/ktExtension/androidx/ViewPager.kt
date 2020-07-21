package lib.github1552980358.ktExtension.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

/**
 * Set up [FragmentPagerAdapter] to [ViewPager] without inheriting [FragmentPagerAdapter] again
 * when only want to show fragments without other action
 * @param behavior
 *   Options: [BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT] or [BEHAVIOR_SET_USER_VISIBLE_HINT]
 *   Refers to official document [FragmentPagerAdapter]
 **/
fun ViewPager.setUpSimpleFragmentPagerAdapter(
    fragmentManager: FragmentManager, 
    arrayList: ArrayList<Fragment>, 
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    adapter = SimpleFragmentPagerAdapter(fragmentManager, arrayList, behavior)
}

fun ViewPager.setUpSimpleFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    array: Array<Fragment>,
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    adapter = SimpleFragmentPagerAdapter(fragmentManager, array, behavior)
}

fun ViewPager.setUpSimpleFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    mutableList: MutableList<Fragment>,
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    adapter = SimpleFragmentPagerAdapter(fragmentManager, mutableList, behavior)
}