package com.example.teamplanningapp.homecontainer

import android.animation.Animator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.teamplanningapp.R
import com.example.teamplanningapp.databinding.FragmentNestedBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_nested.*
import kotlinx.android.synthetic.main.fragment_nested.view.*


class NestedFragment : Fragment() {

    private lateinit var navController: NavController
    var toolbar: Toolbar? = null
    var navigationViewTest: NavigationView? = null
    var searchBar: LinearLayout? = null
    private lateinit var dataBinding: FragmentNestedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        dataBinding = FragmentNestedBinding.inflate(inflater)
        /*animateTransition(
            NestedFragmentArgs.fromBundle(
                requireArguments()
            ).params)*/
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        toolbar = view.findViewById(R.id.toolbar);
        val fragmentContainer = view.findViewById<View>(R.id.nested_nav_graph)
        navController = Navigation.findNavController(fragmentContainer)
        // Set up ActionBar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController((activity as AppCompatActivity), navController, drawerLayout)
        // Set up navigation menu
        navigationViewTest = view.findViewById(R.id.navigationView)
        navigationViewTest?.setupWithNavController(navController)

        //This listener manages the nawdrawer configuration
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            when (nd.id) {
                nc.graph.startDestination -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    appbar.toolbar.visibility = View.VISIBLE
                }
                else -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    //TODO change visibility of action buttons
                    appbar.toolbar.visibility = View.VISIBLE
                }
            }
        }

        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        (toolbar as Toolbar).setNavigationOnClickListener { v ->
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView)
            } else {
                drawerLayout.openDrawer(navigationView, true)
            }
        }

        drawerLayout.setScrimColor(Color.TRANSPARENT)
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(
                drawerView: View,
                slideOffset: Float
            ) {

                // Scale the View based on current slide offset
                val diffScaledOffset = slideOffset * (1 - 0.5f)
                val offsetScale = 1 - diffScaledOffset
                dataBinding.rootLayout.setScaleX(offsetScale)
                dataBinding.rootLayout.setScaleY(offsetScale)

                // Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff: Float = dataBinding.rootLayout.getWidth() * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                dataBinding.rootLayout.setTranslationX(xTranslation)
            }
        }
        )
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            when (nd.id) {
                nc.graph.startDestination -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    appbar.toolbar.visibility = View.VISIBLE
                }
                else -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    appbar.toolbar.visibility = View.VISIBLE
                }
            }
        }
        super.onPrepareOptionsMenu(menu)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
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