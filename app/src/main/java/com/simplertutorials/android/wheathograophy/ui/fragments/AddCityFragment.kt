package com.simplertutorials.android.wheathograophy.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import kotlinx.android.synthetic.main.city_add_fragment.*
import javax.inject.Inject

class AddCityFragment: Fragment() {

//    @Inject
//    lateinit var databaseRepository: DatabaseRepository
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _presenter: AddCityFragmentPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        _presenter = AddCityFragmentPresenter(databaseRepository, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.city_add_fragment, container, false)
        updateUi(view)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun updateUi(view: View?) {
        addcity_btn.setOnClickListener(View.OnClickListener {
            cityadd_text.text
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (context?.applicationContext as MainApplication).component.inject(this)
    }

    fun showSnackBar() {
        Snackbar.make(this.view!!, "City Added", Snackbar.LENGTH_SHORT).show()
    }
}