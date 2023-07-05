package uz.gita.translateuzbeng.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import uz.gita.translateuzbeng.R


fun FragmentActivity.popBackStack() {
    if (supportFragmentManager.backStackEntryCount == 0) finish()
    else supportFragmentManager.popBackStack()
}

fun FragmentActivity.addFragment(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .add(R.id.container, fm)
        .commit()
}

fun FragmentActivity.replaceFragmentSaveStack(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .addToBackStack(fm::class.java.name)
        .commit()
}

fun FragmentActivity.replaceFragment(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .commit()
}

fun Fragment.popBackStack() {
    requireActivity().popBackStack()
}
fun Fragment.addFragment(fm : Fragment) {
    requireActivity().addFragment(fm)
}

fun Fragment.replaceFragmentSaveStack(fm : Fragment) {
    requireActivity().replaceFragmentSaveStack(fm)
}

fun Fragment.replaceFragment(fm : Fragment) {
    requireActivity().replaceFragment(fm)
}