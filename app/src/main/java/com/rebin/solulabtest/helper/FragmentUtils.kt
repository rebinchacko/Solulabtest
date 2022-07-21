
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rebin.solulabtest.R

object FragmentUtils {

    private val TAG = FragmentUtils::class.java!!.getCanonicalName()


    /**
     * The method for adding a new fragment
     * @param activity : Parent Activity
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     */
    fun addFragment(activity: AppCompatActivity, frag: Fragment, id: Int, addtoBackStack: Boolean) {
        val fragmentManager = activity.getSupportFragmentManager()
        val transaction = fragmentManager.beginTransaction()
//        transaction.setCustomAnimations(
//            R.anim.anim_enter,
//            R.anim.anim_exit,
//            R.anim.anim_pop_enter,
//            R.anim.anim_pop_exit
//        )

        if (addtoBackStack)
            transaction.addToBackStack(frag.javaClass.getCanonicalName())
        transaction.add(id, frag, frag.javaClass.getCanonicalName())
        transaction.commit()
    }

    /**
     * The method for replacing a fragment
     * @param activity : Parent Activity
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     * @param animationType: Fragment transition animation type
     */
    fun replaceFragment(
        activity: AppCompatActivity,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {
        val fragmentManager = activity.getSupportFragmentManager()
        val transaction = fragmentManager.beginTransaction()

        /* when (animationType) {
             FragmentAnimation.TRANSITION_POP -> transaction.setCustomAnimations(
                 R.anim.anim_enter,
                 R.anim.anim_exit,
                 R.anim.anim_pop_enter,
                 R.anim.anim_pop_exit
             )
             FragmentAnimation.TRANSITION_FADE_IN_OUT -> transaction.setCustomAnimations(
                 R.anim.anim_frag_fade_in,
                 R.anim.anim_frag_fade_out
             )
             FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT -> transaction.setCustomAnimations(
                 R.anim.slide_in_from_rigth,
                 R.anim.slide_out_to_left,
                 R.anim.slide_in_from_left,
                 R.anim.slide_out_to_right
             )
             FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT -> transaction.setCustomAnimations(
                 R.anim.slide_in_from_rigth,
                 0
             )
         }*/

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.getCanonicalName())

        transaction.replace(id, fragment, fragment.javaClass.getCanonicalName())
        transaction.commit()
    }


    /**
     * The method for replacing a fragment allowing state loss
     * @param activity : Parent Activity
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     * @param animationType: Fragment transition animation type
     */
    fun replaceFragmentAllowingStateLoss(
        activity: AppCompatActivity,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean,
        animationType: FragmentAnimation
    ) {
        val fragManager = activity.getSupportFragmentManager()
        val transaction = fragManager.beginTransaction()
        when (animationType) {
            FragmentAnimation.TRANSITION_POP -> transaction.setCustomAnimations(
                R.anim.anim_enter,
                R.anim.anim_exit,
                R.anim.anim_pop_enter,
                R.anim.anim_pop_exit
            )
            FragmentAnimation.TRANSITION_FADE_IN_OUT -> transaction.setCustomAnimations(
                R.anim.anim_frag_fade_in,
                R.anim.anim_frag_fade_out
            )
            FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT -> transaction.setCustomAnimations(
                R.anim.slide_in_from_rigth,
                R.anim.slide_out_to_left,
                R.anim.slide_in_from_left,
                R.anim.slide_out_to_right
            )
            FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT -> transaction.setCustomAnimations(
                R.anim.slide_in_from_rigth,
                0
            )
        }
        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.getCanonicalName())

        transaction.replace(id, fragment, fragment.javaClass.getCanonicalName())
        transaction.commitAllowingStateLoss()
    }


    /**
     * The method for replacing a fragment without any transition animatiom
     * @param activity : Parent Activity
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     */
    fun replaceFragmentWithoutAnimation(
        activity: AppCompatActivity,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {
        val fragmentManager = activity.getSupportFragmentManager()
        val fragTransaction = fragmentManager.beginTransaction()
        if (addToBackStack)
            fragTransaction.addToBackStack(fragment.javaClass.getCanonicalName())

        fragTransaction.replace(id, fragment, fragment.javaClass.getCanonicalName())
        fragTransaction.commit()
    }

    /**
     * The method for replacing a child fragment without any transition animatiom
     * @param currentFragment : Current Fragment
     * @param frag: Fragment to be added
     * @param id: Fragment container ID
     * @param addtoBackStack: Flag indicating whether to add to backstack or not
     */
    fun replaceChildFragment(
        currentFragment: Fragment,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {
        val fragmentManager = currentFragment.getChildFragmentManager()
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.anim_enter,
            R.anim.anim_exit,
            R.anim.pop_enter,
            R.anim.pop_exit
        )

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.getCanonicalName())

        transaction.replace(id, fragment, fragment.javaClass.getCanonicalName())

        transaction.commit()
    }

    /**
     * This method pops the back the stack till the first fragment
     * @param activity: Parent Activity
     */
    fun popToFirstFragment(activity: AppCompatActivity) {
        try {
            activity.getSupportFragmentManager().popBackStackImmediate(
                activity.getSupportFragmentManager().getBackStackEntryAt(0).getId(),
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        } catch (e: IndexOutOfBoundsException) {
            //Timber.log(Log.ASSERT, e.message)
        } catch (e: NullPointerException) {
            // Timber.log(Log.ASSERT, e.message)
        }

    }

    /**
     * This method checks whether the specified fragment is the top fragment or not
     * @param activity: Parent Activity
     * @param fragmentTag : Fragment Tag Name
     */
    fun isCurrentTopFragment(activity: AppCompatActivity, fragmentTag: String): Boolean {
        val fragmentManager = activity.getSupportFragmentManager()
        val tag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1)
            .getName()
        return if (fragmentTag.equals(tag, ignoreCase = true)) true else false
    }

    /**
     * This method pops the backstack till the specified fragment
     * @param activity : Parent activity
     * @param tagname: Fragment Tag Name
     */
    fun popToProvidedFragment(activity: AppCompatActivity, tagname: String) {
        try {
            activity.getSupportFragmentManager().popBackStackImmediate(tagname, 0)
        } catch (e: IndexOutOfBoundsException) {
            //Timber.log(Log.ASSERT, e.message)
        } catch (e: NullPointerException) {
            // Timber.log(Log.ASSERT, e.message)
        }

    }

    /**
     * This method clears the whole backstack including the current fragment
     * @param activity: Parent activity
     */
    fun clearBackStackInclusive(activity: AppCompatActivity) {
        if (activity.getSupportFragmentManager().getBackStackEntryCount() == 0)
            return
        val entry = activity.getSupportFragmentManager().getBackStackEntryAt(
            0
        )
        activity.getSupportFragmentManager().popBackStack(
            entry.getId(),
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        activity.getSupportFragmentManager().executePendingTransactions()

    }

    /**
     * This method clears the backstack
     */
    fun clearBackStack(activity: AppCompatActivity) {
        while (activity.getSupportFragmentManager().getBackStackEntryCount() != 0) {
            activity.getSupportFragmentManager().popBackStackImmediate()
        }
    }

    /**
     * This method pops the immediate fragment
     */
    fun popBackStack(activity: AppCompatActivity) {
        activity.getSupportFragmentManager().popBackStackImmediate()
    }


}