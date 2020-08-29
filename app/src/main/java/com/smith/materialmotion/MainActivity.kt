package com.smith.materialmotion

import android.animation.Animator
import android.graphics.Outline
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.max


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(this, R.id.nav_host_fragment)
        rootView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        val behavior = BottomSheetBehavior.from(bottomSheet)
        setupSearch()
        setupInsets()
        setupFab(behavior)
        setupBottomSheet(behavior)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setupSearch() {
        /* search.setOnClickListener {
             if (navController.currentDestination?.id != R.id.searchFragment){
                 navController.navigate(R.id.action_deliveryWorkflowFragment_to_searchFragment)
             }
         }*/
    }

    private fun setupInsets() {
        val fabBottomMargin = fab.marginBottom
//        val searchTopMargin = search.marginTop
        fab.setOnApplyWindowInsetsListener { _, insets ->
            fab.updateLayoutParams<CoordinatorLayout.LayoutParams> {
                bottomMargin = fabBottomMargin + insets.systemWindowInsetBottom
            }
            /*search.updateLayoutParams<CoordinatorLayout.LayoutParams> {
                topMargin = searchTopMargin + insets.systemWindowInsetTop
            }*/
            insets
        }
    }

    private fun setupFab(behavior: BottomSheetBehavior<FrameLayout>) {
        fab.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                fab.addOnShowAnimationListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        behavior.peekHeight = (rootView.height - fab.y).toInt()
                        handleSheetClipping(1f)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
                fab.show()
                fab.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        fab.setOnClickListener {
            if (behavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                bottomSheet.visibility = View.VISIBLE
                fab.visibility = View.GONE
            } else {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun setupBottomSheet(behavior: BottomSheetBehavior<FrameLayout>) {
        bottomSheet.clipToOutline = true
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                handleForegroundAlpha(1 - slideOffset)
                handleSheetClipping(1 - slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheet.visibility = View.GONE
                    fab.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun handleForegroundAlpha(slideOffset: Float) {
        bottomSheet.foreground?.alpha = (255 * slideOffset).toInt()
    }

    private fun handleSheetClipping(slideOffset: Float) {

        bottomSheet.outlineProvider = object : ViewOutlineProvider() {
            val bottomSheetWidth = bottomSheet.width
            val bottomSheetHeight = bottomSheet.height
            override fun getOutline(view: View?, outline: Outline?) {
                val width = bottomSheetWidth * (1 - slideOffset) + fab.width * slideOffset
                val height = bottomSheetHeight * (1 - slideOffset) + fab.height * slideOffset
                val radius = max(height, width) * (slideOffset)
                val rect = Rect(0, 0, width.toInt(), height.toInt())
                rect.offset((fab.x * slideOffset).toInt(), 0)
                outline?.setRoundRect(rect, radius)
            }
        }
        bottomSheet.invalidateOutline()
        bottomSheet.invalidate()
    }
}