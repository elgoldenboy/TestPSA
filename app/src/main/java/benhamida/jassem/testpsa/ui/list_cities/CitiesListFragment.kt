package benhamida.jassem.testpsa.ui.list_cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.testpsa.R
import benhamida.jassem.testpsa.databinding.FragmentListCitiesBinding
import benhamida.jassem.testpsa.di.ViewModelFactory

class CitiesListFragment : Fragment(), CitiesListAdapter.OnClickListener {

    private lateinit var fragmentListCitiesBinding: FragmentListCitiesBinding
    private lateinit var viewModel: CitiesListViewModel
    private lateinit var adapter: CitiesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(CitiesListViewModel::class.java)
        fragmentListCitiesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_cities, container, false)
        return fragmentListCitiesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCities()
    }

    private fun goToAddCityScreen() {
        val action = CitiesListFragmentDirections.actionGoToAddCity()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToWeatherDetailsScreen(city: City) {
        val action = CitiesListFragmentDirections.actionGoToWeatherData(city)
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun setupUI() {
        fragmentListCitiesBinding.citiesListRv.layoutManager = LinearLayoutManager(requireContext())
        adapter = CitiesListAdapter(this)
        fragmentListCitiesBinding.citiesListRv.adapter = adapter
        fragmentListCitiesBinding.addCityFab.setOnClickListener { goToAddCityScreen() }
    }

    private fun setupObserver() {
        viewModel.citiesList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setData(it)
            }
        })
    }

    override fun onItemClickListener(city: City) {
        city.id.let {
            if(city.name.isNotEmpty()) {
                goToWeatherDetailsScreen(city)
            }
        }
    }
}