package br.com.aguiar.misocial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aguiar.misocial.domain.post.Posts
import br.com.aguiar.misocial.domain.post.PostsInteractor
import br.com.aguiar.misocial.domain.user.UserInteractor
import br.com.aguiar.misocial.domain.user.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val interactor: PostsInteractor,
    private val userInteractor: UserInteractor
) : ViewModel(), CoroutineScope {

    private var mainJob = Job()
    private var homeJobs = Job()
    private var ownersJob = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + mainJob

    private val postList = MutableLiveData<List<Posts>>()
    fun postList(): LiveData<List<Posts>> = postList

    private val ownerList = MutableLiveData<List<Users>>()
    fun ownerList() : LiveData<List<Users>> = ownerList

    private val loading = MutableLiveData<Boolean>()
    fun loading(): LiveData<Boolean> = loading

    fun fetchPosts() {
        if(homeJobs.isActive) return
        homeJobs = launch {
            loading.value = true
            val result = interactor()
            postList.value = result.sortedByDescending { it.id }
            loading.value = false
        }

    }

    fun fetchOwners() {
        if(ownersJob.isActive) return
        ownersJob = launch {
            val result = userInteractor()
            ownerList.value = result
        }
    }

    override fun onCleared() {
        super.onCleared()
        homeJobs.cancel()
        ownersJob.cancel()
        mainJob.cancel()
    }

}