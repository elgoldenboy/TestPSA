package benhamida.jassem.testpsa.ui.weather_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import benhamida.jassem.testpsa.R
import benhamida.jassem.testpsa.databinding.FragmentWeatherDataBinding
import benhamida.jassem.testpsa.di.ViewModelFactory

class WeatherDataFragment : Fragment() {

    private lateinit var fragmentWeatherDataBinding: FragmentWeatherDataBinding
    private lateinit var weatherDataViewModel: WeatherDataViewModel
    val args: WeatherDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        weatherDataViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(
            WeatherDataViewModel::class.java)

        fragmentWeatherDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_data, container, false)
        return fragmentWeatherDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.currentCity = args.city
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        weatherDataViewModel.getWeatherInfoFromCache(weatherDataViewModel.currentCity.id)
        weatherDataViewModel.updateWeatherInfo(weatherDataViewModel.currentCity)
    }

    private fun setupObserver() {
        weatherDataViewModel.weatherDetails.observe(viewLifecycleOwner, {
            fragmentWeatherDataBinding.degTv.setText(it.temp?.toInt().toString())
            fragmentWeatherDataBinding.cityNameTv.setText(weatherDataViewModel.currentCity.name)
            fragmentWeatherDataBinding.pressureTv.text = getString(R.string.pressure) + it.pressure.toString()
            fragmentWeatherDataBinding.humidityTv.text = getString(R.string.humidity) + it.humidity.toString()
            fragmentWeatherDataBinding.windSpeedTv.text = getString(R.string.wind_speed) + it.wind_speed.toString()
            fragmentWeatherDataBinding.descriptionTv.text = it.description.toString()
            fragmentWeatherDataBinding.weatherImg.setBackgroundResource(getImage(it.icon))
        })
    }

    private fun getImage(icon: String?): Int {
        return when(icon) {
            "01d" -> R.drawable.img_01d
            "01n" -> R.drawable.img_01n
            "02d" -> R.drawable.img_02d
            "02n" -> R.drawable.img_02n
            "03d" -> R.drawable.img_03d
            "03n" -> R.drawable.img_03n
            "04d" -> R.drawable.img_04d
            "04n" -> R.drawable.img_04n
            "09d" -> R.drawable.img_09d
            "09n" -> R.drawable.img_09n
            "10d" -> R.drawable.img_10d
            "10n" -> R.drawable.img_10n
            "11d" -> R.drawable.img_11d
            "11n" -> R.drawable.img_11n
            "13d" -> R.drawable.img_13d
            "13n" -> R.drawable.img_13n
            "50d" -> R.drawable.img_50d
            "50n" -> R.drawable.img_50n
            else -> R.drawable.img_01d
        }
    }
}