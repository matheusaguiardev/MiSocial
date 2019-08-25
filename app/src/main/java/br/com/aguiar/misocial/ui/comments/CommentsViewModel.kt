package br.com.aguiar.misocial.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aguiar.misocial.domain.comment.Comments
import br.com.aguiar.misocial.domain.comment.CommentsInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CommentsViewModel(
    private val interactor: CommentsInteractor
) : ViewModel(), CoroutineScope {

    private var commentsJobs = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + commentsJobs

    private val loading = MutableLiveData<Boolean>()
    fun loading(): LiveData<Boolean> = loading

    private val commentsList = MutableLiveData<List<Comments>>()
    fun commentsList(): LiveData<List<Comments>> = commentsList

    fun fetchComments(postId: Int) {

        commentsJobs = launch {
            loading.value = true
            val result = interactor()
            commentsList.value = result
                .filter { it.postId == postId }
                .sortedByDescending { it.id }
            loading.value = false
        }

    }


}