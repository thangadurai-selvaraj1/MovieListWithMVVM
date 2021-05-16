package com.thangadurai.saveotask.ui.activity.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponse
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponseItem
import com.thangadurai.saveotask.data.network.Resource
import com.thangadurai.saveotask.data.network.Status
import com.thangadurai.saveotask.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    var movieName = ObservableField("")
    var movieDes = ObservableField("")
    var movieCategory1 = ObservableField("")
    var movieCategory2 = ObservableField("")
    var movieCategory3 = ObservableField("")
    var movieRating = ObservableField("")
    var movieRatingValue = ObservableField(0f)
    var movieReview = ObservableField("")
    var movieSynopsis = ObservableField("")

    fun settingValue(model: BasePhotoResponseItem) {
        movieName.set(model.title)
        movieDes.set("R|3h 7min |30 Des,2015")
        movieCategory1.set("CRIME")
        movieCategory2.set("MYSTERY")
        movieCategory3.set("DRAMA")
        movieRating.set("4.5")
        movieRatingValue.set(4.5f)
        movieReview.set("Review: 10(Critics)|2345(User)")
        movieSynopsis.set("The film began as a concept in 1997 and was scheduled for distribution by Artisan Entertainment. However, a lawsuit disrupted the project and was not settled until September 2003. In 2005, Marvel Studios received a loan from Merrill Lynch, and planned to finance and release the film through Paramount Pictures. Directors Jon Favreau and Louis Leterrier were interested in directing the project before Johnston was approached in 2008. The principal characters were cast between March and June 2010. Production began in June, and filming took place in London, Manchester, Caerwent, Liverpool, and Los Angeles. Several different techniques were used by the visual effects company Lola to create the physical appearance of the character before he becomes Captain America.")
    }

}