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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import br.com.simplepass.loadingbutton.animatedDrawables.ProgressType
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import br.com.simplepass.loadingbutton.customViews.ProgressButton
import com.example.teamplanningapp.R
import com.example.teamplanningapp.databinding.FragmentLoginBinding
import com.example.teamplanningapp.viewmodel.LoginViewModel

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
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val btn = dataBinding.root.findViewById(R.id.login_button) as CircularProgressButton
        btn.run { setOnClickListener { morphDoneAndRevert(requireNotNull(activity)) } }


    }
    private fun ProgressButton.morphDoneAndRevert(
        context: Context,
        fillColor: Int = ContextCompat.getColor(context, R.color.customGreen),
        bitmap: Bitmap = defaultDoneImage(context.resources),
        doneTime: Long = 3000,
        revertTime: Long = 4000
    ) {
        progressType = ProgressType.INDETERMINATE
        startAnimation()
        //TODO REMOVE HANDLER AND CALL FIREBASE
        Handler().run {
            postDelayed({ doneLoadingAnimation(fillColor, bitmap) }, doneTime)
            //TODO call revert only with fail
            postDelayed(::revertAnimation, revertTime)
            //IF RESULT OK, GO HOME AND USE https://gist.github.com/ferdy182/d9b3525aa65b5b4c468a
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

    private fun defaultDoneImage(resources: Resources) =
        BitmapFactory.decodeResource(resources, R.mipmap.ic_check_ok)

}