package com.example.autofilltestapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.autofill.AutofillManager
import androidx.fragment.app.Fragment
import com.example.autofilltestapp.MainActivity
import com.example.autofilltestapp.databinding.FragmentShippingBinding

/**
 * A fragment containing a Shipping Address.
 */
class ShippingFragment : Fragment() {

    private var _binding: FragmentShippingBinding? = null
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
      _binding = FragmentShippingBinding.inflate(inflater, container, false)
      val root = binding.root

        // Initialize AutofillManager
        val afm = requireContext().getSystemService(AutofillManager::class.java)
        afm?.requestAutofill(binding.editTextShippingPersonName)
        afm?.requestAutofill(binding.editTextShippingStreetAddress)
        afm?.requestAutofill(binding.editTextShippingLocality)
        afm?.requestAutofill(binding.editTextShippingRegion)
        afm?.requestAutofill(binding.editTextShippingPostalCode)

        binding.buttonNext.setOnClickListener {
            Log.d("ShippingFragment", "AutoFillManager.isNull: ${afm == null}")
            Log.d("ShippingFragment", "AutoFillManager.isEnabled: ${afm?.isEnabled}")
            Log.d("ShippingFragment", "AutoFillManager.hasEnabledAutofillServices: ${afm?.hasEnabledAutofillServices()}")
            afm?.commit()
            (activity as? MainActivity)?.setTab(1)
        }
        return root
    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance(): ShippingFragment {
            return ShippingFragment()
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}