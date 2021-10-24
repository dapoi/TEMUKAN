package com.dafdev.selamatkan.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dafdev.selamatkan.data.source.response.HospitalsNonCovidItem
import com.dafdev.selamatkan.databinding.ItemListNonCovidHospitalBinding
import com.dafdev.selamatkan.utils.Constant
import com.dafdev.selamatkan.view.activity.main.HospitalDetailActivity

class HospitalNonCovidAdapter(private val context: Context) :
    RecyclerView.Adapter<HospitalNonCovidAdapter.NonCovidViewHolder>() {

    private val listHospital = ArrayList<HospitalsNonCovidItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setNonCovidHospital(data: List<HospitalsNonCovidItem>) {
        listHospital.clear()
        listHospital.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HospitalNonCovidAdapter.NonCovidViewHolder {
        return NonCovidViewHolder(
            ItemListNonCovidHospitalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: HospitalNonCovidAdapter.NonCovidViewHolder,
        position: Int
    ) {
        holder.bind(listHospital[position])
    }

    override fun getItemCount(): Int = listHospital.size

    inner class NonCovidViewHolder(private val binding: ItemListNonCovidHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HospitalsNonCovidItem) {
            with(binding) {
                data.apply {
                    tvHospitalName.text = name
                    tvHospitalAddress.text = address
                    if (phone == null) {
                        tvHospitalPhone.text = "-"
                    } else {
                        tvHospitalPhone.text = phone
                    }
                    tvInfo.text = available_beds?.get(0)?.info
                    cvHospital.setOnClickListener {
                        Constant.hospitalId = id!!
                        Constant.hospitalName = name!!
                        if (phone != null) {
                            Constant.phoneNumber = phone
                        }
                        Intent(context, HospitalDetailActivity::class.java).also {
                            context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}