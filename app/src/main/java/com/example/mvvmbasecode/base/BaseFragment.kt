package com.example.mvvmbasecode.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.mvvmbasecode.MainActivity
import com.example.mvvmbasecode.`interface`.SendAnyTwoParameterInterface
import com.example.mvvmbasecode.model.PermissionViewModel
import com.example.mvvmbasecode.network.SessionManager
import com.example.mvvmbasecode.utils.CommonUtils
import com.example.mvvmbasecode.utils.ViewInflate
import com.example.mvvmbasecode.utils.goToSettingScreenPopUp
import com.example.mvvmbasecode.utils.hideKeyboard
import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class BaseFragment<B : ViewBinding>(private val bindingReference: ViewInflate<B>) : Fragment() {

    lateinit var binding: B

    private var progressDialog: Dialog? = null
    private lateinit var mStartActivityResult: ActivityResultLauncher<Intent>

    private lateinit var requestMultiplePermissions: ActivityResultLauncher<Array<String>>
    private var showSettingView = true
    private var goToSetting = false
    private lateinit var permissionList: Array<String>

    private val permissionVM: PermissionViewModel by viewModels()
    val session: SessionManager by lazy {
        SessionManager(requireContext())
    }

    fun commonRuntimePermissions(permissionList: Array<String>, showSettingView: Boolean) {
        this.permissionList = permissionList
        this.showSettingView = showSettingView

        if (checkPermission()) allowThePermission()
        else askForPermission()
    }

    fun launchIntent(intent: Intent) {
        mStartActivityResult.launch(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mStartActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    permissionVM.mutableLiveData(result)
                }
            }

        requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    if (!it.value) {
                        if (shouldShowRequestPermissionRationale(it.key)) askForPermission()
                        else if (showSettingView) {
                            goToSetting()
                        }

                        return@registerForActivityResult
                    }
                    allowThePermission()
                }
            }
    }

    private fun askForPermission() {
        requestMultiplePermissions.launch(permissionList)
    }

    private fun goToSetting() {
        (requireActivity() as MainActivity).goToSettingScreenPopUp(object :
            SendAnyTwoParameterInterface {
            override fun sendAction(id: Any, isAccept: Any) {
                if (isAccept as Boolean) goToSetting = true
                else {
                    goToSetting = false
                    goToSetting()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (showSettingView) {
            if (goToSetting) {
                if (!checkPermission()) goToSetting()
                else allowThePermission()
            }
        }
    }

    private fun allowThePermission() {
        goToSetting = false
        permissionVM.allowPermission(true)
    }

    private fun checkPermission(): Boolean {
        var flag = false
        for (permission in permissionList) {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(), permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                flag = false
                break
            } else {
                flag = true
            }
        }
        return flag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = bindingReference.invoke(layoutInflater, container, false)
            setup()
            binding.root
        }

//        binding = bindingInflater.invoke(inflater, container, false)
//
//        try {
//            hideKeyboard(requireActivity())
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return binding.root
    }

    private fun setup() {
        hideKeyboard(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = CommonUtils.showLoadingDialog(requireActivity())
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )

    }

    fun showProgress() {
        hideProgress()
        if (progressDialog != null && !progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    fun hideProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    fun isValidEmail(email: String): Boolean {
        val ragEx = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        return email.matches(ragEx)
    }

    fun isPartem(password: String): Boolean {
        val pattern: Pattern

        val passwordPattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})"

        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    override fun onDestroyView() {
        hideProgress()
        onBackPressedCallback.remove()
        super.onDestroyView()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    open fun onBackPressed() {
        findNavController().navigateUp()
    }
}