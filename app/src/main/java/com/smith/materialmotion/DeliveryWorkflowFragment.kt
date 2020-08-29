package com.smith.materialmotion

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.constraintlayout.motion.widget.MotionLayout
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.renderer.glsurfaceview.MapboxGLSurfaceView
import kotlinx.android.synthetic.main.fragment_delivery_workflow.*
import kotlinx.android.synthetic.main.fragment_delivery_workflow.view.*

class DeliveryWorkflowFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delivery_workflow, container, false)
        view.mapView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                onInflateMap(view, savedInstanceState)
                view.mapView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        return view
    }

    private fun onInflateMap(view: View, savedInstanceState: Bundle?) {
        val lat = 12.931121
        val long = 77.628402
        view.mapView.onCreate(savedInstanceState)
        rootView.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
//                view.mapView.onStart()
            }

        })
        view.mapView.getMapAsync { mapboxMap ->
            mapboxMap.setStyle(Style.LIGHT) {

            }

        }
    }
}