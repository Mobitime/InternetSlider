package com.example.internetslider.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.internetslider.R
import com.example.internetslider.model.Page

class ViewPagerFragment : Fragment() {

    companion object {
        private const val ARG_PAGE = "page"

        fun newInstance(page: Page): ViewPagerFragment {
            return ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PAGE, page)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val page = arguments?.getSerializable(ARG_PAGE) as Page
        val webView = view.findViewById<WebView>(R.id.webView)

        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(page.url)
        }
    }
}
