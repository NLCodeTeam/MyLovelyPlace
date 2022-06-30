package ru.nlct.mylovelyplace

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Base fragment - фрагмент для инициализации даггера
 *
 * @author Marianna Sabanchieva
 */

open class BaseFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as PlaceApplication).dagger
            .inject(this)
    }
}