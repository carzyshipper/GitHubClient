package mustafaozhan.github.com.githubclient.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import mustafaozhan.github.com.githubclient.extensions.fadeIO
import mustafaozhan.github.com.githubclient.extensions.setState

@Suppress("OverridingDeprecatedMember")
/**
 * Created by Mustafa Ozhan on 1/29/18 at 1:06 AM on Arch Linux wit Love <3.
 */
class MyWebViewClient(private val mGifLayout: LinearLayout) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url)
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        mGifLayout.fadeIO(true)
        mGifLayout.visibility = View.VISIBLE

    }

    override fun onPageFinished(view: WebView?, url: String?) {
        // hide element by class name
        view?.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('position-relative js-header-wrapper ')[0].style.display='none'; })()")
        view?.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('footer container-lg px-3')[0].style.display='none'; })()")

        val manager = view?.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val i = manager.activeNetworkInfo
        val hasConnect = i != null && i.isConnected && i.isAvailable

        mGifLayout.fadeIO(false)
        if (hasConnect)
            mGifLayout.setState(State.SUCCESS)
        else
            mGifLayout.setState(State.FAILED)
    }
}