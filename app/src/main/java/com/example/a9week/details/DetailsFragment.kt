package com.example.a9week.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.a9week.R
import com.example.a9week.TempSettingsDisplayManager
import com.example.a9week.formatTempDisplay

class DetailsFragment : Fragment() {


    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var tempSettingsDisplayManage: TempSettingsDisplayManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_details, container, false)

        tempSettingsDisplayManage = TempSettingsDisplayManager(requireContext())

        val temp_tv = layout.findViewById<TextView>(R.id.tv_temp)
        val description_tv = layout.findViewById<TextView>(R.id.tv_description)


        temp_tv.text =
            formatTempDisplay(args.temp, tempSettingsDisplayManage.getTempDisplaySetting())
        description_tv.text = args.description


        return layout
    }






}
