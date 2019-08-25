package br.com.aguiar.misocial.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.post.Posts
import br.com.aguiar.misocial.domain.user.Users
import br.com.aguiar.misocial.ui.adapter.PostAdapter
import br.com.aguiar.misocial.ui.comments.CommentsActivity
import br.com.aguiar.misocial.ui.dialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity() {

    private val alertDialog by lazy {
        LoadingDialog()
    }

    private val viewModel: HomeViewModel by inject()
    private val adapter by lazy {
        PostAdapter(::itemCallbackClick, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(viewModel) {
            postList().observe(this@HomeActivity, Observer(::postObservers))
            loading().observe(this@HomeActivity, Observer(::loadingObserver))
            ownerList().observe(this@HomeActivity, Observer(::updatePostOwners))
        }

        viewModel.fetchPosts()

    }

    private fun postObservers(list: List<Posts>?) {
        list?.let {
            viewModel.fetchOwners()
            adapter.setList(it)
            setUpList()
        }
    }

    private fun setUpList() {
        if (postsList.adapter == null) {
            postsList.layoutManager = GridLayoutManager(this, 1)
            postsList.adapter = adapter
        }
    }

    private fun updatePostOwners(owner: List<Users>?) {
        owner?.let {
            val list = adapter.getList()
            list.map { post -> post.createdBy = owner.find { it.id == post.userId }?.userName ?: "Guest"}
            adapter.setList(list)
        }
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

    private fun itemCallbackClick(postId: Int) {
        val intent = Intent(this, CommentsActivity::class.java)
        intent.putExtra(CommentsActivity.POST_ID, postId)
        startActivity(intent)
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