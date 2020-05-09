package com.example.teamplanningapp.home

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamplanningapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomeBinding.inflate(inflater)
        animateTransition(
            HomeFragmentArgs.fromBundle(
                requireArguments()
            ).params)
        return dataBinding.root
    }

    /**
     * Reveal animation for the view
     */
    private fun animateTransition(params: Bundle?) {
        dataBinding.lifecycleOwner = this
        dataBinding.rootLayout.addOnLayoutChangeListener { p0, p1, p2, p3, p4, p5, p6, p7, p8 ->
            // Check if the runtime version is at least Lollipop
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // get the center for the clipping circle
                val cx = dataBinding.rootLayout.width / 2
                val cy = dataBinding.rootLayout.height / 2
                val xPosition = params?.getFloat("x")?.toInt() ?: 0
                val yPosition = params?.getFloat("y")?.toInt() ?: 0

                // get the final radius for the clipping circle
                val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

                // create the animator for this view (the start radius is zero)
                val anim = ViewAnimationUtils.createCircularReveal(
                    dataBinding.rootLayout,
                    xPosition,
                    yPosition,
                    0f,
                    finalRadius
                )
                // make the view visible and start the animation
                dataBinding.rootLayout.visibility = View.VISIBLE
                anim.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationStart(p0: Animator?) {
                        //TODO START ANIMATION FOR THE VIEW CHILDREN
                    }

                })
                anim.start()
            }
        }
    }
}