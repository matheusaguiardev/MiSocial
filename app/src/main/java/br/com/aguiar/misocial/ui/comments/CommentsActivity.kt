package br.com.aguiar.misocial.ui.comments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.comment.Comments
import br.com.aguiar.misocial.ui.adapter.CommentAdapter
import br.com.aguiar.misocial.ui.dialog.LoadingDialog
import br.com.aguiar.misocial.ui.extension.toHide
import br.com.aguiar.misocial.ui.extension.toVisible
import kotlinx.android.synthetic.main.activity_comments.*
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsActivity : AppCompatActivity() {

    private val viewModel: CommentsViewModel by viewModel()

    companion object {
        const val POST_ID = "POST_ID"
    }

    private val alertDialog by lazy {
        LoadingDialog()
    }

    private val adapter by lazy { CommentAdapter(this) }

    val postId by lazy {
        this.intent.getIntExtra(POST_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        with(viewModel){
            loading().observe(this@CommentsActivity, Observer(::loadingObserver))
            commentsList().observe(this@CommentsActivity, Observer(::commentsObserver))
            fetchComments(postId)
        }
    }

    private fun commentsObserver(commentList: List<Comments>?){
        commentList?.let{
            if (it.isNotEmpty()) {
                setUpList()
                adapter.dataSource = it
                emptyStateText.toHide()
                commentsList.toVisible()
            } else {
                emptyStateText.toVisible()
                commentsList.toHide()
            }
        }
    }

    private fun setUpList(){
        if(commentsList.adapter != null) return

        commentsList.layoutManager = LinearLayoutManager(this)
        commentsList.adapter = adapter
    }

    private fun loadingObserver(showDialog: Boolean?) {
        showDialog?.let {
            if (it) {
                showAlertDialog()
            } else {
                hideAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        if (!(alertDialog.isAdded && alertDialog.isVisible)) {
            val fm = supportFragmentManager
            alertDialog.show(fm, "fragment_alert")
        }
    }

    private fun hideAlertDialog() {
        if (alertDialog.isAdded || alertDialog.isVisible) {
            alertDialog.dismiss()
        }
    }
}
