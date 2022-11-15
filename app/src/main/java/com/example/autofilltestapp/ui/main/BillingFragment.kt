package com.example.autofilltestapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.autofill.AutofillManager
import androidx.fragment.app.Fragment
import com.example.autofilltestapp.MainActivity
import com.example.autofilltestapp.databinding.FragmentBillingBinding
import com.example.autofilltestapp.databinding.FragmentShippingBinding

/**
 * A fragment containing a Billing Address.
 */
class BillingFragment : Fragment() {

    private var _binding: FragmentBillingBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
      _binding = FragmentBillingBinding.inflate(inflater, container, false)
      val root = binding.root

        // Initialize AutofillManager
        val afm = requireContext().getSystemService(AutofillManager::class.java)
        afm?.requestAutofill(binding.editTextBillingPersonName)
        afm?.requestAutofill(binding.editTextBillingStreetAddress)
        afm?.requestAutofill(binding.editTextBillingLocality)
        afm?.requestAutofill(binding.editTextBillingRegion)
        afm?.requestAutofill(binding.editTextBillingPostalCode)

        binding.buttonNext.setOnClickListener {
            Log.d("BillingFragment", "AutoFillManager.isNull: ${afm == null}")
            Log.d("BillingFragment", "AutoFillManager.isEnabled: ${afm?.isEnabled}")
            Log.d("BillingFragment", "AutoFillManager.hasEnabledAutofillServices: ${afm?.hasEnabledAutofillServices()}")
            afm?.commit()
            (activity as? MainActivity)?.setTab(0)
        }
        return root
    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance(): BillingFragment {
            return BillingFragment()
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}