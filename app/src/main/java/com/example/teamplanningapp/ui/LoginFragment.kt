package com.example.teamplanningapp.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import br.com.simplepass.loadingbutton.animatedDrawables.ProgressType
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import br.com.simplepass.loadingbutton.customViews.ProgressButton
import com.example.teamplanningapp.R
import com.example.teamplanningapp.constant.*
import com.example.teamplanningapp.databinding.FragmentLoginBinding
import com.example.teamplanningapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        private val TAG = LoginFragment::class.java.name
    }

    private val loginViewModel: LoginViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, LoginViewModel.Factory(app = activity.application)).get(LoginViewModel::class.java)
    }

    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.custommove)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        dataBinding = FragmentLoginBinding.inflate(inflater)
        dataBinding.loginViewModel = loginViewModel
        dataBinding.lifecycleOwner = this

        loginViewModel.loginAuthenticationState.observe(this.viewLifecycleOwner, Observer {
            login_button.run { morphDoneAndRevert(requireNotNull(activity), it) }
        })

        return dataBinding.root
    }

    //TODO review animation, it should be declared as bindingadapter and manage differetns states
    //TODO review the handler as soons as we implement firebase authentication
    private fun ProgressButton.morphDoneAndRevert(
        context: Context,
        state: LoginAuthenticationStates?,
        fillColor: Int = ContextCompat.getColor(context, R.color.customGreen),
        bitmap: Bitmap = defaultDoneImage(context.resources),
        doneTime: Long = 3000,
        revertTime: Long = 4000
    ) {
        progressType = ProgressType.INDETERMINATE

        when (state) {
            is Authenticating -> {
                startAnimation()
            }
            is Authenticated -> {
                Handler().run {
                    doneLoadingAnimation(fillColor, bitmap)
                    postDelayed({
                        val extras = FragmentNavigatorExtras(
                        login_button to "login_button")
                        findNavController()
                            .navigate(R.id.homeFragment, null, null, extras)}, doneTime/2)
                }
            }
            is Unauthenticated -> {
                Handler().run {
                    postDelayed(::revertAnimation, revertTime)
                }
            }
            is InvalidAuthentication -> {
                val fillColorError = ContextCompat.getColor(context, R.color.customRed)
                val bitmapError: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_check_ko)
                Handler().run {
                    postDelayed({ doneLoadingAnimation(fillColorError, bitmapError) }, doneTime)
                    postDelayed(::revertAnimation, revertTime)
                }
            }
        }

    }

    private fun defaultDoneImage(resources: Resources) =
        BitmapFactory.decodeResource(resources, R.mipmap.ic_check_ok)

}