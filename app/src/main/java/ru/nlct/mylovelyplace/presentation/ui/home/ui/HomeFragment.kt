package ru.nlct.mylovelyplace.presentation.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import ru.nlct.mylovelyplace.BaseFragment
import ru.nlct.mylovelyplace.DatabaseConst.ElbrusLocation
import ru.nlct.mylovelyplace.DatabaseConst.ZOOM
import ru.nlct.mylovelyplace.DatabaseConst.ZOOM_TO
import ru.nlct.mylovelyplace.R
import ru.nlct.mylovelyplace.domain.models.Place
import ru.nlct.mylovelyplace.databinding.FragmentHomeBinding
import ru.nlct.mylovelyplace.presentation.ui.home.adapters.SliderAdapter
import ru.nlct.mylovelyplace.presentation.ui.home.cardslider.CardSliderLayoutManager
import ru.nlct.mylovelyplace.presentation.ui.home.cardslider.CardSnapHelper

/**
 * Home fragment - фрагмент для отображения главной страницы
 *
 * @author Marianna Sabanchieva
 */

class HomeFragment : BaseFragment() {

    private val placeViewModel: PlaceViewModel by viewModels { factory }
    private var _binding: FragmentHomeBinding? = null
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var map: MapView
    private lateinit var mapController: IMapController
    private lateinit var layoutManger: CardSliderLayoutManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        sliderAdapter = SliderAdapter(mutableListOf()) { id ->
            onCardClick(id)
        }
        initRecyclerView()
        map = binding.map

        initMap()
        initObservers()
        placeViewModel.init()

        showMap()
    }

    private fun onCardClick(id: Long) {
        //TODO: добавить переход на страницу с детальной информацией
    }

    private fun initObservers() {
        placeViewModel.allPlaces.observe(viewLifecycleOwner, ::setPlaces)
    }

    private fun setPlaces(places: List<Place>) {
        sliderAdapter.setPlaces(places)
    }

    private fun initMap() {
        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        val mapController = map.controller
        mapController.setZoom(ZOOM)
        val startPoint = ElbrusLocation
        mapController.setCenter(startPoint)
        map.invalidate()
    }

    private fun showMap() {
        context?.let {
            binding.map.setTileSource(TileSourceFactory.MAPNIK)
            binding.map.setMultiTouchControls(true)
            binding.map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            binding.map.addOnFirstLayoutListener { v, left, top, right, bottom ->
                val locOverlay = MyLocationNewOverlay(GpsMyLocationProvider(it), binding.map)
                locOverlay.enableMyLocation()
                binding.map.overlays.add(locOverlay)
                val compassOverlay =
                    CompassOverlay(it, InternalCompassOrientationProvider(it), binding.map)
                compassOverlay.enableCompass()
                binding.map.overlays.add(compassOverlay)
                binding.map.controller.zoomTo(ZOOM_TO)
                placeViewModel.allPlaces.observe(viewLifecycleOwner, ::onActiveCardChange)
            }
            binding.map.invalidate()
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.adapter = sliderAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    placeViewModel.allPlaces.observe(viewLifecycleOwner, ::onActiveCardChange)
                }
            }
        })
        layoutManger = recyclerView.layoutManager as CardSliderLayoutManager
        CardSnapHelper().attachToRecyclerView(recyclerView)
    }

    private fun onActiveCardChange(places: List<Place>) {
        val index = layoutManger.getActiveCardPosition()
        with(binding) {
            with(places[index]) {
                map.controller.animateTo(GeoPoint(latitude, longitude))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

}