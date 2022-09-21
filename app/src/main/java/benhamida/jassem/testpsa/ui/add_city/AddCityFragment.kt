package benhamida.jassem.testpsa.ui.add_city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import benhamida.jassem.testpsa.R
import benhamida.jassem.testpsa.databinding.FragmentAddCityBinding
import benhamida.jassem.testpsa.di.ViewModelFactory
import java.text.NumberFormat
import java.util.*

class AddCityFragment : Fragment() {

    private lateinit var fragmentAddCityBinding: FragmentAddCityBinding
    private lateinit var viewModel: AddCityViewModel
    var nf: NumberFormat = NumberFormat.getInstance(Locale.FRENCH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(
            AddCityViewModel::class.java)
        fragmentAddCityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_city, container, false)
        return fragmentAddCityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        fragmentAddCityBinding.addCityBtn.setOnClickListener {
            var name = fragmentAddCityBinding.nameEt.text.toString()
            var lon = fragmentAddCityBinding.lonEt.text.toString()
            var lat = fragmentAddCityBinding.latEt.text.toString()
            if(name.isNotEmpty() && lon.isNotEmpty() && lat.isNotEmpty())
                viewModel.addCity(name, lat.toDouble(), lon.toDouble())
            else
                Toast.makeText(requireContext(), R.string.error_add_city, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupObserver() {
        viewModel.operationEnd.observe(viewLifecycleOwner, {
            if(it) {
                NavHostFragment.findNavController(this).popBackStack()
            }
        })
    }
}