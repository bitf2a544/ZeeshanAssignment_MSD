package com.example.zeeshanassignmentmsd.viewmodel

import androidx.lifecycle.*
import com.example.zeeshanassignmentmsd.data.model.DeckOfCards
import com.example.zeeshanassignmentmsd.repository.MainRepository
import com.example.zeeshanassignmentmsd.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _deckOfCards = MutableLiveData<Resource<DeckOfCards>>()
    val deckOfCards: LiveData<Resource<DeckOfCards>> get() = _deckOfCards

    fun fetchLatestDeckCardsListing() {
        _deckOfCards.postValue(Resource.loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            viewModelScope.launch {
                try {
                    if (mainRepository.isNetworkAvailable()) {
                        mainRepository.getLatestDecOfCardsFromNetwork(20)
                            .let {
                                if (it.isSuccessful) {
                                    _deckOfCards.postValue(Resource.success(it.body()))
                                } else {
                                    _deckOfCards.postValue(
                                        Resource.error(
                                            it.errorBody().toString(), null
                                        )
                                    )
                                }
                            }
                    } else _deckOfCards.postValue(
                        Resource.error(
                            "No internet connection",
                            null
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getDeckOfCardsMutableLiveData(): LiveData<Resource<DeckOfCards>?> {
        return _deckOfCards
    }

}