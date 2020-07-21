package lib.github1552980358.ktExtension.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter: FragmentPagerAdapter {
    
    private var fragments: MutableList<Fragment>
    
    /**
     * @param behavior
     *   Options: [BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT] or [BEHAVIOR_SET_USER_VISIBLE_HINT]
     *   Refers to official document [FragmentPagerAdapter]
     **/
    constructor(fragmentManager: FragmentManager, fragments: Array<Fragment>, behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT):
        this(fragmentManager, fragments.toMutableList(), behavior)
    
    constructor(fragmentManager: FragmentManager, fragments: ArrayList<Fragment>, behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT):
        this(fragmentManager, fragments.toMutableList(), behavior)
    
    constructor(fragmentManager: FragmentManager, fragments: MutableList<Fragment>, behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT):
        super(fragmentManager, behavior) {
        this.fragments = fragments
    }
    
    override fun getCount() = fragments.size
    
    override fun getItem(position: Int) = fragments[position]
    
}