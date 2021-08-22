package com.example.kkn_unmer.utils

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.GenericTransitionOptions
import com.example.kkn_unmer.R
import com.example.kkn_unmer.utils.glide.GlideApp
import de.hdodenhof.circleimageview.CircleImageView

//@BindingAdapter("setImageUrl")
//fun AppCompatImageView.setImageUrl(imageUrl: String) {
//    load(imageUrl) {
//        crossfade(true)
//        placeholder(R.drawable.placeholder_image)
//        error(R.drawable.placeholder_image)
//    }
//}

//@BindingAdapter("setRefresh")
//fun SwipeRefreshLayout.setRefresh(isRefresh: Boolean) {
//    this.isRefreshing = isRefresh
//}

@BindingAdapter("setImageUrl")
fun AppCompatImageView.setImageUrl(imageUrl: String?) {
    GlideApp.with(context)
        .load(imageUrl)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}

@BindingAdapter("setCircleImageUrl")
fun CircleImageView.setCircleImageUrl(imageUrl: String?) {
    GlideApp.with(context)
        .load(imageUrl)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageDrawable: Int) {
    GlideApp.with(context)
        .load(imageDrawable)
        .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
        .into(this)
}

@BindingAdapter("binding")
fun bindAppCompatEditText(appCompatEditText: AppCompatEditText, string: MutableLiveData<String>){
    val pair: Pair<MutableLiveData<String>, TextWatcherAdapter>? = appCompatEditText.getTag(R.id.bound_observable) as Pair<MutableLiveData<String>, TextWatcherAdapter>?
    if (pair==null || pair.first != string) {
        if (pair != null) {
            appCompatEditText.removeTextChangedListener(pair.second)
        }
        val watcher = object : TextWatcherAdapter(){
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                super.onTextChanged(p0, p1, p2, p3)
                string.value = p0?.toString()
            }
        }
        appCompatEditText.setTag(R.id.bound_observable, Pair(string, watcher))
        appCompatEditText.addTextChangedListener(watcher)
    }
    val newValue = string.value
    if (appCompatEditText.text.toString() != newValue){
        appCompatEditText.setText(newValue)
    }
}

@BindingAdapter("bindingTextView")
fun bindAppCompatTextView(appCompatTextView: AppCompatTextView, string: ObservableField<String>){
    val pair: Pair<ObservableField<String>, TextWatcherAdapter>? = appCompatTextView.getTag(R.id.bound_observable) as Pair<ObservableField<String>, TextWatcherAdapter>?
    if (pair==null || pair.first != string) {
        if (pair != null) {
            appCompatTextView.removeTextChangedListener(pair.second)
        }
        val watcher = object : TextWatcherAdapter(){
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                super.onTextChanged(p0, p1, p2, p3)
                string.set(p0?.toString())
            }
        }
        appCompatTextView.setTag(R.id.bound_observable, Pair(string, watcher))
        appCompatTextView.addTextChangedListener(watcher)
    }
    val newValue = string.get()
    if (appCompatTextView.text.toString() != newValue){
        appCompatTextView.text = newValue
    }
}